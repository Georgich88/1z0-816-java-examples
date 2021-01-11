package com.georgeisaev.ocp.nio.exam.files.symbolic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesIsSymbolicLinkMethod {

    public static void main(String[] args) throws IOException {
        createDirectoryInSymbolicLinkDirectory();
    }

    /**
     * Assume /kang exists as a symbolic link to the directory /mammal/kangaroo within the file system.
     * Which of the following statements are correct about this code snippet?
     * <pre>{@code
     *     var path = Paths.get("/kang");
     *     if(Files.isDirectory(path) && Files.isSymbolicLink(path))
     *         Files.createDirectory(path.resolve("joey"));
     * }</pre>
     *
     * @throws IOException
     */
    private static void createDirectoryInSymbolicLinkDirectory() throws IOException {
        Path path = Paths.get("/kang");
        if (Files.isDirectory(path) && Files.isSymbolicLink(path)) {
            Files.createDirectory(path.resolve("joey"));
        }
    }

}
