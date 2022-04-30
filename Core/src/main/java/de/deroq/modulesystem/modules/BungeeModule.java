package de.deroq.modulesystem.modules;

import de.deroq.modulesystem.managers.ModuleManager;
import de.deroq.modulesystem.models.Module;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeModule {

    private final Module module;

    public BungeeModule(ModuleManager moduleManager) {
        this.module = moduleManager.getModule(this.getClass()).get();
    }

    public void onLoad() {

    }

    public void onUnload() {

    }

    public Plugin getPlugin() {
        return ProxyServer.getInstance().getPluginManager().getPlugin("ModuleSystem");
    }

    public Module getModule() {
        return module;
    }
}
