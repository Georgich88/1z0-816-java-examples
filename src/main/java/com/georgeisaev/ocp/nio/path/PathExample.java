package com.georgeisaev.ocp.nio.path;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class PathExample {

    private static final Logger logger = Logger.getLogger(PathExample.class);
    private static final String LINE_SEPARATOR = "---------------------";

    public static void main(String[] args) {
        printPathNameElements();
        printSubpath();
        printPathInformation(Path.of("/nio/zoo"));
        printPathInformation(Path.of("/nio/zoo/armadillo/shells.txt"));
        printPathInformation(Path.of("./zoo/armadillo/../shells.txt"));
        printSystemUserDir();
        printAbsolutePath();
        printResolvedPaths();
        printRelativePaths();
        printNormalizedPaths();
        printRealPath();
    }

    /**
     * Illustrates {@link Path#getName(int)} and  {@link Path#getNameCount()} methods of {@link Path} interface.
     */
    private static void printPathNameElements() {
        logger.info(LINE_SEPARATOR);
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
        logger.info(LINE_SEPARATOR);
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
        logger.info(LINE_SEPARATOR);
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
        logger.info(LINE_SEPARATOR);
        Path pathToBird = Paths.get(
                "/usr/local/src/dev/projects/edu/1z0-816-java-examples/resources/nio/birds/egret.txt");
        logger.info("pathToBird is absolute?: " + pathToBird.isAbsolute());
        logger.info("Absolute pathToBird: " + pathToBird.toAbsolutePath());
        Path pathToCondor = Paths.get("birds/condor.txt");
        logger.info("pathToCondor is absolute?: " + pathToCondor.isAbsolute());
        logger.info("Absolute pathToCondor: " + pathToCondor.toAbsolutePath());
    }

    /**
     * Prints path resolved by {@link Path#resolve(Path)} methods.
     */
    private static void printResolvedPaths() {
        logger.info(LINE_SEPARATOR);
        Path path1 = Path.of("/nio/cat/../panther");
        Path path2 = Path.of("food");
        logger.info(path1.resolve(path2));
    }

    /**
     * Shows the result of {@link Path#relativize(Path)} method.
     */
    private static void printRelativePaths() {
        logger.info(LINE_SEPARATOR);
        logger.info("relativize(Path)");
        Path path1 = Path.of("fish.txt");
        Path path2 = Path.of("friendly/birds.txt");
        logger.info(path1.relativize(path2));
        logger.info(path2.relativize(path1));
        // The idea is this: if you are pointed at a path in the file system,
        // what steps would you need to take to reach the other path?
        Path path3 = Paths.get("/usr/habitat");
        Path path4 = Paths.get("/usr/sanctuary/raven/poe.txt");
        logger.info(path3.relativize(path4));
        logger.info(path4.relativize(path3));
    }

    /**
     * Illustrates unnecessary redundancy elimination in a path by {@link Path#normalize()} method.
     */
    private static void printNormalizedPaths() {
        logger.info(LINE_SEPARATOR);
        Path p1 = Path.of("./armadillo/../shells.txt");
        logger.info(p1.normalize()); // shells.txt
        Path p2 = Path.of("/cats/../panther/food");
        logger.info(p2.normalize()); // /panther/food
        Path p3 = Path.of("../../fish.txt");
        logger.info(p3.normalize()); // ../../fish.txt
        Path p4 = Paths.get("/pony/../weather.txt");
        Path p5 = Paths.get("/weather.txt");
        logger.info(p4.equals(p5)); // false
        logger.info(p4.normalize().equals(p5.normalize())); // true
    }

    /**
     * Shows the result of {@link Path#toRealPath(LinkOption...)} method.
     */
    private static void printRealPath() {
        logger.info(LINE_SEPARATOR);
        try {
            logger.info(Paths.get("src/main/resources/nio/zebra/food.txt").toRealPath());
            logger.info(Paths.get(".").toRealPath());
            logger.info(Paths.get(".././food.txt").toRealPath());
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
