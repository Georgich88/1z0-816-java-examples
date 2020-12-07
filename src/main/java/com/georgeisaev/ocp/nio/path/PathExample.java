package com.georgeisaev.ocp.nio.path;

import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class PathExample {

    private static final Logger logger = Logger.getLogger(PathExample.class);

    public static void main(String[] args) {
        printPathNameElements();
        printSubpath();
        printPathInformation(Path.of("/nio/zoo"));
        printPathInformation(Path.of("/nio/zoo/armadillo/shells.txt"));
        printPathInformation(Path.of("./zoo/armadillo/../shells.txt"));
        printSystemUserDir();
        printAbsolutePath();
    }

    /**
     * Illustrates {@link Path#getName(int)} and  {@link Path#getNameCount()} methods of {@link Path} interface.
     */
    private static void printPathNameElements() {
        Path path = Paths.get("/nio/land/hippo/harry.happy");
        logger.info("Path name is: '" + path + "'");
        IntStream.range(0, path.getNameCount()).forEach(i -> logger.info("Element " + i + " is " + path.getName(i)));
        Path absolutePath = Path.of("/");
        logger.info("Name count for absolute path: " + absolutePath.getNameCount());
        try {
            logger.info(absolutePath.getName(0));
        } catch (Exception e) {
            logger.error("The root element is not included in the list of names", e);
        }
    }

    /**
     * Illustrates {@link Path#subpath(int, int)} method of {@link Path} interface.
     */
    private static void printSubpath() {
        Path path = Paths.get("/nio/mammal/omnivore/raccoon.image");
        logger.info("Path is " + path);
        IntStream.range(0, path.getNameCount()).forEach(i -> {
            logger.info("Element " + i + " is: " + path.getName(i));
        });
        logger.info("sub-path (0,3) is '" + path.subpath(0, 3) + "'");
        logger.info("sub-path (1,2) is '" + path.subpath(1, 2) + "'");
        logger.info("sub-path (1,3) is '" + path.subpath(1, 3) + "'");
        try {
            Path illegalSubpath = path.subpath(0, 4);
        } catch (Exception e) {
            logger.error("Like getNameCount() and getName(), subpath() is 0-indexed and does not include the root.", e);
        }
    }

    /**
     * Prints name, root and parents of a given file.
     * Illustrates {@link Path#getFileName()}, {@link Path#getRoot()} and  {@link Path#getParent()} methods of
     * {@link Path} interface.
     *
     * @param path path to the file
     */
    public static void printPathInformation(Path path) {
        logger.info("Filename is: " + path.getFileName());
        logger.info("\tRoot is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null) {
            logger.info("\tCurrent parent is: " + currentParent);
        }
    }


    /**
     * Prints the value that {@link Path#toAbsolutePath()} uses when applied to a relative path.
     */
    public static void printSystemUserDir() {
        logger.info("user.dir: " + System.getProperty("user.dir"));
    }

    /**
     * Shows usage of both of methods {@link Path#isAbsolute()} and {@link Path#toAbsolutePath()}.
     */
    private static void printAbsolutePath() {
        Path pathToBird = Paths.get(
                "/usr/local/src/dev/projects/edu/1z0-816-java-examples/resources/nio/birds/egret.txt");
        logger.info("pathToBird is absolute?: " + pathToBird.isAbsolute());
        logger.info("Absolute pathToBird: " + pathToBird.toAbsolutePath());
        Path pathToCondor = Paths.get("birds/condor.txt");
        logger.info("pathToCondor is absolute?: " + pathToCondor.isAbsolute());
        logger.info("Absolute pathToCondor: " + pathToCondor.toAbsolutePath());
    }

}
