package com.georgeisaev.ocp.concurrency.cyclicbarrier;

import org.apache.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

import static java.lang.Math.min;
import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.apache.log4j.Logger.getLogger;

/**
 * Solution illustrates mechanics of locking barriers using {@linkplain CyclicBarrier} class.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class LionPenManager {

    private static final int MIN_THREAD_NUMBER = 4;
    private static final String MSG_INFO_PEN_CLEANED = "*** Pen Cleaned!";
    private static final String MSG_INFO_REMOVE_LIONS = "Removing lions";
    private static final String MSG_INFO_ADD_LIONS = "Adding lions";
    private static final String MSG_INFO_CLEAR_PEN = "Cleaning the pen";
    private static final Logger logger = getLogger(LionPenManager.class);

    public static void main(String[] args) {
        // Number of available threads should be at least as large as CyclicBarrier limit value.
        // Here we use available processor number as thread number, but at least four threads.
        final int availableCores = min(MIN_THREAD_NUMBER, getRuntime().availableProcessors());
        ExecutorService service = null;
        try {
            service = newFixedThreadPool(availableCores);
            final LionPenManager manager = new LionPenManager();
            final CyclicBarrier afterLionsRemoval = new CyclicBarrier(availableCores);
            final CyclicBarrier afterPenCleaning = new CyclicBarrier(availableCores,
                    () -> logger.info(MSG_INFO_PEN_CLEANED));
            for (int i = 0; i < availableCores; i++) {
                service.submit(() -> manager.performTask(afterLionsRemoval, afterPenCleaning));
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    // Pseudo modification methods

    private void removeLions() {
        logger.info(MSG_INFO_REMOVE_LIONS);
    }

    private void cleanPen() {
        logger.info(MSG_INFO_CLEAR_PEN);
    }

    private void addLions() {
        logger.info(MSG_INFO_ADD_LIONS);
    }

    /**
     * Performs a lion pen task controlling the result of the same stage worker via {@linkplain CyclicBarrier}
     * limiter objects.
     *
     * @param afterLionsRemoval a limit of removal tasks to be performed before moving on the cleaning stage
     * @param afterPenCleaning  a limit of cleaning tasks to be performed before moving on the add lions stage
     */
    public void performTask(CyclicBarrier afterLionsRemoval, CyclicBarrier afterPenCleaning) {
        try {
            removeLions();
            afterLionsRemoval.await();
            cleanPen();
            afterPenCleaning.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            logger.error(e);
        }
    }

}
