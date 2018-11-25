package com.example.laptoproeland.tomeofmagic.Characters.Properties;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable {

    private String name;
    private String lore;
    private ArrayList<Race> subraces;
    private ArrayList<Trait> traits;

    public Race(String name, String lore, ArrayList<Race> subraces, ArrayList<Trait> traits) {
        this.name = name;
        this.lore = lore;
        this.subraces = subraces;
        this.traits = traits;
    }

    public Race(String name, String lore, ArrayList<Trait> traits) {
        this.name = name;
        this.lore = lore;
        this.subraces = null;
        this.traits = traits;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public Race getSubrace(String subraceName){
        if(subraces != null) {
            for (Race subrace : subraces) {
                if (subraceName.equals(subrace.getName())) {
                    return subrace;
                }
            }
        }
        return null;
    }

    public ArrayList<Race> getSubraces() {
        return subraces;
    }

    public ArrayList<Trait> getTraits() {
        return traits;
    }

    public Trait getTrait(String title) {
        for(Trait trait : traits){
            if (title.equals(trait.getTitle())){
                return trait;
            }
        }
        return null;
    }

    public void addTrait(Trait trait){
        traits.add(trait);
    }
}
