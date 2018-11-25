package com.example.laptoproeland.tomeofmagic;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.laptoproeland.tomeofmagic.Characters.Properties.Background;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Race;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Talent;
import com.example.laptoproeland.tomeofmagic.Spellbook.Spell;

import java.util.ArrayList;

public class AppData extends Application {

    private static ArrayList<Spell> spells = new ArrayList<>();
    private static ArrayList<Character> characters = new ArrayList<>();
    private static ArrayList<Race> races = new ArrayList<>();
    private static ArrayList<Background> backgrounds = new ArrayList<>();
    private static ArrayList<Talent> talents = new ArrayList<>();

    private static String query = "";

    private static String classSelected = "All classes";
    private static String levelSelected = "All levels";

    private static ArrayList<String> schoolsSelected;
    private static ArrayList<String> sourcesSelected;

    private static String concentration = "All";
    private static String ritual = "All";
    private static String components = "All";

    private static boolean verbalComponent = true;
    private static boolean somaticComponent = true;
    private static boolean materialComponent = true;

    private static String componentCost = "All";

    private static String castingTimeSelected = "Any";
    private static String rangeSelected = "Any";
    private static String durationSelected = "Any";

    public static ArrayList<String> getSchoolsSelected() {
        return schoolsSelected;
    }

    public static void setSchoolsSelected(ArrayList<String> school){
        AppData.schoolsSelected = school;
    }

    public static ArrayList<String> getSourcesSelected() {
        return sourcesSelected;
    }

    public static void setSourcesSelected(ArrayList<String> sourcesSelected) {
        AppData.sourcesSelected = sourcesSelected;
    }

    public static String getConcentration() {
        return concentration;
    }

    public static void setConcentration(String concentration) {
        AppData.concentration = concentration;
    }

    public static String getRitual() {
        return ritual;
    }

    public static void setRitual(String ritual) {
        AppData.ritual = ritual;
    }

    public static String getComponents() {
        return components;
    }

    public static void setComponents(String components) {
        AppData.components = components;
    }

    public static boolean isVerbalComponent() {
        return verbalComponent;
    }

    public static void setVerbalComponent(boolean verbalComponent) {
        AppData.verbalComponent = verbalComponent;
    }

    public static boolean isSomaticComponent() {
        return somaticComponent;
    }

    public static void setSomaticComponent(boolean somaticComponent) {
        AppData.somaticComponent = somaticComponent;
    }

    public static boolean isMaterialComponent() {
        return materialComponent;
    }

    public static void setMaterialComponent(boolean materialComponent) {
        AppData.materialComponent = materialComponent;
    }

    public static String getComponentCost() {
        return componentCost;
    }

    public static void setComponentCost(String componentCost) {
        AppData.componentCost = componentCost;
    }

    public static String getCastingTimeSelected() {
        return castingTimeSelected;
    }

    public static void setCastingTimeSelected(String castingTimeSelected) {
        AppData.castingTimeSelected = castingTimeSelected;
    }

    public static String getRangeSelected() {
        return rangeSelected;
    }

    public static void setRangeSelected(String rangeSelected) {
        AppData.rangeSelected = rangeSelected;
    }

    public static String getDurationSelected() {
        return durationSelected;
    }

    public static void setDurationSelected(String durationSelected) {
        AppData.durationSelected = durationSelected;
    }

    public static ArrayList<Spell> getSpells() {
        return spells;
    }

    public static void setSpells(ArrayList<Spell> spells) {
        AppData.spells = spells;
    }

    public static boolean gotSpells(){
        return !spells.isEmpty();
    }

    public static String getClassSelected() {
        return classSelected;
    }

    public static void setClassSelected(String classSelected) {
        AppData.classSelected = classSelected;
    }

    public static String getLevelSelected() {
        return levelSelected;
    }

    public static void setLevelSelected(String levelSelected) {
        AppData.levelSelected = levelSelected;
    }

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String query) {
        AppData.query = query;
    }

    public static ArrayList<Character> getCharacters() {
        return characters;
    }

    public static void addCharacter(Character character) {
        characters.add(character);
    }

    public static Race getRace(String name){
        if(races != null){
            for(Race race : races){
                if(name.equals(race.getName())){
                    return race;
                } else {
                    if(race.getSubraces() != null) {
                        for (Race subrace : race.getSubraces()) {
                            if (name.equals(subrace.getName())) {
                                return subrace;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void setRaces(ArrayList<Race> races){
        AppData.races = races;
    }

    public static void setBackgrounds(ArrayList<Background> backgrounds){
        AppData.backgrounds = backgrounds;
    }

    public static Background getBackground(String name){
        if(backgrounds != null){
            for(Background background : backgrounds){
                if(name.equals(background.getName())){
                    return background;
                }
            }
        }
        return null;
    }

    public static void setTalents(ArrayList<Talent> talents){
        AppData.talents = talents;
    }

    public static Talent getTalent(String name){
        if(talents != null){
            for(Talent talent : talents){
                if(name.equals(talent.getName())){
                    return talent;
                }
            }
        }
        return null;
    }

    public static Talent getTalent(String name, Talent.Type type){
        if(talents != null){
            for(Talent talent : talents){
                if(name.equals(talent.getName()) && talent.getType() == type){
                    return talent;
                }
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getSettings() {
        return "AppData: \n" +
                "Total amount of spells: " + spells.size() + "\n" +
                "Current searchquery: " + query + "\n" +
                "Class: " + classSelected + "\n" +
                "Level: " + levelSelected + "\n" +
                "Schools: " + schoolsSelected + "\n" +
                "Sources: " + sourcesSelected + "\n";
    }
}