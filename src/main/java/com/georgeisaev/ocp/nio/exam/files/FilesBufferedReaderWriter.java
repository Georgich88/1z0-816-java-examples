package com.georgeisaev.ocp.nio.exam.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesBufferedReaderWriter {


    public static void main(String[] args) throws Exception {
        duplicateFile(Path.of("src/main/resources/nio/birds/condor.txt"),
                Path.of("src/main/resources/nio/not_exist"));
    }

    static void duplicateFile(Path m, Path x) throws Exception {
        var r = Files.newBufferedReader(m);
        var w = Files.newBufferedWriter(x, StandardOpenOption.APPEND);
        String currentLine = null;
        while ((currentLine = r.readLine()) != null) {
            w.write(currentLine);
        }
    }

}
