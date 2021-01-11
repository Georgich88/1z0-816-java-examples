package com.georgeisaev.ocp.nio.exam.paths;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathNormalizeMethod {

    private static final Logger logger = Logger.getLogger(PathNormalizeMethod.class);

    public static void main(String[] args) throws IOException {
        normalizeAndCompareDifferentPaths();
    }

    private static void normalizeAndCompareDifferentPaths() throws IOException {
        var p1 = Path.of("src/main/resources/nio/lizard", ".")
                .resolve(Path.of("walking.txt"));
        var p2 = new File("src/main/resources/nio/lizard/././actions/../walking.txt")
                .toPath();
        logger.info(Files.isSameFile(p1, p2));
        logger.info(p1.equals(p2));
        logger.info(p1.normalize().equals(p2.normalize()));
    }

}
