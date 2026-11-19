package library;

import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;
    private final int year;

    public Book(String isbn, String title, int year) {
        this.isbn = Objects.requireNonNull(isbn, "isbn");
        this.title = Objects.requireNonNull(title, "title");
        this.year = year;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public int getYear() { return year; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() { return isbn.hashCode(); }

    @Override
    public String toString() {
        return title + " (" + year + "), ISBN: " + isbn;
    }
}
//hello
