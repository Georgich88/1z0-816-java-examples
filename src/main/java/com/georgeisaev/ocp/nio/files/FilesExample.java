package com.georgeisaev.ocp.nio.files;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesExample {

    private static final Logger logger = Logger.getLogger(FilesExample.class);
    private static final String LINE_SEPARATOR = "---------------------";
    private static final String FILE_PATH_CANINE_FUR = "src/main/resources/nio/canine/fur.jpg";
    private static final String FILE_PATH_CANINE_TYPES = "src/main/resources/nio/canine/types.txt";
    private static final String FILE_PATH_COYOTE = "src/main/resources/nio/canine/coyote";
    private static final String FILE_PATH_ZEBRA_FOOD = "src/main/resources/nio/zebra/food.txt";

    public static void main(String[] args) throws IOException {
        printFileExists();
        printSameFile();
        determinePathType();
        checkFileAccessibility();
        printSize();
    }

    /**
     * Shows the result of {@link Files#exists(Path, LinkOption...)} method.
     */
    private static void printFileExists() {
        logger.info(LINE_SEPARATOR);
        boolean b1 = Files.exists(Paths.get(FILE_PATH_ZEBRA_FOOD));
        logger.info("Path " + (b1 ? "Exists" : "Missing"));
        boolean b2 = Files.exists(Paths.get("/ostrich"));
        logger.info("Path " + (b2 ? "Exists" : "Missing"));
    }

    /**
     * Shows the result of {@link Files#isSameFile(Path, Path)} method.
     */
    private static void printSameFile() throws IOException {
        logger.info(LINE_SEPARATOR);
        logger.info(Files.isSameFile(
                Path.of("src/main/resources/nio/animals/cobra"),
                Path.of("src/main/resources/nio/animals/snake")));
        logger.info(Files.isSameFile(
                Path.of("src/main/resources/nio/animals/monkey/ears.png"),
                Path.of("src/main/resources/nio/animals/wolf/ears.png")));
    }

    /**
     * Shows the result of three methods for determining type of a {@link Path}:
     * {@link Files#isDirectory(Path, LinkOption...)}
     * {@link Files#isSymbolicLink(Path)}
     * {@link Files#isRegularFile(Path, LinkOption...)}
     */
    private static void determinePathType() {
        logger.info(LINE_SEPARATOR);
        logger.info("isDirectory: " + Files.isDirectory(Paths.get(FILE_PATH_CANINE_FUR)));
        logger.info("isSymbolicLink: " + Files.isSymbolicLink(Paths.get(FILE_PATH_COYOTE)));
        logger.info("isRegularFile: " + Files.isRegularFile(Paths.get(FILE_PATH_CANINE_TYPES)));
    }

    /**
     * In many file systems, it is possible to set a boolean attribute to a file that marks it hidden, readable, or
     * executable. The Files class includes methods that expose this information:
     * {@link Files#isHidden(Path)}
     * {@link Files#isReadable(Path)}
     * {@link Files#isWritable(Path)}
     * {@link Files#isExecutable(Path)}
     *
     * @throws IOException
     */
    private static void checkFileAccessibility() throws IOException {
        logger.info(LINE_SEPARATOR);
        logger.info("In many file systems, it is possible to set a boolean attribute to a file that marks \n" +
                "it hidden, readable, or executable. The Files class includes methods that expose this information");
        logger.info(Files.isHidden(Paths.get("src/main/resources/nio/walrus.txt")));
        logger.info(Files.isReadable(Paths.get("src/main/resources/nio/seal/baby.png")));
        logger.info(Files.isWritable(Paths.get("src/main/resources/nio/dolphin.txt")));
        logger.info(Files.isExecutable(Paths.get("src/main/resources/nio/whale.png")));
    }

    private static void printSize() throws IOException {
        logger.info(LINE_SEPARATOR);
        logger.info("The Files class includes a method to determine the size of the file in bytes.");
        logger.info("Files.size = " + Files.size(Paths.get(FILE_PATH_CANINE_FUR)));
        logger.info("Files.size = " + Files.size(Paths.get(FILE_PATH_CANINE_TYPES)));
    }

}