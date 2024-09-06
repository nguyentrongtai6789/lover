package com.example.bookstorebackend.model;

import java.util.Map;

public class Translation {
    private Map<String, String> translations;

    public Translation(Map<String, String> translations) {
        this.translations = translations;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }
}