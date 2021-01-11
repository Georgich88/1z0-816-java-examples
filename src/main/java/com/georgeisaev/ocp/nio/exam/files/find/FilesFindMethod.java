package com.georgeisaev.ocp.nio.exam.files.find;

import com.georgeisaev.ocp.nio.files.FilesExample;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
public class FilesFindMethod {

    private static final Logger logger = Logger.getLogger(FilesExample.class);
    private static final String DIR_PATH = "src/main/resources/nio/pathtest";

    public static void main(String[] args) throws IOException {
        findRecursively();
        findSymbolicLinks();
    }

    private static void findSymbolicLinks() throws IOException {
        var f = Path.of("/monkeys");
        try (var m =
                     Files.find(f, 0, (p,a) -> a.isSymbolicLink())) { // y1
            m.map(s -> s.toString())
                    .collect(Collectors.toList())
                    .stream()
                    .filter(s -> s.toString().endsWith(".txt")) // y2
                    .forEach(System.out::println);
        }
    }

    private static void findRecursively() {
        try {
            Stream<Path> s = Files.find(Paths.get(DIR_PATH), Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.endsWith("test.txt") && basicFileAttributes.isRegularFile());
            s.forEach(logger::info);
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
