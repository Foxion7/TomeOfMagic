package com.example.laptoproeland.tomeofmagic.Characters.Properties;

import java.io.Serializable;

public class ClassTrait extends Trait implements Serializable {

    private int level;

    public ClassTrait(String title, String description, int level) {
        super(title, description);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
