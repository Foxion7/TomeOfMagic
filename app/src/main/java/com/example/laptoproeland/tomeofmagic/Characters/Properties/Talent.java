package com.example.laptoproeland.tomeofmagic.Characters.Properties;

import java.io.Serializable;

public class Talent implements Serializable {

    private String name;
    private Type type;
    private String description;
    private boolean proficiency = false;
    private boolean halfProficiency = false;

    public enum Type {
        Skill, Equipment, Language
    }

    public Talent(String name, String description, Type type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getProficiency() {
        return proficiency;
    }

    public Type getType() {
        return type;
    }

    public int getProficiencyBonus(int basicProficiencyBonus) {
        if(proficiency){
            return basicProficiencyBonus;
        } else if (halfProficiency){
            return basicProficiencyBonus / 2;
        }
        return 0;
    }

    public void setProficiency(boolean proficient) {
        this.proficiency = proficient;
    }

    public void setProficiency(String proficiency) {
        if("Full".equals(proficiency)){
            this.proficiency = true;
            this.halfProficiency = false;
        } else if ("Half".equals(proficiency)){
            this.proficiency = true;
            this.halfProficiency = true;
        } else {
            this.proficiency = false;
            this.halfProficiency = false;
        }
    }
}
