package de.deroq.modulesystem.models;

import java.io.File;
import java.util.List;

public class ModuleDescription {

    private final String name;
    private final String author;
    private final String version;
    private final String mainClass;
    private final String prefix;
    private final List<String> depends;
    private final List<String> softDepends;
    private final File file;

    public ModuleDescription(String name, String author, String version, String mainClass, String prefix, List<String> depends, List<String> softDepends, File file) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.mainClass = mainClass;
        this.prefix = prefix;
        this.depends = depends;
        this.softDepends = softDepends;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getMainClass() {
        return mainClass;
    }

    public List<String> getDepends() {
        return depends;
    }

    public List<String> getSoftDepends() {
        return softDepends;
    }

    public String getPrefix() {
        return prefix;
    }

    public File getFile() {
        return file;
    }
}
