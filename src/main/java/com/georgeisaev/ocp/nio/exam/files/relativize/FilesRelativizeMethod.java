package com.georgeisaev.ocp.nio.exam.files.relativize;

import org.apache.log4j.Logger;

import java.nio.file.Path;

public class FilesRelativizeMethod {

    private static final Logger logger = Logger.getLogger(FilesRelativizeMethod.class);

    public static void main(String[] args) {
        relativizeMethodSignature();
    }

    /**
     * What is the output of the following code?
     * <p>
     * 4: var path = Path.of("/user/./root","../kodiacbear.txt");
     * 5: path.normalize().relativize("/lion");
     * 6: System.out.println(path);
     */
    private static void relativizeMethodSignature() {
        var path = Path.of("/user/./root", "../kodiacbear.txt");
        var normalizedPath = path.normalize();
        // If we replace `normalizedPath.relativize(Path.of("/lion"))` to `normalizedPath.relativize("/lion")
        // it won't compile because of relativize method doesn't have signature relativize(String)
        var normalizedRelativePath = normalizedPath.relativize(Path.of("/lion"));
        logger.info(path); // path is immutable, so it will print exactly what was defined in var path
        logger.info(normalizedPath);
        logger.info(normalizedRelativePath);
        logger.info(Path.of("/lion").isAbsolute());
    }

}
