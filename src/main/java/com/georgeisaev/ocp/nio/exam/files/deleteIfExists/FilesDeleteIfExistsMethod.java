package com.georgeisaev.ocp.nio.exam.files.deleteIfExists;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesDeleteIfExistsMethod {

    private static final String MSG_INFO_SUCCESS = "Success";
    private static final String MSG_ERR_TRY_AGAIN = "Try Again";

    public static void main(String[] args) {
        Console console = System.console();
        console.printf("Input filepath: ");
        String filepath;
        while ((filepath = console.readLine()) != null && !filepath.isBlank()) {
            try {
                removeBadFile(Path.of(filepath), console);
            } catch (IOException e) {
                console.printf("%s %s\n", MSG_ERR_TRY_AGAIN, e.getMessage());
            }
        }
    }

    /**
     * Question:
     * For which values of path sent to this method would it be possible for the following code to output "Success"?
     * <pre>{@code
     *     public void removeBadFile(Path path) {
     *         if (Files.isDirectory(path)) {
     *             System.out.println(Files.deleteIfExists(path)
     *                     ? "Success" : "Try Again");
     *         }
     *     }
     * }</pre>
     * <p>
     * Correct answer: all file manipulation methods throw IOException, so without handling exception it won't compile.
     *
     * @param path    - path to the file to remove
     * @param console - console to output result
     * @throws IOException
     */
    public static void removeBadFile(Path path, Console console) throws IOException {
        if (Files.isDirectory(path)) {
            console.printf(Files.deleteIfExists(path)
                    ? MSG_INFO_SUCCESS : MSG_ERR_TRY_AGAIN);
        }
    }

}
