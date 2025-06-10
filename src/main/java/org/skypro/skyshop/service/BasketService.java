package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID productId) {
        System.out.println("Ищем продукт с ID: " + productId);
        System.out.println("Все доступные продукты:");
        storageService.getAllProducts().forEach(product ->
                System.out.println(" - " + product.getId() + " | " + product.getName()));

        Product product = storageService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с таким ID не найден"));

        productBasket.addProduct(product.getId());
    }

    public UserBasket getUserBasket() {
        return new UserBasket(
                productBasket.getProducts().entrySet().stream()
                        .map(entry -> new BasketItem(
                                storageService.getProductById(entry.getKey()).orElseThrow(),
                                entry.getValue()))
                        .collect(Collectors.toList())
        );
    }
}
