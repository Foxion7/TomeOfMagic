package com.example.laptoproeland.tomeofmagic.Spellbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SpellAdapter extends ArrayAdapter<Spell> implements Filterable {

    public List<Spell> filteredSpellsList;
    private List<Spell> allSpellsList = new ArrayList<>();
    private SpellFilter spellFilter;

    public SpellAdapter(Context context, ArrayList<Spell> spells) {
        super(context, 0, spells);
        this.allSpellsList.addAll(spells);
        this.filteredSpellsList = spells;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Spell spell = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.spell_listitem, parent, false);
        }

        TextView tvName = view.findViewById(R.id.name);
        TextView tvLevel = view.findViewById(R.id.level);
        TextView tvSchool = view.findViewById(R.id.school);
        ImageView ivVComponent = view.findViewById(R.id.ivVComponent);
        ImageView ivSComponent = view.findViewById(R.id.ivSComponent);
        ImageView ivMComponent = view.findViewById(R.id.ivMComponent);
        ImageView ivConcentration = view.findViewById(R.id.ivConcentration);
        ImageView ivRitual = view.findViewById(R.id.ivRitual);

        if (!spell.getComponents().contains("V")){
            ivVComponent.setVisibility(INVISIBLE);
        } else {
            ivVComponent.setVisibility(VISIBLE);
        }
        if (!spell.getComponents().contains("S")){
            ivSComponent.setVisibility(INVISIBLE);
        } else {
            ivSComponent.setVisibility(VISIBLE);
        }
        if (!spell.getComponents().contains("M")){
            ivMComponent.setVisibility(INVISIBLE);
        } else {
            ivMComponent.setVisibility(VISIBLE);
        }
        if (!spell.getDuration().contains("Concentration")){
            ivConcentration.setVisibility(View.GONE);
        } else {
            ivConcentration.setVisibility(VISIBLE);
        }
        if (!spell.isRitual()){
            ivRitual.setVisibility(View.GONE);
        } else {
            ivRitual.setVisibility(VISIBLE);
        }

        tvName.setText(spell.getName());

        if(spell.getLevel().equals("0")){
            tvLevel.setText("Cantrip");
        } else {
            tvLevel.setText("Level " + spell.getLevel());
        }
        tvSchool.setText(spell.getSchool());

        return view;
    }

    private class SpellFilter extends Filter {

        Spell spell;
        String selectedClass;
        String selectedLevel;
        String query;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = allSpellsList;
                results.count = allSpellsList.size();
            } else {
                ArrayList<Spell> tempList = new ArrayList<>();

                String[] constraintString = constraint.toString().split("\\|");
                selectedClass = constraintString[0];
                selectedLevel = constraintString[1];
                if(constraintString.length == 3){
                    query = constraintString[2];
                } else {
                    query = "";
                }

                for (Spell spell : allSpellsList) {
                    this.spell = spell;
                    boolean filter = true;
                    if(FilterOnClass() && FilterOnLevel() &&
                            FilterOnSchool() && FilterOnSource() &&
                            FilterOnConcentration() && FilterOnRitual() &&
                            FilterOnComponent() && FilterOnComponentCost() &&
                            FilterOnCastingTime() && FilterOnRange() && FilterOnDuration()){
                        filter = false;
                        if(query != null){
                            if(!spell.getName().toLowerCase().contains(query.toLowerCase())){
                                filter = true;
                            }
                        }
                    }
                    if(!filter){
                        tempList.add(spell);
                    }
                }
                results.values = tempList;
                results.count = tempList.size();
            }
            return results;
        }

        public boolean FilterOnClass(){
            return (AppData.getClassSelected().equals("All classes") || spell.getClasses().contains(AppData.getClassSelected()));
        }

        public boolean FilterOnLevel(){
            int level = java.lang.Character.getNumericValue(AppData.getLevelSelected().charAt(0));
            return (AppData.getLevelSelected().equals("All levels") || (Integer.parseInt(spell.getLevel()) == 0 && AppData.getLevelSelected().equals("Cantrip")) || Integer.parseInt(spell.getLevel()) == level);
        }

        public boolean FilterOnSchool(){
            if(AppData.getSchoolsSelected() != null) {
                return (AppData.getSchoolsSelected().contains(spell.getSchool()));
            }
            return true;
        }

        public boolean FilterOnSource(){
            if(AppData.getSourcesSelected() != null) {
                for (String source : spell.getSources()) {
                    if (AppData.getSourcesSelected().contains(source)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }

        public boolean FilterOnConcentration(){
            if(AppData.getConcentration().equals("All")){
                return true;
            } else if (AppData.getConcentration().equals("Yes")){
                return (spell.getConcentration());
            } else if (AppData.getConcentration().equals("No")){
                return (!spell.getConcentration());
            }
            return false;
        }

        public boolean FilterOnRitual(){
            if(AppData.getRitual().equals("All")){
                return true;
            } else if (AppData.getRitual().equals("Yes")){
                return (spell.isRitual());
            } else if (AppData.getRitual().equals("No")){
                return (!spell.isRitual());
            }
                return false;
            }

        public boolean FilterOnComponent(){

            if(AppData.getComponents().equals("All")||
                (AppData.isVerbalComponent() == spell.getComponents().contains("V")) &&
                (AppData.isSomaticComponent() == spell.getComponents().contains("S")) &&
                (AppData.isMaterialComponent() == spell.getComponents().contains("M"))) {
                return true;
            }
            return false;
        }

        public boolean FilterOnComponentCost(){

            if(AppData.getComponentCost().equals("Yes") && (spell.getComponents().contains("consumes") || spell.getComponents().contains("consumes"))){
                return true;
            } else if (AppData.getComponentCost().equals("No") && !(spell.getComponents().contains("consumes") && spell.getComponents().contains("consumes"))){
                return true;
            } else if (AppData.getComponentCost().equals("All")){
                return true;
            }
            return false;
        }

        public boolean FilterOnCastingTime(){
            return (AppData.getCastingTimeSelected().equals("Any") || spell.getCasting_time().contains(AppData.getCastingTimeSelected()));
        }

        public boolean FilterOnRange(){
            return (AppData.getRangeSelected().equals("Any") || spell.getRange().contains(AppData.getRangeSelected()));
        }

        public boolean FilterOnDuration(){
            return (AppData.getDurationSelected().equals("Any") || spell.getDuration().contains(AppData.getDurationSelected()));
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredSpellsList.clear();
            filteredSpellsList.addAll((List<Spell>) results.values);
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        if (spellFilter == null)
            spellFilter = new SpellFilter();
        return spellFilter;
    }
}
