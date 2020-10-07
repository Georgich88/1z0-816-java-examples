# Lock Framework

A synchronized block supports only a limited set of functionality.
The Concurrency API includes the Lock interface that is conceptually similar to using the synchronized keyword, but with a lot more functionalities.

## ReentrantLock Interface
The `ReentrantLock` class provides the same features as a `synchronized` block, while adding a number of improvements.
* Requesting a lock without blocking
* Requesting to request a lock while blocking for a specified amount of time
* A lock can be created with a fairness property, in which the lock is granted to threads in the order it was requested.

The Lock interface includes four methods:

|Method|Description|
|---|---|
|`void lock()`|Requests a lock and blocks until lock is acquired|
|`void unlock()`|Releases a lock|
|`boolean tryLock()`|Requests a lock and returns immediately. Returns a boolean indicating whether the lock was successfully acquired|
|`boolean tryLock(long,TimeUnit)` |Requests a lock and blocks up to the specified time until lock is required. Returns a boolean indicating whether the lock was successfully acquired|

### Acquire a Lock
While the ReentrantLock class allows you to wait for a lock, it so far suffers from the same problem as a synchronized block. A thread could end up waiting forever to obtain a lock.
```
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
```

### tryLock()
The `tryLock()` method will attempt to acquire a lock and immediately return a boolean result indicating whether the lock was obtained. Unlike the `lock()` method, it does not wait if another thread already holds the lock.
```
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
```

### tryLock(long,TimeUnit)
The Lock interface includes an overloaded version of tryLock(long,TimeUnit) that acts like a hybrid of `lock()` and `tryLock()`.
```
/**
 * Illustrates {@linkplain ReentrantLock#tryLock(long, TimeUnit)} method.
 *
 * @throws InterruptedException
 */
public static void printMessage(long seconds) throws InterruptedException {
    final Lock lock = new ReentrantLock();
    new Thread(() -> printMessage(lock)).start();
    if (lock.tryLock(seconds, TimeUnit.SECONDS)) {
        try {
            logger.info(MSG_INFO_LOCK_OBTAINED);
        } finally {
            lock.unlock();
        }
    } else {
        logger.error(MSG_ERROR_UNABLE_TO_ACQUIRE_LOCK);
    }
}
```