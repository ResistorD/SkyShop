package org.skypro.skyshop.model.product;

import java.util.UUID;

public class Book extends Product {
    private final String author;

    public Book(UUID id, String name, double price, String author) {
        super(id, name, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
