package com.example.laptoproeland.tomeofmagic.Characters;

import com.example.laptoproeland.tomeofmagic.Characters.Properties.AbilityScore;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Race;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Laptop Roeland on 13-11-2017.
 */

public class Character implements Serializable {

    private String name;
    private String title;
    private Race race;
    private ArrayList<Class> classes;
    private int proficiencyBonus = 2;
    private int totalLevel = 0;

    public AbilityScore strength;
    public AbilityScore dexterity;
    public AbilityScore constitution;
    public AbilityScore intelligence;
    public AbilityScore wisdom;
    public AbilityScore charisma;

    public Character(String name, Race race, Class c, int strengthScore, int dexterityScore, int constitutionScore, int intelligenceScore, int wisdomScore, int charismaScore) {

        classes = new ArrayList<>();

        this.strength = new AbilityScore("Strength", strengthScore);
        this.dexterity = new AbilityScore("Dexterity", dexterityScore);
        this.constitution = new AbilityScore("Constitution", constitutionScore);
        this.intelligence = new AbilityScore("Intelligence", intelligenceScore);
        this.wisdom = new AbilityScore("Wisdom", wisdomScore);
        this.charisma = new AbilityScore("Charisma", charismaScore);

        this.name = name;
        this.race = race;
        this.classes.add(c);
    }

    public void levelUp(Class c) {
        if (classes != null) {
            for (int i = 0; i < classes.size(); i++) {
                if (classes.get(i).getName().equals(c.getName())) {
                    classes.get(i).levelUp();
                } else {
                    this.classes.add(c);
                }
            }
        }
        this.totalLevel = getTotalLevels();

        if(totalLevel <= 4) {
            proficiencyBonus = 2;

        }else if (totalLevel <= 8){
            proficiencyBonus = 3;

        } else if (totalLevel <= 13){
            proficiencyBonus = 4;

        } else if (totalLevel <= 16){
            proficiencyBonus = 5;

        } else if (totalLevel <= 20){
            proficiencyBonus = 6;
        }
    }

    public int getTotalLevels(){
        int totalLevels = 0;
        if (classes != null) {
            for (int i = 0; i < classes.size(); i++) {
                totalLevels += classes.get(i).getLevel();
            }
        }
        return totalLevels;
    }

    public String getClassesAndLevels(){
        String characterClasses = "";
        for(int i = 0; i < classes.size(); i++){
            characterClasses +=  classes.get(i).getName() + " " + classes.get(i).getLevel();
            if(i < classes.size()-1){
                characterClasses += ", ";
            }
        }
        return characterClasses;
    }

    public String getName(){
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public void SetRace(String name){
        if (name.equals("Aarakocra")){

        }
    }
}
