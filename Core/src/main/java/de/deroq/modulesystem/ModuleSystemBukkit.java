package de.deroq.modulesystem;

import de.deroq.modulesystem.managers.ModuleManager;
import de.deroq.modulesystem.modules.BukkitModule;
import de.deroq.modulesystem.utils.ModuleType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ModuleSystemBukkit extends JavaPlugin {

    private static ModuleSystemBukkit moduleSystemBukkit;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        moduleSystemBukkit = this;
        initManagers();

        getLogger().info("ModuleSystemBukkit has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ModuleSystemBukkit has been disabled.");
    }

    private void initManagers() {
        this.moduleManager = new ModuleManager(new File("plugins/ModuleSystem/modules"), BukkitModule.class, ModuleType.BUKKIT);
        moduleManager.loadModules();
    }

    public static ModuleSystemBukkit getModuleSystemBukkit() {
        return moduleSystemBukkit;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
