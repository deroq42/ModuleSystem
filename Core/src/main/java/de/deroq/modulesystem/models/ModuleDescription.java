package de.deroq.modulesystem.models;

import java.io.File;
import java.util.List;

public class ModuleDescription {

    private String name;
    private String author;
    private String version;
    private String mainClass;
    private String prefix;
    private List<String> depends;
    private List<String> softDepends;
    private File file;

    public ModuleDescription() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getDepends() {
        return depends;
    }

    public void setDepends(List<String> depends) {
        this.depends = depends;
    }

    public List<String> getSoftDepends() {
        return softDepends;
    }

    public void setSoftDepends(List<String> softDepends) {
        this.softDepends = softDepends;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
