package com.georgeisaev.ocp.concurrency.collections.skiplists;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.georgeisaev.ocp.concurrency.collections.skiplists.Animal.createBird;
import static com.georgeisaev.ocp.concurrency.collections.skiplists.Animal.createMammal;
import static java.util.Comparator.comparing;
import static java.util.Objects.hash;

/**
 * Illustrative examples of the SkipList classes.
 * <p>
 * The SkipList classes, {@linkplain ConcurrentSkipListSet} and {@linkplain ConcurrentSkipListMap}, are concurrent
 * versions of their sorted counterparts, {@linkplain TreeSet} and {@linkplain TreeMap}, respectively.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class SkipListExample {

    private static final Logger logger = Logger.getLogger(SkipListExample.class);

    public static void main(String[] args) {
        populateAnimalGarden();
    }

    private static void populateAnimalGarden() {
        Set<Animal> gardenAnimals = new ConcurrentSkipListSet<>(comparing(Animal::getName));
        final List<Animal> mammals = List.of(createMammal("rabbit"), createMammal("gopher"), createMammal("gopher"),
                createMammal("tiger"));
        final List<Animal> birds = List.of(createBird("sparrow"), createBird("parrot"));
        ExecutorService service = null;
        try {
            service = Executors.newCachedThreadPool();
            service.submit(() -> gardenAnimals.addAll(mammals));
            service.submit(() -> gardenAnimals.addAll(birds));
            Callable<String> descriptionDetailer = () -> gardenAnimals.stream()
                    .map(Animal::toString)
                    .collect(Collectors.joining(", "));
            String details = service.invokeAny(List.of(descriptionDetailer));
            logger.info(details);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("", e);
        } finally {
            if (service != null) service.shutdown();
        }
    }

}

class Animal {

    private static final Map<String, Animal> animalByNameCache = new WeakHashMap<>();
    private final String name;
    private final String animalClass;

    // Factory methods

    private Animal(String name, String animalClass) {
        this.name = name;
        this.animalClass = animalClass;
    }

    // Constructors

    static Animal createMammal(String name) {
        return createAnimal(name, "mammals");
    }

    static Animal createBird(String name) {
        return createAnimal(name, "birds");
    }

    private static synchronized Animal createAnimal(String name, String animalClass) {
        validateAnimalName(name);
        if (animalByNameCache.containsKey(name)) {
            return animalByNameCache.get(name);
        }
        Animal newMammal = new Animal(name, animalClass);
        animalByNameCache.put(name, newMammal);
        return newMammal;
    }

    // Validation

    private static void validateAnimalName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Animal name should be specified");
        }
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public String getAnimalClass() {
        return animalClass;
    }

    // Object inherited methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getName().equals(animal.getName()) &&
                getAnimalClass().equals(animal.getAnimalClass());
    }

    @Override
    public int hashCode() {
        return hash(getName(), getAnimalClass());
    }

    @Override
    public String toString() {
        return name;
    }

}