package com.georgeisaev.ocp.nio.files;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Given that ./pathtest is a directory that contains several sub directories.
 * Each sub directory contains several files but there is exactly one regular file named file_to_find.txt
 * within the whole directory structure.
 * <p>
 * Question:
 * Which of the given options can be inserted in the code below so that it will print complete path of test.txt?
 * <p>
 * Code snippet to investigate.
 * <pre>{@code
 * try {
 *     Stream<Path> s = null;
 *     // INSERT CODE HERE
 *     s.forEach(System.out::println);
 * } catch(IOException ioe){
 *     ioe.printStackTrace();
 * }
 * }</pre>
 */
public class ExamFilesFindMethod {

    private static final Logger logger = Logger.getLogger(FilesExample.class);
    private static final String DIR_PATH = "src/main/resources/nio/pathtest";

    public static void main(String[] args) throws IOException {
        try {
            Stream<Path> s = Files.find(Paths.get(DIR_PATH), Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.endsWith("test.txt") && basicFileAttributes.isRegularFile());
            s.forEach(logger::info);
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
