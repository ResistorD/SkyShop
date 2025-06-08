package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "article";
    }

}

