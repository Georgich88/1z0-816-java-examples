package com.georgeisaev.ocp.concurrency.collections.copyonwrite;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import static java.util.Objects.hash;

/**
 * Illustrate a concurrent access to data stored in {@code CopyOnWrite} collections.
 *
 * @param <T> type of the library index
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class Shelf<T> {

    static final int MAX_SHELF_SIZE = 1 << 4; // 16 books

    // Fields

    private final int capacity;
    private T index;
    private CopyOnWriteArrayList<Book> books;
    private CopyOnWriteArraySet<Author> authors;

    // Constructors

    public Shelf(T index) {
        this(index, MAX_SHELF_SIZE);
    }

    public Shelf(T index, int capacity) {
        this.index = index;
        this.capacity = capacity;
        this.books = new CopyOnWriteArrayList<>();
        this.authors = new CopyOnWriteArraySet<>();
    }

    // Modification methods

    void putOn(Book bookToAdd) {
        books.add(bookToAdd);
        authors.addAll(bookToAdd.getAuthors());
    }

    List<Book> takeFrom(String name) {
        List<Book> removedBooks =
                books.stream()
                        .filter(book -> name.equals(book.toString()))
                        .collect(Collectors.toList());
        Set<Author> authorsToRemove =
                removedBooks.stream()
                        .map(Book::getAuthors)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet());
        authors.removeAll(authorsToRemove);
        return removedBooks;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Shelf.class.getSimpleName() + "[", "]")
                .add("index=" + index)
                .add("books=" + books)
                .toString();
    }

}

class Book {

    private final String name;
    private final Set<Author> authors;

    protected Book(String name, String... authorNames) {
        this.name = name;
        this.authors = Set.of(Arrays.stream(authorNames)
                .map(Author::new)
                .toArray(Author[]::new));
    }

    protected Book(String name, Author... authors) {
        this.name = name;
        this.authors = Set.of(authors);
    }

    public String getName() {
        return name;
    }

    public Set<Author> getAuthors() {
        return Set.copyOf(authors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return name.equals(book.name) &&
                authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        return hash(name, authors);
    }

    @Override
    public String toString() {
        return name;
    }

}

class Author {

    private final String name;

    public Author(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

}