package com.georgeisaev.ocp.nio.exam.paths;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathGetNameCountMethod {

    private static final Logger logger = Logger.getLogger(PathGetNameCountMethod.class);

    public static void main(String[] args) throws IOException {
        printGetNameCount(".");
    }

    private static void printGetNameCount(String pathTo) {
        final Path path = Paths.get(pathTo).normalize();
        int count = 0;
        for (int i = 0; i < path.getNameCount(); ++i) {
            count++;
        }
        logger.info(count);
    }

}
