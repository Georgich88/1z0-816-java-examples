package com.georgeisaev.ocp.nio.exam.paths;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CreatePathExample {

    public static void main(String[] args) {
        differentWaysToCreatePath();
    }

    /**
     * Which of the following correctly create Path instances? (Choose all that apply.)
     * A. new Path("jaguar.txt")
     * B. FileSystems.getDefault() .getPath("puma.txt")
     * C. Path.get("cats","lynx.txt")
     * D. new java.io.File("tiger.txt").toPath()
     * E. new FileSystem().getPath("lion")F. Paths.getPath("ocelot.txt")
     * G. Path.of(Path.of(".").toUri())
     */
    private static void differentWaysToCreatePath() {
        // Path pathA = new Path("jaguar.txt");
        Path pathB = FileSystems.getDefault().getPath("puma.txt");
        // Path pathC = Path.get("cats","lynx.txt");
        Path pathD = new java.io.File("tiger.txt").toPath();
        // Path pathE = new FileSystem().getPath("lion");
        // Path pathF = Paths.getPath("ocelot.txt");
        Path pathG = Path.of(Path.of(".").toUri());
    }

}
