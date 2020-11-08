package com.georgeisaev.ocp.io.streams.bytechar;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class ByteCharacterStreamExample {

    // Logging constants
    private static final Logger logger = Logger.getLogger(ByteCharacterStreamExample.class);
    private static final String MSG_ERR_CANNOT_WRITE = "Cannot write into a file";
    private static final String MSG_ERR_WRITING_CHARACTER = "Writing character %s with code %d";
    private static final String MSG_ERR_WRITING_BYTES = "Writing byte %d with code %d";
    // Filepath constants
    private static final URL URL_IO_RESOURCES = ByteCharacterStreamExample.class.getClassLoader().getResource("io");
    private static final String FILEPATH_IO;
    private static final String INPUT_FILE_NAME;
    private static final String OUTPUT_FILE_NAME;

    static {
        assert URL_IO_RESOURCES != null;
        FILEPATH_IO = URL_IO_RESOURCES.getPath();
        INPUT_FILE_NAME = FILEPATH_IO + "/byte-char/input.txt";
        OUTPUT_FILE_NAME = FILEPATH_IO + "/byte-char/output.txt";
    }

    public static void main(String[] args) {
        // Character streams
        List<Integer> chars = charIoStreamExample();
        // Byte streams
        List<Integer> bytes = byteIoStreamExample();
        logger.info("chars, size=" + chars.size() + ", " + chars);
        logger.info("bytes, size=" + bytes.size() + ", " + bytes);
    }

    /**
     * The byte streams are primarily used to work with binary data, such as an image or executable file, while
     * character streams are used to work with text files. Since the byte stream classes can write all types of
     * binary data, including strings, it follows that the character stream classes aren't strictly necessary.
     * <p>
     * There are advantages, though, to using the character stream classes, as they are specifically focused on
     * managing character and string data.
     */
    private static List<Integer> charIoStreamExample() {
        List<Integer> readCharacters = new ArrayList<>();
        try (FileReader reader = new FileReader(INPUT_FILE_NAME);
             FileWriter writer = new FileWriter(OUTPUT_FILE_NAME)) {
            int character;
            while ((character = reader.read()) != -1) {
                readCharacters.add(character);
                writer.write(character);
                logger.info(format(MSG_ERR_WRITING_CHARACTER, (char) character, character));
            }
        } catch (IOException e) {
            logger.error(MSG_ERR_CANNOT_WRITE, e);
        }
        return readCharacters;
    }

    /**
     * The byte streams are primarily used to work with binary data, such as an image or executable file, while
     * character streams are used to work with text files. Since the byte stream classes can write all
     * types of binary data, including strings, it follows that the character stream classes aren't strictly
     * necessary.
     * <p>
     * There are advantages, though, to using the character stream classes, as they are specifically
     * focused on managing character and string data. For example, you can use a Writer class to output a
     * String value to a file without necessarily having to worry about the underlying character encoding of
     * the file.
     */
    private static List<Integer> byteIoStreamExample() {
        List<Integer> readBytes = new ArrayList<>();
        try (FileInputStream reader = new FileInputStream(INPUT_FILE_NAME);
             FileOutputStream writer = new FileOutputStream(OUTPUT_FILE_NAME)) {
            int byteVar;
            while ((byteVar = reader.read()) != -1) {
                readBytes.add(byteVar);
                writer.write(byteVar);
                logger.info(format(MSG_ERR_WRITING_BYTES, (byte) byteVar, byteVar));
            }
        } catch (IOException e) {
            logger.error(MSG_ERR_CANNOT_WRITE, e);
        }
        return readBytes;
    }

}
