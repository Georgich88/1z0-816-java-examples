package com.georgeisaev.ocp.nio.exam.files.attributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class ReadAttributeMethod {

    public static void main(String[] args) throws IOException {
        setTimes();
    }

    /**
     * @throws IOException
     */
    public static void setTimes() throws IOException {
        Path path = Paths.get("sloth.schedule");
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        BasicFileAttributeView attributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        Files.createDirectories(path.resolve(".backup")); // mkdir is legacy File method
        if (attributes.size() > 0 && attributes.isDirectory()) {
            // BasicFileAttributes is read-only attributes
            // to set time BasicFileAttributeView should be used
            attributeView.setTimes(null, null, null);
        }
    }

}
