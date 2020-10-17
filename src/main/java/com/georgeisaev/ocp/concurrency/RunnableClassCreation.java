package com.georgeisaev.ocp.concurrency;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.stream.DoubleStream;

import static java.time.LocalDateTime.now;

/**
 * Even though Runnable is a functional interface, many classes implement it directly, as {@link CalculateAverage} here.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class RunnableClassCreation {

    private static final Logger logger = Logger.getLogger(RunnableClassCreation.class);
    private static final Random RANDOM_NUMBER_GENERATOR = new Random();
    private static final int ELEMENT_NUMBERS = Integer.MAX_VALUE / 1_00;
    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) {
        logger.info(String.format("Start the main thread %s", now()));
        for (int i = 0; i < THREAD_COUNT; i++) {
            new CalculateAverage(generateRandomScores()).run();
        }
        logger.info(String.format("End the main thread %s", now()));
    }

    private static double[] generateRandomScores() {
        return DoubleStream
                .generate(RANDOM_NUMBER_GENERATOR::nextDouble)
                .limit(ELEMENT_NUMBERS)
                .toArray();
    }

}

class CalculateAverage implements Runnable {

    private static final Logger logger = Logger.getLogger(CalculateAverage.class);
    private final double[] scores;

    public CalculateAverage(double... scores) {
        this.scores = scores;
    }

    public void run() {
        DoubleSummaryStatistics summaryStatistics = Arrays.stream(this.scores).summaryStatistics();
        logger.info(String.format("Statistics calculated at %s", now()));
        logger.info(summaryStatistics);
    }

}