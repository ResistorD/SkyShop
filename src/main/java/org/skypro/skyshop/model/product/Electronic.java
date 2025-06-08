package org.skypro.skyshop.model.product;

import java.util.UUID;

public class Electronic extends Product {
    private final int warrantyMonths;

    public Electronic(UUID id, String name, double price, int warrantyMonths) {
        super(id, name, price);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}
