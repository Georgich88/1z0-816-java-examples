package com.georgeisaev.ocp.concurrency.synchronizedblocks;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.georgeisaev.ocp.concurrency.synchronizedblocks.SheepManager.printDaysWork;

/**
 * Solution illustrates addition of the {@code synchronized} modifier to any instance method to synchronize
 * automatically on the object itself.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class SynchronizingOnMethods {

    private static final int THREAD_NUMBERS = 20;
    private static final int SHIP_NUMBERS = 10;

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(THREAD_NUMBERS);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < SHIP_NUMBERS; i++) {
                service.submit(manager::incrementAndReport);
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
        printDaysWork();
    }

}

class SheepManager {

    private static final Logger logger = Logger.getLogger(SheepManager.class);

    private int sheepCount;

    public static synchronized void printDaysWork() {
        logger.info("Finished work");
    }

    synchronized void incrementAndReport() {
        sheepCount++;
        logger.info(sheepCount + " sheep");
    }

}
