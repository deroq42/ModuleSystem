package de.deroq.moduletest;

import de.deroq.modulesystem.ModuleSystemBukkit;
import de.deroq.modulesystem.modules.BukkitModule;

public class ModuleTest extends BukkitModule {

    public ModuleTest() {
        super(ModuleSystemBukkit.getModuleSystemBukkit().getModuleManager());
    }

    @Override
    public void onLoad() {
        getPlugin().getLogger().info("ModuleTest has been enabled.");
    }

    @Override
    public void onUnload() {
        getPlugin().getLogger().info("ModuleTest has been disabled.");
    }
}
