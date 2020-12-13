package com.georgeisaev.ocp.nio.exam.paths;

import com.georgeisaev.ocp.nio.files.FilesExample;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SubpathMethod {

    private static final Logger logger = Logger.getLogger(FilesExample.class);

    public static void main(String[] args) {
        printSubpathAndToPathMethodChain();
    }

    /**
     * Illustrates the question of {@link Path#subpath(int, int)} and {@link Path#getName(int)} methods.
     * Question:
     * If the current working directory is /user/home , then what is the output of the following code?
     * <pre>{@code
     *     var p = Paths.get("/zoo/animals/bear/koala/food.txt");
     *     System.out.print(p.subpath(1,3).getName(1).toAbsolutePath());
     * }</pre>
     * <p>
     * Answer:
     * The invocation steps:
     *     0. Path indices [0]/zoo[1]/animals[2]/bear[3]/koala[4]/food.txt[5]
     *     1. subpath(1, 3) provides following result: /animals/bear ([0]/animals[1]/bear[3])
     *     2. subpath(1, 3).getName(1): /animals
     *     3. subpath(1, 3).getName(1).toAbsolutePath(): /user/home/animals
     */
    private static void printSubpathAndToPathMethodChain() {
        Path path = Paths.get("/zoo/animals/bear/koala/food.txt");
        logger.info("subpath(1, 3): " + path.subpath(1, 3));
        logger.info("subpath(1, 3).getName(1): " + path.subpath(1, 3).getName(1));
        logger.info(path.subpath(1, 3).getName(1).toAbsolutePath());
    }

}
