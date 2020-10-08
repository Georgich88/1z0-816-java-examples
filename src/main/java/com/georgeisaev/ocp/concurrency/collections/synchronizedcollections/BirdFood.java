package com.georgeisaev.ocp.concurrency.collections.synchronizedcollections;

import org.apache.log4j.Logger;

import java.util.*;

import static com.georgeisaev.ocp.concurrency.collections.synchronizedcollections.BirdFactory.ofName;

interface Bird {

}

/**
 * Illustrates that unlike the concurrent collections, the synchronized collections also throw an exception if they
 * are modified within an iterator by a single thread.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class BirdFood {

    private static final Logger logger = Logger.getLogger(BirdFood.class);

    public static void main(String[] args) {
        Map<Bird, Integer> foodData = new HashMap<>();
        foodData.put(ofName("penguin"), 100);
        foodData.put(ofName("flamingo"), 100);
        Map<Bird, Integer> syncFoodData = Collections.synchronizedMap(foodData);
        try {
            for (Bird bird : syncFoodData.keySet()) {
                syncFoodData.remove(bird);
            }
        } catch (Exception e) {
            logger.error("", e);
        }

    }

}

final class BirdFactory {

    private static final Map<String, Bird> CACHE;

    static {
        CACHE = new WeakHashMap<>();
    }

    private BirdFactory() {
    }

    // Factory methods

    public static synchronized Bird ofName(String name) {
        return CACHE.computeIfAbsent(name, BirdImp::new);
    }

}

class BirdImp implements Bird {

    // Fields

    private final String name;

    // Constructors

    protected BirdImp(String name) {
        this.name = name;
    }

    // Object inherited methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BirdImp)) return false;
        BirdImp bird = (BirdImp) o;
        return Objects.equals(name, bird.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

}