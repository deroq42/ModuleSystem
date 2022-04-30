package de.deroq.modulesystem;

import de.deroq.modulesystem.managers.ModuleManager;
import de.deroq.modulesystem.modules.BukkitModule;
import de.deroq.modulesystem.modules.BungeeModule;
import de.deroq.modulesystem.utils.ModuleType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ModuleSystemBungee extends JavaPlugin {

    private static ModuleSystemBungee moduleSystemBungee;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        makeInstances();

        getLogger().info("ModuleSystem has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ModuleSystem has been disabled.");
    }

    private void makeInstances() {
        moduleSystemBungee = this;

        this.moduleManager = new ModuleManager(new File("plugins/ModuleSystem/modules"), BungeeModule.class, ModuleType.BUNGEE);
        moduleManager.loadModules();
    }

    public static ModuleSystemBungee getModuleSystemBungee() {
        return moduleSystemBungee;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}