package com.georgeisaev.ocp.nio.exam.files.maniputlations;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesMoveMethod {

    private static final Logger logger = Logger.getLogger(FilesMoveMethod.class);

    public static void main(String[] args) throws IOException {
        atomicMoveFilenoFollowingLinks();
    }

    private static void atomicMoveFilenoFollowingLinks() throws IOException {
        Files.move(Path.of("monkey.txt"), Paths.get("/animals"),
                StandardCopyOption.ATOMIC_MOVE,
                LinkOption.NOFOLLOW_LINKS);
    }

}
