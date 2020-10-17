package com.georgeisaev.ocp.concurrency.threadingproblems.deadlock;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.georgeisaev.ocp.concurrency.threadingproblems.deadlock.NutritionFactory.produceFood;
import static com.georgeisaev.ocp.concurrency.threadingproblems.deadlock.NutritionFactory.produceWater;

interface NutritionSource {

    String consume();

}

/**
 * Illustrates deadlock problem.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class Fox {

    public static final int MOVING_TIME_MILLIS = 1000;
    private static final Logger logger = Logger.getLogger(Fox.class);

    public static void main(String[] args) {
        Fox foxy = new Fox();
        Fox tails = new Fox();
        NutritionSource water = produceWater();
        NutritionSource food = produceFood();
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.consume(food, water));
            service.submit(() -> tails.consume(water, food));
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    public void consume(NutritionSource firstNutrition, NutritionSource secondNutrition) {
        synchronized (firstNutrition) {
            logger.info(firstNutrition.consume());
            logger.info(move());
            synchronized (secondNutrition) {
                logger.info(secondNutrition.consume());
            }
        }
    }

    public String move() {
        try {
            Thread.sleep(MOVING_TIME_MILLIS);
        } catch (InterruptedException e) {
            logger.error("Cannot move", e);
        }
        return "Moving";
    }

}

final class NutritionFactory {

    private NutritionFactory() {

    }

    public static NutritionSource produceFood() {
        return new Food();
    }

    public static NutritionSource produceWater() {
        return new Water();
    }

}

class Food implements NutritionSource {

    @Override
    public String consume() {
        return "Eating";
    }

}

class Water implements NutritionSource {

    @Override
    public String consume() {
        return "Drinking";
    }

}
