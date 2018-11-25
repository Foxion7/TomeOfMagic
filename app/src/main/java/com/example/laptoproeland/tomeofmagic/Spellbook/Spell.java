package com.example.laptoproeland.tomeofmagic.Spellbook;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class Spell implements Serializable {

    private String name;
    private int level;
    private String school;
    private boolean ritual;
    private String components;
    private String duration, casting_time, range, description;
    private ArrayList<String> classes, sources, tags;

    public Spell(String name, int level, String school, boolean ritual, String components, String casting_time, String duration, String range, String description, ArrayList<String> classes, ArrayList<String> sources, ArrayList<String> tags) {
        this.name = name;
        this.level = level;
        this.school = school;
        this.ritual = ritual;
        this.components = components;
        this.casting_time = casting_time;
        this.duration = duration;
        this.range = range;
        this.description = description;
        this.classes = classes;
        this.sources = sources;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public String getLevel() {
        return String.valueOf(level);
    }

    public String getComponents() {
        return components;
    }

    public String getCasting_time() {
        if(casting_time.contains("|")){
            String[] separated = casting_time.split("\\|");
            return separated[0];
        }
        return casting_time;
    }

    public String getDuration() {
        return duration;
    }

    public boolean getConcentration(){
        return duration.contains("Concentration");
    }

    public String getRange() {
        return range;
    }

    public String getDescription() {
        String[] separated = description.split("At Higher Levels.");
        return separated[0];
    }

    public String getAt_higher_levels() {

        if (description.contains("At Higher Levels.")) {
            String[] separated = description.split("At Higher Levels.");
            if (separated[1] != null) {
                return separated[1];
            }
        }
        return "No additional effects.";
    }

    public String getClasses() {
        String returnClasses = "";

        if (classes != null) {
            for (String item : classes) {
                if (!returnClasses.equals("")){
                    returnClasses += ", ";
                }
                returnClasses += item;
            }
        }
        return returnClasses;
    }

    public boolean isRitual() {
        return ritual;
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public String getSourcesText() {
        String returnSources = "";

        if (sources != null) {
            for (String item : sources) {
                if (!returnSources.equals("")){
                    returnSources += ", ";
                }
                returnSources += item;
            }
        }
        return returnSources;
    }
}
