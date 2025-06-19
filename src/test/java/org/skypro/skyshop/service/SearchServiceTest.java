package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = Mockito.mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    void shouldReturnEmptyListWhenStorageIsEmpty() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        List<SearchResult> results = searchService.search("Футбол");

        assertEquals(0, results.size());
    }

    @Test
    void shouldReturnEmptyListWhenNothingMatches() {
        Searchable obj = () -> "Смартфон";
        when(storageService.getAllSearchables()).thenReturn(List.of(obj));

        List<SearchResult> results = searchService.search("Футбол");

        assertEquals(0, results.size());
    }

    @Test
    void shouldReturnMatchWhenNameContainsQuery() {
        Searchable obj = () -> "Футболка мужская";
        when(storageService.getAllSearchables()).thenReturn(List.of(obj));

        List<SearchResult> results = searchService.search("футбол");

        assertEquals(1, results.size());
        assertEquals("Футболка мужская", results.get(0).name());
    }
}
