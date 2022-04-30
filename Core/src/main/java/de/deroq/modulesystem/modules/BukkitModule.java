package de.deroq.modulesystem.modules;

import de.deroq.modulesystem.managers.ModuleManager;
import de.deroq.modulesystem.models.Module;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BukkitModule {

    private final Module module;

    public BukkitModule(ModuleManager moduleManager) {
        this.module = moduleManager.getModule(this.getClass()).get();
    }

    public void onLoad() {

    }

    public void onUnload() {

    }

    public Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("ModuleSystem");
    }

    public Module getModule() {
        return module;
    }
}
