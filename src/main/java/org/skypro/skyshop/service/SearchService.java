package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        System.out.println(">>> SearchService.search called with: " + pattern);

        Collection<Searchable> allItems = storageService.getAllSearchables();
        System.out.println(">>> Searchable items in storage: " + allItems.size());

        List<SearchResult> results = allItems.stream()
                .filter(s -> s.getSearchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());

        System.out.println(">>> Found matching: " + results.size());
        return results;
    }

}
