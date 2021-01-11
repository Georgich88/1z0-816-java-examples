package com.georgeisaev.ocp.nio.exam.paths;

import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ResolveMethod {

    private static final Logger logger = Logger.getLogger(ResolveMethod.class);

    public static void main(String[] args) {
        resolvePaths();
    }

    private static void resolvePaths() {
        Path path1 = Path.of("/pets/../cat.txt");
        Path path2 = Paths.get("./dog.txt");
        logger.info(path1.resolve(path2));
        logger.info(path2.resolve(path1));
    }

}
