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
    private static final String FILE_PATH_ZEBRA_FOOD = "src/main/resources/nio/zebra/food.txt";

    public static void main(String[] args) throws IOException {
        printFileExists();
        printSameFile();
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

}