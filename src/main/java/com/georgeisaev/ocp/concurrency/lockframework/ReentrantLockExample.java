package com.georgeisaev.ocp.concurrency.lockframework;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Solution illustrates main methods of {@linkplain ReentrantLock} class from Lock framework.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class ReentrantLockExample {

    private static final String MSG_INFO_LOCK_OBTAINED = "Lock obtained, entering protected code";
    private static final String MSG_ERROR_UNABLE_TO_ACQUIRE_LOCK = "Unable to acquire lock, doing something else";
    private static final Logger logger = Logger.getLogger(ReentrantLockExample.class);

    public static void main(String[] args) throws InterruptedException {
        printMessage();
        printMessage(10);
    }

    /**
     * Illustrates {@linkplain ReentrantLock#lock()} method.
     *
     * @param lock a lock object to lock
     */
    public static void printMessage(Lock lock) {
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Illustrates {@linkplain ReentrantLock#tryLock()} method.
     *
     * @throws InterruptedException
     */
    public static void printMessage() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        new Thread(() -> printMessage(lock)).start();
        Thread.sleep(1_000);
        if (lock.tryLock()) {
            try {
                logger.info(MSG_INFO_LOCK_OBTAINED);
            } finally {
                lock.unlock();
            }
        } else {
            logger.error(MSG_ERROR_UNABLE_TO_ACQUIRE_LOCK);
        }
    }

    /**
     * Illustrates {@linkplain ReentrantLock#tryLock(long, TimeUnit)} method.
     *
     * @throws InterruptedException
     */
    public static void printMessage(long seconds) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        new Thread(() -> printMessage(lock)).start();
        if (lock.tryLock(seconds, SECONDS)) {
            try {
                logger.info(MSG_INFO_LOCK_OBTAINED);
            } finally {
                lock.unlock();
            }
        } else {
            logger.error(MSG_ERROR_UNABLE_TO_ACQUIRE_LOCK);
        }
    }

}
