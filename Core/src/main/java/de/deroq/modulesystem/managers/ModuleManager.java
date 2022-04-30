package de.deroq.modulesystem.managers;

import de.deroq.modulesystem.models.Module;
import de.deroq.modulesystem.exceptions.ModuleLoadException;
import de.deroq.modulesystem.models.ModuleDescription;
import de.deroq.modulesystem.modules.BukkitModule;
import de.deroq.modulesystem.modules.BungeeModule;
import de.deroq.modulesystem.utils.ModuleType;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModuleManager {

    private final List<Module> modules;
    private final List<BukkitModule> bukkitModules;
    private final List<BungeeModule> bungeeModules;
    private final File modulesDirectory;
    private final Class<?> clazz;
    private final ModuleType moduleType;

    public ModuleManager(File modulesDirectory, Class<?> clazz, ModuleType moduleType) {
        this.modules = new ArrayList<>();
        this.bukkitModules = new ArrayList<>();
        this.bungeeModules = new ArrayList<>();
        this.modulesDirectory = modulesDirectory;
        this.clazz = clazz;
        this.moduleType = moduleType;
    }

    public void loadModules() {
        for (File file : modulesDirectory.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }

            if (!file.getName().endsWith(".jar")) {
                continue;
            }

            try {
                Module module = loadModule(file);
                modules.add(module);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Set<Module> used = new HashSet<>();
        Queue<Module> order = new LinkedList<>();

        for (Module module : modules) {
            try {
                initDependencies(module, used, order);
            } catch (ModuleLoadException e) {
                e.printStackTrace();
            }
        }

        for (Module module : order) {
            ModuleDescription moduleDescription = module.getModuleDescription();

            try {
                if (!clazz.isAssignableFrom(module.getClazz())) {
                    throw new ModuleLoadException("Error while loading module" + moduleDescription.getName() + ": Main class does not extend Module.");
                }

                try {
                    if (moduleType == ModuleType.BUKKIT) {
                        BukkitModule bukkitModule = (BukkitModule) module.getClazz().newInstance();
                        bukkitModule.onLoad();
                        bukkitModules.add(bukkitModule);
                    }

                    if (moduleType == ModuleType.BUNGEE) {
                        BungeeModule bungeeModule = (BungeeModule) module.getClazz().newInstance();
                        bungeeModule.onLoad();
                        bungeeModules.add(bungeeModule);
                    }

                    System.out.println("Module " + moduleDescription.getName() + " version " + moduleDescription.getVersion() + " by " + moduleDescription.getAuthor() + " has been loaded.");
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ModuleLoadException e) {
                e.printStackTrace();
            }
        }
    }

    public void initDependencies(Module module, Set<Module> used, Queue<Module> order) throws ModuleLoadException {
        if (used.contains(module)) {
            return;
        }

        used.add(module);
        ModuleDescription moduleDescription = module.getModuleDescription();
        List<String> depends = Stream.concat(moduleDescription.getDepends().stream(), moduleDescription.getSoftDepends().stream()).collect(Collectors.toList());

        for(String depend : depends) {
            if (!getModule(depend).isPresent()) {
                throw new ModuleLoadException("Error while loading module " + moduleDescription.getName() + ": Dependency " + depend + " can not be found.");
            }

            Module dependModule = getModule(depend).get();
            if (used.contains(dependModule)) {
                continue;
            }

            initDependencies(dependModule, used, order);
        }

        order.add(module);
    }

    private Module loadModule(File file) throws IOException, ModuleLoadException, ClassNotFoundException {
        JarFile jarFile = new JarFile(file);
        List<String> moduleFileNames = Arrays.asList("module.yml", "bukkitmodule.yml", "bungeemodule.yml");
        Optional<JarEntry> jarEntry = Optional.empty();

        for (String moduleFileName : moduleFileNames) {
            if (jarFile.getJarEntry(moduleFileName) == null) {
                continue;
            }

            jarEntry = Optional.of(jarFile.getJarEntry(moduleFileName));
            break;
        }

        if (!jarEntry.isPresent()) {
            throw new ModuleLoadException("Error while loading module " + jarFile.getName().split("\\\\")[3] + ": module.yml can not be found.");
        }

        InputStream inputStream = jarFile.getInputStream(jarEntry.get());
        Map<String, Object> properties = new Yaml().load(inputStream);

        ModuleDescription moduleDescription = parseModuleDescription(properties, file);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, clazz.getClassLoader());
        Class<?> main = classLoader.loadClass(moduleDescription.getMainClass());

        return new Module(main, moduleDescription, moduleType);
    }

    private ModuleDescription parseModuleDescription(Map<String, Object> properties, File file) throws ModuleLoadException {
        String[] keys = new String[]{"name", "author", "version", "main", "prefix"};

        for (int i = 0; i < keys.length; i++) {
            if (!properties.containsKey(keys[i])) {
                throw new ModuleLoadException("Error while loading module " + file.getName() + ": " + keys[i] + " can not be found.");
            }
        }

        String name = (String) properties.get("name");
        String author = (String) properties.get("author");
        String version = (String) properties.get("version");
        String mainClass = (String) properties.get("main");
        String prefix = (String) properties.get("prefix");
        List<String> depends = new ArrayList<>();
        List<String> softDepends = new ArrayList<>();

        if(properties.containsKey("depends")) {
            depends = (List<String>) properties.get("depends");
        }
        if(properties.containsKey("softDepends")) {
            softDepends = (List<String>) properties.get("softDepends");
        }

        return new ModuleDescription(name, author, version, mainClass, prefix, depends, softDepends, file);
    }

    public Optional<Module> getModule(String name) {
        for (Module module : modules) {
            if (!module.getModuleDescription().getName().equals(name)) {
                continue;
            }

            return Optional.of(module);
        }

        return Optional.empty();
    }

    public Optional<Module> getModule(Class<?> clazz) {
        for (Module module : modules) {
            if (!module.getClazz().equals(clazz)) {
                continue;
            }

            return Optional.of(module);
        }

        return Optional.empty();
    }

    public Optional<BukkitModule> getBukkitModule(String name) {
        for (BukkitModule bukkitModule : bukkitModules) {
            if (!bukkitModule.getModule().getModuleDescription().getName().equals(name)) {
                continue;
            }

            return Optional.of(bukkitModule);
        }

        return Optional.empty();
    }

    public Optional<BungeeModule> getBungeeModule(String name) {
        for (BungeeModule bungeeModule : bungeeModules) {
            if (bungeeModule.getModule().getModuleDescription().getName().equals(name)) {
                continue;
            }

            return Optional.of(bungeeModule);
        }

        return Optional.empty();
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<BukkitModule> getBukkitModules() {
        return bukkitModules;
    }

    public List<BungeeModule> getBungeeModules() {
        return bungeeModules;
    }

    public File getModulesDirectory() {
        return modulesDirectory;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }
}

