package com.georgeisaev.ocp.nio.files.attributes;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FilesAttributes {

    private static final Logger logger = Logger.getLogger(FilesAttributes.class);
    private static final String LINE_SEPARATOR = "---------------------";
    private static final String FILE_PATH_CANINE_FUR = "src/main/resources/nio/canine/fur.jpg";
    private static final String FILE_PATH_CANINE_TYPES = "src/main/resources/nio/canine/types.txt";
    private static final String FILE_PATH_COYOTE = "src/main/resources/nio/canine/coyote";

    public static void main(String[] args) throws IOException {
        determinePathType();
        checkFileAccessibility();
        printSize();
        defineLastModifiedTime();
        retrieveBasicFileAttributes();
        useFileAttributeView();
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

    /**
     * The size returned by this method represents the conceptual size of the data, and this may differ from the
     * actual size on the persistent storage device.
     *
     * @throws IOException
     */
    private static void printSize() throws IOException {
        logger.info(LINE_SEPARATOR);
        logger.info("The Files class includes a method to determine the size of the file in bytes.");
        logger.info("Files.size = " + Files.size(Paths.get(FILE_PATH_CANINE_FUR)));
        logger.info("Files.size = " + Files.size(Paths.get(FILE_PATH_CANINE_TYPES)));
    }

    /**
     * Most operating systems support tracking a last-modified date/time value with each file. Some applications
     * use this to determine when the file's contents should be read again. In the majority of circumstances, it is a
     * lot faster to check a single file metadata attribute than to reload the entire contents of the file.
     *
     * @throws IOException
     */
    private static void defineLastModifiedTime() throws IOException {
        logger.info(LINE_SEPARATOR);
        final Path path = Paths.get("src/main/resources/nio/rabbit/food.jpg");
        logger.info(Files.getLastModifiedTime(path).toMillis());
    }

    /**
     * The BasicFileAttributes class includes many values with the same name as the attribute methods in the Files
     * class.
     * The advantage of using this method, though, is that all of the attributes are retrieved at once.
     *
     * @throws IOException
     */
    private static void retrieveBasicFileAttributes() throws IOException {
        logger.info(LINE_SEPARATOR);
        Path path = Paths.get("src/main/resources/nio/turtles/sea.txt");
        BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
        logger.info("Is a directory ? " + data.isDirectory());
        logger.info("Is a regular file ? " + data.isRegularFile());
        logger.info("Is a symbolic link ? " + data.isSymbolicLink());
        logger.info("Size (in bytes):" + data.size());
        logger.info("Last modified: " + data.lastModifiedTime());
    }

    /**
     * We can use the updatable view to increment a file's last modified date/time value by 10,000 milliseconds,
     * or 10 seconds.
     *
     * @throws IOException
     */
    private static void useFileAttributeView() throws IOException {
        logger.info(LINE_SEPARATOR);
        // Read file attributes
        Path path = Paths.get("src/main/resources/nio/turtles/sea.txt");
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes attributes = view.readAttributes();
        logger.info("Last modified time before: " + attributes.lastModifiedTime());
        // Modify file last modified time
        FileTime lastModifiedTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 10_000);
        view.setTimes(lastModifiedTime, null, null);
        logger.info("Last modified time after: " + view.readAttributes().lastModifiedTime());
    }

}
