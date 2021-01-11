package com.georgeisaev.ocp.nio.exam.files.mkdir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectoriesMethod {

    public static void main(String[] args) throws IOException {
        createDirectoriesExample();
    }

    /**
     * If the current working directory is /zoo and the path /zoo/turkey does not exist, then what is the
     * result of executing the following code?
     * <p>
     * Answer: this method checks if both Path objects locate the same file, and depending on the implementation, may
     * require to open or access both files. Because both paths do not exists it will throw an error when trying to
     * access them.
     *
     * @throws IOException
     */
    private static void createDirectoriesExample() throws IOException {
        Path path = Paths.get("turkey");
        if (Files.isSameFile(path, Paths.get("/zoo/turkey"))) {
            Files.createDirectories(path.resolve("info"));
        }
    }

}
