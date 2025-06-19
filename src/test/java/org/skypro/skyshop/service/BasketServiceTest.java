package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    private BasketService basketService;
    private ProductBasket productBasket;
    private StorageService storageService;

    private final UUID validId = UUID.randomUUID();
    private final UUID invalidId = UUID.randomUUID();
    private final Product dummyProduct = mock(Product.class);

    @BeforeEach
    void setUp() {
        productBasket = mock(ProductBasket.class);
        storageService = mock(StorageService.class);
        basketService = new BasketService(productBasket, storageService);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidId));
    }

    @Test
    void shouldAddProductWhenExists() {
        when(storageService.getProductById(validId)).thenReturn(Optional.of(dummyProduct));
        when(dummyProduct.getId()).thenReturn(validId); // <-- вот это важно

        basketService.addProduct(validId);

        verify(productBasket, times(1)).addProduct(validId);
    }


    @Test
    void shouldReturnEmptyUserBasketIfBasketIsEmpty() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertNotNull(result);
        assertTrue(result.getItems().isEmpty());
        assertEquals(0, result.getTotal());
    }

    @Test
    void shouldReturnUserBasketWithItems() {
        when(productBasket.getProducts()).thenReturn(Map.of(validId, 2));
        when(storageService.getProductById(validId)).thenReturn(Optional.of(dummyProduct));
        when(dummyProduct.getPrice()).thenReturn(100.0);

        UserBasket result = basketService.getUserBasket();

        assertEquals(1, result.getItems().size());
        assertEquals(200.0, result.getTotal());
    }
}
