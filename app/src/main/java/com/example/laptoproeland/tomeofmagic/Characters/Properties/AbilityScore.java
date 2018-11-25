package com.example.laptoproeland.tomeofmagic.Characters.Properties;

import java.io.Serializable;
import java.util.ArrayList;

public class AbilityScore implements Serializable {

    private String name;
    private int score;
    private int modifier;
    private int save;

    private int maximum = 20;
    private int minimum = 3;

    ArrayList<Talent> talents;

    public AbilityScore(String name, int score) {
        talents = new ArrayList<>();

//        if(name.equals("Strength")){
//            talents.add(new Talent("Athletics", "[AthleticsDescription]"));
//
//        } else if (name.equals("Dexterity")){
//            talents.add(new Talent("Acrobatics", "[AcrobaticsDescription]"));
//            talents.add(new Talent("Sleight of Hand", "[Sleight of HandDescription]"));
//            talents.add(new Talent("Stealth", "[StealthDescription]"));
//
//        } else if (name.equals("Intelligence")){
//            talents.add(new Talent("Arcana", "[ArcanaDescription]"));
//            talents.add(new Talent("History", "[HistoryDescription]"));
//            talents.add(new Talent("Investigation", "[InvestigationDescription]"));
//            talents.add(new Talent("Nature", "[NatureDescription]"));
//            talents.add(new Talent("Religion", "[ReligionDescription]"));
//
//        } else if (name.equals("Wisdom")){
//            talents.add(new Talent("Animal Handling", "[Animal HandlingDescription]"));
//            talents.add(new Talent("Insight", "[InsightDescription]"));
//            talents.add(new Talent("Medicine", "[MedicineDescription]"));
//            talents.add(new Talent("Perception", "[PerceptionDescription]"));
//            talents.add(new Talent("Survival", "[SurvivalDescription]"));
//
//        } else if (name.equals("Charisma")){
//            talents.add(new Talent("Deception", "[DeceptionDescription]"));
//            talents.add(new Talent("Intimidation", "[IntimidationDescription]"));
//            talents.add(new Talent("Performance", "[PerformanceDescription]"));
//            talents.add(new Talent("Persuasion", "[PersuasionDescription]"));
//        }

        this.name = name;
        this.score = score;
        this.modifier = ((score-10)/2);
        this.save = modifier;
    }

    public void ModifyScore(int increment){

        int newScore = score += increment;

        if(newScore >= minimum && newScore <= maximum) {
            score = newScore;
            this.modifier = ((score - 10) / 2);
            this.save = modifier;
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getModifier() {
        return modifier;
    }

    public int getSave() {
        return save;
    }
}
