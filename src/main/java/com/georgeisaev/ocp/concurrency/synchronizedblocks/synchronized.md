#Improving Access with Synchronized Blocks

##Synchronizing on Methods

We can establish a monitor using `synchronized(this)` around the body of the method.
Java provides a more convenient compiler enhancement for doing so.
We can add the `synchronized` modifier to any instance method to synchronize automatically on the object itself.

For example,
```
private void incrementAndReport() {
    synchronized(this) {
        logger.info((++sheepCount)+" ");
    }
}
private synchronized void incrementAndReport() {
    logger.info((++sheepCount) + " ");
}
```

We can also apply the synchronized modifier to static methods to synchronize on the class object.
For example,
```
public static void printDaysWork() {
    synchronized(SheepManager.class) {
        logger.info("Finished work");
    }
}
public static synchronized void printDaysWork() {
    logger.info("Finished work");
}
```