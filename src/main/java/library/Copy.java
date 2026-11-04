package library;

import java.util.Objects;

public class Copy {
    private final String inventoryNumber;
    private final Book book;
    private boolean available = true;

    public Copy(String inventoryNumber, Book book) {
        this.inventoryNumber = Objects.requireNonNull(inventoryNumber, "inventoryNumber");
        this.book = Objects.requireNonNull(book, "book");
    }

    public String getInventoryNumber() { return inventoryNumber; }
    public Book getBook() { return book; }
    public boolean isAvailable() { return available; }

    public void borrow() {
        if (!available) throw new IllegalStateException("Copy is not available");
        available = false;
    }

    public void giveBack() {
        if (available) throw new IllegalStateException("Copy is already available");
        available = true;
    }
}
