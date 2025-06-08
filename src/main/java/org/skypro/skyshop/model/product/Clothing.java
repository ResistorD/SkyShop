package org.skypro.skyshop.model.product;

import java.util.UUID;

public class Clothing extends Product {
    private final String size;

    public Clothing(UUID id, String name, double price, String size) {
        super(id, name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
