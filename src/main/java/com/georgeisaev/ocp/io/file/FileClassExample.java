package com.georgeisaev.ocp.io.file;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import static com.georgeisaev.ocp.io.file.ZooPropertyFile.*;
import static java.util.Arrays.deepToString;

/**
 * Possible properties of zoo management center.
 */
enum ZooPropertyFile {
    IO_PATH, ZOO_1, ZOO_2, ZOO_3;
}

/**
 * Illustrates work with {@linkplain java.io.File} class.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class FileClassExample {

    private static final Logger logger = Logger.getLogger(FileClassExample.class);
    private static final URL URL_IO_RESOURCES = FileClassExample.class.getClassLoader().getResource("io");
    private static final String FILEPATH_IO = URL_IO_RESOURCES.getPath();
    private static final String FILEPATH_ZOO_1 = "zoo1.txt";
    private static final String FILEPATH_IO_ZOO_2 = "zoo2.txt";
    private static final String FILEPATH_IO_ZOO_3 = "zoo3.txt";

    public static void main(String[] args) {
        var properties = retrievePropertyFile();
        logger.info(properties);
        properties.forEach((property, file) -> logger.info(property + (file.exists() ? " exists" : " does not exist")));
        properties.forEach((property, file) -> logger.info(property + (file.isFile() ? " is a file" :
                " is not a file")));
        properties.forEach((property, file) -> logger.info(property + (file.isDirectory() ? " is a directory" :
                " is not a directory")));
        properties.forEach((property, file) -> logger.info(property + " absolute path: " + file.getAbsolutePath()));
        properties.forEach((property, file) -> logger.info(property + " absolute file: " + file.getAbsoluteFile()));
        properties.forEach((property, file) -> logger.info(property + " parent: " + file.getParent()));
        properties.forEach((property, file) -> logger.info(property + " last modified: " + new Date(file.lastModified())));
        properties.forEach((property, file) -> logger.info(property + " children: " + deepToString(file.listFiles())));

    }

    private static Map<ZooPropertyFile, File> retrievePropertyFile() {
        Map<ZooPropertyFile, File> propertyFiles = new EnumMap<>(ZooPropertyFile.class);
        File pathIo = new File(FILEPATH_IO);
        File firstZooFile = new File(FILEPATH_IO + File.separator + FILEPATH_ZOO_1);
        File secondZooFile = new File(FILEPATH_IO, FILEPATH_IO_ZOO_2);
        File thirdZooFile = new File(pathIo, FILEPATH_IO_ZOO_3);
        propertyFiles.put(IO_PATH, pathIo);
        propertyFiles.put(ZOO_1, firstZooFile);
        propertyFiles.put(ZOO_2, secondZooFile);
        propertyFiles.put(ZOO_3, thirdZooFile);
        return propertyFiles;
    }

}
