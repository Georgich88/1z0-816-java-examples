package com.georgeisaev.ocp.nio.exam.files.funcional;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;

public class WalkMethod {

    private static final Logger logger = Logger.getLogger(WalkMethod.class);

    public static void main(String[] args) throws IOException {
        walkWithFilter();
        walkInDirectories();
    }

    /**
     * What are some possible results of executing the following code? (Choose all that apply.)
     * A. It prints some files in the root directory.
     * B. It prints all files in the root directory.
     * C. FileSystemLoopException is thrown at runtime.
     * D. Another exception is thrown at runtime.
     * E. The code will not compile because of line u1 .
     * F. The code will not compile because of line u2 .
     *
     * @throws IOException
     */
    private static void walkInDirectories() throws IOException {
        var x = Path.of("/usr/local/..");
        Files.walk(x.toRealPath().getParent()) // u1
                .map(p -> p.toAbsolutePath().toString()) // u2
                .filter(s -> s.endsWith(".java")) // u3
                .collect(Collectors.toList())
                .forEach(logger::info);
    }

    /**
     * Assume that the directory /animals exists and is empty. What is the result of executing the following
     * code?
     * Question:
     * <pre>{@code
     * Path path = Path.of("/animals");
     *     try (var z = Files.walk(path)) {
     *         boolean b = z
     *             .filter((p,a) -> a.isDirectory() && !path.equals(p)) // x
     *             .findFirst().isPresent(); // y
     *         System.out.print(b ? "No Sub": "Has Sub");
     *     }
     * }</pre>
     * Answer: it will fall on line "x" because filter method requires Predicate function, and isDirectory is a
     * static method of Files helper class.
     */
    private static void walkWithFilter() {
        Path path = Path.of("/animals");
        try (var z = Files.walk(path)) {
            boolean b = z
                    .anyMatch((p) -> {
                        try {
                            return Files.readAttributes(p, BasicFileAttributes.class).isDirectory() && !path.equals(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    });
            logger.info(b ? "No Sub" : "Has Sub");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
