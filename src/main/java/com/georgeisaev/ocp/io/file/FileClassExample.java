package com.georgeisaev.ocp.io.file;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import static com.georgeisaev.ocp.io.file.ZooPropertyFile.*;
import static java.util.Arrays.deepToString;
import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

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
    private static final String FILEPATH_IO;
    static {
        assert URL_IO_RESOURCES != null;
        FILEPATH_IO = URL_IO_RESOURCES.getPath();
    }
    private static final String FILEPATH_ZOO_1 = "zoo1.txt";
    private static final String FILEPATH_IO_ZOO_2 = "zoo2.txt";
    private static final String FILEPATH_IO_ZOO_3 = "zoo3.txt";

    public static void main(String[] args) {
        Map<ZooPropertyFile, File> properties = retrievePropertyFile();
        logger.info(properties);
        // Checks if a file exists
        properties.forEach((property, file) -> logger.info(property + (file.exists() ? " exists" : " does not exist")));
        // Checks if a File reference is a file within the file system
        properties.forEach((property, file) -> logger.info(property + (file.isFile() ? " is a file" :
                " is not a file")));
        // Checks if a File reference is a directory within the file system
        properties.forEach((property, file) -> logger.info(property + (file.isDirectory() ? " is a directory" :
                " is not a directory")));
        // Retrieves the name of the file or directory.
        properties.forEach((property, file) -> logger.info(property + " name: " + file.getName()));
        // Retrieves the absolute name of the file or directory within the file system
        properties.forEach((property, file) -> logger.info(property + " absolute file: " + file.getAbsoluteFile()));
        // Retrieves the parent directory that the path is contained in or null if there is none
        properties.forEach((property, file) -> logger.info(property + " parent: " + file.getParent()));
        // Returns the number of milliseconds since the epoch (number of milliseconds since
        // 12 a.m. UTC on January 1, 1970) when the file was last modified
        properties.forEach((property, file) -> logger.info(property + " last modified: " + new Date(file.lastModified())));
        // Retrieves a list of files within a directory
        File pathIo = properties.get(IO_PATH);
        logger.info(pathIo + " children: " + deepToString(pathIo.listFiles()));
        // Creates the directory named by this path
        File nestedDir = new File(pathIo, "nested-dir");
        if (nestedDir.mkdir()) {
            logger.info(nestedDir + " created: " + nestedDir.getAbsolutePath());
        }
        // Creates the directory named by this path including any nonexistent parent directories
        File doubleNestedDir = new File(pathIo, "double/nested/dir");
        if (doubleNestedDir.mkdirs()) {
            logger.info(nestedDir + " created: " + doubleNestedDir.getAbsolutePath());
        }
        // Renames the file or directory denoted by this path to dest and returns true only if successful
        File firstZooFile = properties.get(ZOO_1);
        File secondZooFile = properties.get(ZOO_2);
        if (firstZooFile.renameTo(secondZooFile)) {
            logger.info(firstZooFile + " was renamed to " + secondZooFile.getAbsolutePath());
            logger.info(ZOO_1 + (firstZooFile.exists() ? " exists" : " does not exist"));
            logger.info(ZOO_2 + (secondZooFile.exists() ? " exists" : " does not exist"));
        }
        // Retrieves the number of bytes in the file
        properties.forEach((property, file) -> logger.info(property + " size: " + byteCountToDisplaySize(file.length())));
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
