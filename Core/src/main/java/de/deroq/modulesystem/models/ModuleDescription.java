package de.deroq.modulesystem.models;

import java.io.File;
import java.util.List;

/**
 * @author deroq
 * @since 01.05.2022
 */

public class ModuleDescription {

    private final String name;
    private final String author;
    private final String version;
    private final String mainClass;
    private final String prefix;
    private final List<String> depends;
    private final List<String> softDepends;
    private final File file;

    private ModuleDescription(String name, String author, String version, String mainClass, String prefix, List<String> depends, List<String> softDepends, File file) {
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

    public String getPrefix() {
        return prefix;
    }

    public List<String> getDepends() {
        return depends;
    }

    public List<String> getSoftDepends() {
        return softDepends;
    }

    public File getFile() {
        return file;
    }

    public static class builder {

        private String name;
        private String author;
        private String version;
        private String mainClass;
        private String prefix;
        private List<String> depends;
        private List<String> softDepends;
        private File file;

        public builder setName(String name) {
            this.name = name;
            return this;
        }

        public builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public builder setMainClass(String mainClass) {
            this.mainClass = mainClass;
            return this;
        }

        public builder setPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public builder setDepends(List<String> depends) {
            this.depends = depends;
            return this;
        }

        public builder setSoftDepends(List<String> softDepends) {
            this.softDepends = softDepends;
            return this;
        }

        public builder setFile(File file) {
            this.file = file;
            return this;
        }

        public ModuleDescription build() {
            return new ModuleDescription(name, author, version, mainClass, prefix, depends, softDepends, file);
        }
    }
}
