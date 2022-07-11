package de.deroq.modulesystem.models;

import de.deroq.modulesystem.utils.ModuleType;

/**
 * @author deroq
 * @since 01.05.2022
 */

public class Module {

    private final Class<?> clazz;
    private final ModuleDescription moduleDescription;
    private final ModuleType moduleType;

    public Module(Class<?> clazz, ModuleDescription moduleDescription, ModuleType moduleType) {
        this.clazz = clazz;
        this.moduleDescription = moduleDescription;
        this.moduleType = moduleType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public ModuleDescription getModuleDescription() {
        return moduleDescription;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }
}
