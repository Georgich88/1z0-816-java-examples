package com.georgeisaev.ocp.concurrency.collections.copyonwrite;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static java.util.Objects.hash;

interface ShelfObject {

}

public class Shelf<T> {

    private static final int MAX_SHELF_SIZE = 1 << 4; // 16 books
    private final int capacity;
    private T index;
    private CopyOnWriteArrayList<Book> books;
    private CopyOnWriteArraySet<Author> authors;

    public Shelf(T index) {
        this(index, MAX_SHELF_SIZE);
    }

    public Shelf(T index, int capacity) {
        this.index = index;
        this.capacity = capacity;
        this.books = new CopyOnWriteArrayList<>();
        this.authors = new CopyOnWriteArraySet<>();
    }

    void putOn(String name, String... authorNames) {
        Book bookToAdd = new Book(name, authorNames);
        books.add(new Book(name, authorNames));
        authors.addAll(bookToAdd.getAuthors());
    }

    void takeFrom(String name, String... authorNames) {

    }

}

class Book implements ShelfObject {

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