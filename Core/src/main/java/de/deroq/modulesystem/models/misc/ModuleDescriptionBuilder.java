package de.deroq.modulesystem.models.misc;

import de.deroq.modulesystem.models.ModuleDescription;

import java.io.File;
import java.util.List;

public class ModuleDescriptionBuilder {

    private final ModuleDescription moduleDescription;

    public ModuleDescriptionBuilder() {
        this.moduleDescription = new ModuleDescription();
    }

    public ModuleDescriptionBuilder setName(String name) {
        moduleDescription.setName(name);
        return this;
    }

    public ModuleDescriptionBuilder setAuthor(String author) {
        moduleDescription.setAuthor(author);
        return this;
    }

    public ModuleDescriptionBuilder setVersion(String version) {
        moduleDescription.setVersion(version);
        return this;
    }

    public ModuleDescriptionBuilder setMainClass(String mainClass) {
        moduleDescription.setMainClass(mainClass);
        return this;
    }

    public ModuleDescriptionBuilder setPrefix(String prefix) {
        moduleDescription.setPrefix(prefix);
        return this;
    }

    public ModuleDescriptionBuilder setDepends(List<String> depends) {
        moduleDescription.setDepends(depends);
        return this;
    }

    public ModuleDescriptionBuilder setSoftDepends(List<String> softDepends) {
        moduleDescription.setSoftDepends(softDepends);
        return this;
    }

    public ModuleDescriptionBuilder setFile(File file) {
        moduleDescription.setFile(file);
        return this;
    }

    public ModuleDescription build() {
        return moduleDescription;
    }
}
