package com.georgeisaev.ocp.concurrency.collections.skiplists;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.georgeisaev.ocp.concurrency.collections.skiplists.Animal.createBird;
import static com.georgeisaev.ocp.concurrency.collections.skiplists.Animal.createMammal;
import static com.georgeisaev.ocp.concurrency.collections.skiplists.AnimalNutrition.createAnimalNutrition;
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
        rainForestAnimalDiet();
    }

    private static void rainForestAnimalDiet() {
        Map<Organism, Nutrition> rainForestAnimalDiet = new ConcurrentSkipListMap<>(comparing(Organism::getName));
        rainForestAnimalDiet.put(createMammal("koala"), createAnimalNutrition("bamboo", 100));
        rainForestAnimalDiet.forEach((key, value) -> logger.info(key + "-" + value));
    }

    private static void populateAnimalGarden() {
        Set<Organism> gardenAnimals = new ConcurrentSkipListSet<>(comparing(Organism::getName));
        final List<Organism> mammals = generateMammals();
        final List<Organism> birds = generateBirds();
        ExecutorService service = null;
        try {
            service = Executors.newCachedThreadPool();
            service.submit(() -> gardenAnimals.addAll(mammals));
            service.submit(() -> gardenAnimals.addAll(birds));
            Callable<String> descriptionDetailer = () -> gardenAnimals.stream()
                    .map(Organism::toString)
                    .collect(Collectors.joining(", "));
            String details = service.invokeAny(List.of(descriptionDetailer));
            logger.info(details);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private static List<Organism> generateBirds() {
        return List.of(createBird("sparrow"), createBird("parrot"));
    }

    private static List<Organism> generateMammals() {
        return List.of(createMammal("rabbit"), createMammal("gopher"), createMammal("gopher"),
                createMammal("tiger"));
    }

}

/**
 * Represent animals from different classes.
 */
class Animal implements Organism {

    public static final String MSG_ERROR_ANIMAL_NAME_IS_EMPTY = "Animal name should be specified";
    private static final Map<String, Animal> animalByNameCache = new WeakHashMap<>();
    private final String name;
    private final String animalClass;

    // Constructors

    private Animal(String name, String animalClass) {
        this.name = name;
        this.animalClass = animalClass;
    }

    // Factory methods

    static Organism createMammal(String name) {
        return createAnimal(name, "mammals");
    }

    static Organism createBird(String name) {
        return createAnimal(name, "birds");
    }

    private static synchronized Organism createAnimal(String name, String animalClass) {
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
            throw new IllegalArgumentException(MSG_ERROR_ANIMAL_NAME_IS_EMPTY);
        }
    }

    // Getters and setters

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKingdom() {
        return "Animals";
    }

    @Override
    public String getOrganismClass() {
        return animalClass;
    }

    // Object inherited methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getName().equals(animal.getName()) &&
                getOrganismClass().equals(animal.getOrganismClass());
    }

    @Override
    public int hashCode() {
        return hash(getName(), getOrganismClass());
    }

    @Override
    public String toString() {
        return name;
    }

}

class AnimalNutrition implements Nutrition {

    private final String name;
    private final long calories;

    // Constructors

    private AnimalNutrition(String name) {
        this(name, 0L);
    }

    private AnimalNutrition(String name, long calories) {
        this.name = name;
        this.calories = calories;
    }

    // Factory methods

    public static Nutrition createAnimalNutrition(String name, long calories) {
        return new AnimalNutrition(name, calories);
    }

    // Getters and setters

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getCalories() {
        return calories;
    }

    // Object inherited methods

    @Override
    public String toString() {
        return name + " " + calories + " cal.";
    }

}