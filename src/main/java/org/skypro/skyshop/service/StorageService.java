package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Book;
import org.skypro.skyshop.model.product.Clothing;
import org.skypro.skyshop.model.product.Electronic;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productMap = new HashMap<>();
    private final Map<UUID, Article> articleMap = new HashMap<>();

    public StorageService() {
        initStorage();
    }

    private void initStorage() {
        Product book = new Book(UUID.randomUUID(), "Гарри Поттер", 500.0, "Дж.К. Роулинг");
        Product clothing = new Clothing(UUID.randomUUID(), "Футболка", 999.0, "L");
        Product electronic = new Electronic(UUID.randomUUID(), "Смартфон", 30000.0, 24);

        Article article1 = new Article(UUID.randomUUID(), "Как выбрать смартфон", "Полезные советы по выбору техники");
        Article article2 = new Article(UUID.randomUUID(), "Модные футболки", "Обзор летних моделей");

        productMap.put(book.getId(), book);
        productMap.put(clothing.getId(), clothing);
        productMap.put(electronic.getId(), electronic);

        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(productMap.values());
        all.addAll(articleMap.values());
        return all;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
