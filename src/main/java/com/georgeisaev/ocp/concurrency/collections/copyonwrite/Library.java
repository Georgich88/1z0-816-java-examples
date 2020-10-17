package com.georgeisaev.ocp.concurrency.collections.copyonwrite;

import com.github.javafaker.Faker;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Stores books by shelves and served by library workers {@linkplain Librarian}.
 *
 * @param <T> type of the library index
 * @author Georgy Isaev
 * @version 1.0.0
 */
public abstract class Library<T> {

    private static final int MIN_THREAD_NUMBER = 4;
    private static final Logger logger = Logger.getLogger(Library.class);
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    // Fields

    private ConcurrentHashMap<T, Shelf<T>> shelves;

    // Constructors

    public Library() {
        this.shelves = new ConcurrentHashMap<>();
    }

    private static String generateFullName() {
        return FAKER.name().fullName();
    }

    public static void main(String[] args) {

        final int availableCores = Math.min(MIN_THREAD_NUMBER, getRuntime().availableProcessors());
        ExecutorService service = null;
        Library<Character> library = new Library<>() {
            @Override
            protected Character generateBookIndex(Book book) {
                return book.getName().charAt(0);
            }
        };
        try {
            service = (newFixedThreadPool(availableCores));
            ExecutorService finalService = service;
            IntStream.range(0, availableCores)
                    .forEach(i -> {
                        Library<Character>.Librarian librarian = library.new Librarian(generateFullName());
                        finalService.submit(() -> IntStream.range(0, RANDOM.nextInt(Shelf.MAX_SHELF_SIZE))
                                .forEach(j -> {
                                    librarian.putOnRandom();
                                    logger.info(library);
                                })
                        );
                        finalService.submit(() -> IntStream.range(0, RANDOM.nextInt(Shelf.MAX_SHELF_SIZE))
                                .forEach(j -> {
                                    librarian.takeFromRandom();
                                    logger.info(library);
                                }));
                    });
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
        logger.info(library);
    }

    protected abstract T generateBookIndex(Book book);

    // Object inherited methods

    @Override
    public String toString() {
        return new StringJoiner(", ", Library.class.getSimpleName() + "[", "]")
                .add("shelves=" + shelves)
                .toString();
    }

    // Library worker functionality

    class Librarian {

        private final String name;

        public Librarian(String name) {
            this.name = name;
        }

        void putOnRandom() {
            com.github.javafaker.Book bookFakeInfo = FAKER.book();
            Book book = new Book(bookFakeInfo.title(), bookFakeInfo.author());
            T index = generateBookIndex(book);
            Shelf<T> shelf = shelves.computeIfAbsent(index, Shelf::new);
            shelf.putOn(book);
        }

        void takeFromRandom() {
            com.github.javafaker.Book bookFakeInfo = FAKER.book();
            Book book = new Book(bookFakeInfo.title(), bookFakeInfo.author());
            T index = generateBookIndex(book);
            Shelf<T> shelf = shelves.computeIfAbsent(index, Shelf::new);
            shelf.takeFrom(book.getName());
        }

    }

}
