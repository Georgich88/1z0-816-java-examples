package com.georgeisaev.ocp.nio.exam.files.funcional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FilesReadAllLinesMethod {

    public static void main(String[] args) throws IOException {
        readAllLines(Path.of("src/main/resources/nio/fox/food-schedule.csv"));
    }

    /** Assuming the /fox/food-schedule.csv file exists with the specified contents, what is the expected
     output of calling printData() on it?

     * @param path
     * @throws IOException
     */
    static void readAllLines(Path path) throws IOException {
        Files.readAllLines(path) // r1
                .stream() // missing in question
                .flatMap(p -> Stream.of(p.split(","))) // r2
                .map(q -> q.toUpperCase()) // r3
                .forEach(System.out::println);
    }
}
