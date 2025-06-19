package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

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

        Collection<SearchResult> results = searchService.search("Футбол");

        assertEquals(0, results.size());
    }

    @Test
    void shouldReturnEmptyListWhenNothingMatches() {
        Searchable obj = mock(Searchable.class);
        when(obj.getSearchTerm()).thenReturn("Смартфон");
        when(obj.getId()).thenReturn(UUID.randomUUID());
        when(obj.getContentType()).thenReturn("product");

        when(storageService.getAllSearchables()).thenReturn(List.of(obj));

        Collection<SearchResult> results = searchService.search("Футбол");

        assertEquals(0, results.size());
    }

    @Test
    void shouldReturnMatchWhenNameContainsQuery() {
        Searchable obj = mock(Searchable.class);
        when(obj.getSearchTerm()).thenReturn("Футболка мужская");
        when(obj.getId()).thenReturn(UUID.randomUUID());
        when(obj.getContentType()).thenReturn("product");

        when(storageService.getAllSearchables()).thenReturn(List.of(obj));

        Collection<SearchResult> results = searchService.search("футбол");

        assertEquals(1, results.size());
        SearchResult result = results.iterator().next();
        assertEquals("Футболка мужская", result.getName());
    }
}
