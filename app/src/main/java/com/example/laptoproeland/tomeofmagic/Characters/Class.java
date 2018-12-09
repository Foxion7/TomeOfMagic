package com.example.laptoproeland.tomeofmagic.Characters;

import java.io.Serializable;

/**
 * Created by Laptop Roeland on 13-11-2017.
 */

public class Class implements Serializable{

    private String name;
    private String description;
    private int hitdie;
    private String subclass;
    private int level;


    public Class(String name) {
        this(name, null, 1);
    }

    public Class(String name, int level) {
        this(name, null, level);
    }

    public Class(String name, String subclass, int level) {
        this.name = name;
        this.subclass = subclass;
        this.level = level;
    }

    public void levelUp(){
        level++;
    }

    public int getLevel(){
        return level;
    }

    public String getName() {
        return name;
    }

    public String getSubclass() {
        return subclass;
    }
}