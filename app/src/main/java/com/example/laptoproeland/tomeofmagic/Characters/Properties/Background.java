package com.example.laptoproeland.tomeofmagic.Characters.Properties;

import java.util.ArrayList;

public class Background {

    String name;
    String description;
    ArrayList<Talent> talents;

    public Background(String name, String description, ArrayList<Talent> talents) {
        this.name = name;
        this.description = description;
        this.talents = talents;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Talent> getTalents() {
        return talents;
    }

    public void setTalents(ArrayList<Talent> talents) {
        this.talents = talents;
    }

    public void addTalent(Talent talent){
        talents.add(talent);
    }

    @Override
    public String toString() {

        String s = "Background{" +
                "name='" + name + '\'' +
                ", description='" + description;

        for(Talent talent : talents){
            s += talent.getName();
        }

        return s;
    }
}
