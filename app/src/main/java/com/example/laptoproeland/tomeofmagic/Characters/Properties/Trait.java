package com.example.laptoproeland.tomeofmagic.Characters.Properties;

import java.io.Serializable;

public class Trait implements Serializable {

    private String title;
    private String description;

    public Trait(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}