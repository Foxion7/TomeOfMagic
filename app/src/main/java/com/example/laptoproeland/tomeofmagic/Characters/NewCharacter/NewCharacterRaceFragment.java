package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Race;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Trait;
import com.example.laptoproeland.tomeofmagic.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCharacterRaceFragment extends Fragment {


    Spinner raceSpinner;
    Spinner subraceSpinner;
    Spinner raceChoiceSpinner;

    Race subrace = null;

    TextView tvSubrace;

    TextView tvRaceName;
    TextView tvRaceTraitDescription;

    TextView tvSubraceName;
    TextView tvSubraceTraitDescription;

    TextView tvRaceChoice;

    View view;

    public NewCharacterRaceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new_character_race, container, false);

        tvRaceName = view.findViewById(R.id.tvRaceName);

        tvRaceTraitDescription = view.findViewById(R.id.tvRaceTraitDescription);
        tvRaceTraitDescription.setMovementMethod(new ScrollingMovementMethod());

        tvSubraceName = view.findViewById(R.id.tvSubraceName);
        tvSubraceTraitDescription = view.findViewById(R.id.tvSubraceTraits);

        tvRaceChoice = view.findViewById(R.id.tvRaceChoice);

        // Base race setup
        raceSpinner = view.findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> raceSpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.races_array, android.R.layout.simple_spinner_item);
        raceSpinAdapter.setDropDownViewResource(R.layout.new_character_spinner_listitem);
        raceSpinner.setAdapter(raceSpinAdapter);

        // Subrace setup
        subraceSpinner = view.findViewById(R.id.subrace_spinner);
        tvSubrace = view.findViewById(R.id.tvSubrace);

        // Optional choice setup
        raceChoiceSpinner = view.findViewById(R.id.race_choice_spinner);
        ArrayAdapter<CharSequence> choiceSpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.subrace_variant_tiefling_array, android.R.layout.simple_spinner_item);
        choiceSpinAdapter.setDropDownViewResource(R.layout.new_character_spinner_listitem);
        raceChoiceSpinner.setAdapter(choiceSpinAdapter);
        raceChoiceVisible(false);

        updateSubraceSpinner(R.array.dragonborn_ancestry_array);
        updateTraitTexts();

        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Only races with subraces are within if-statements.
                if(raceSpinner.getSelectedItem().toString().equals("Dragonborn")){
                    updateSubraceSpinner(R.array.dragonborn_ancestry_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Dwarf")){
                    updateSubraceSpinner(R.array.subrace_dwarf_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Elf")){
                    updateSubraceSpinner(R.array.subrace_elf_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Gnome")){
                    updateSubraceSpinner(R.array.subrace_gnome_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Half-elf")){
                    updateSubraceSpinner(R.array.subrace_halfelf_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Halfling")){
                    updateSubraceSpinner(R.array.subrace_halfling_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Human")){
                    updateSubraceSpinner(R.array.subrace_human_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Tiefling")){
                    updateSubraceSpinner(R.array.subrace_tiefling_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Genasi")){
                    updateSubraceSpinner(R.array.subrace_genasi_array);
                } else if(raceSpinner.getSelectedItem().toString().equals("Aasimar")){
                    updateSubraceSpinner(R.array.subrace_aasimar_array);
                } else {
                    subraceSpinner.setVisibility(View.INVISIBLE);
                    tvSubrace.setVisibility(View.INVISIBLE);

                    tvSubraceName.setVisibility(GONE);
                    tvSubraceTraitDescription.setVisibility(GONE);
                }

                // Tiefling variant options visible or not.
                if(raceSpinner.getSelectedItem().toString().equals("Tiefling")){
                    tvRaceChoice.setText("Variant: ");
                    raceChoiceVisible(true);
                } else {
                    raceChoiceVisible(false);
                }
                updateTraitTexts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        subraceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTraitTexts();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        raceChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTraitTexts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return view;
    }

    public void updateSubraceSpinner(int subraceArray){

        if("Dragonborn".equals(raceSpinner.getSelectedItem().toString())){
            tvSubrace.setText("Ancestry:");
        } else {
            tvSubrace.setText("Subrace:");
        }

        if(tvSubrace.getVisibility() == View.INVISIBLE && subraceSpinner.getVisibility() == View.INVISIBLE){
            subraceSpinner.setVisibility(VISIBLE);
            tvSubrace.setVisibility(VISIBLE);
        }

        ArrayAdapter<CharSequence> subraceSpinAdapter = ArrayAdapter.createFromResource(getContext(),
                subraceArray, android.R.layout.simple_spinner_item);
        subraceSpinAdapter.notifyDataSetChanged();
        subraceSpinAdapter.setDropDownViewResource(R.layout.new_character_spinner_listitem);
        subraceSpinner.setAdapter(subraceSpinAdapter);
    }

    public void updateTraitTexts(){

        boolean containsReplacement = false;
        Race race = AppData.getRace(raceSpinner.getSelectedItem().toString());

        if(subraceSpinner.getVisibility() == VISIBLE) {
            subrace = AppData.getRace(subraceSpinner.getSelectedItem().toString());
        }

        if(race != null) {
            tvRaceName.setText(race.getName());
            String traitsText = "";

            for(int i = 0; i < race.getTraits().size(); i++){
                // Tries to check for existing replacements.
                Trait replacement = getReplacement(race.getTraits().get(i));
                if(replacement == null){
                    // If no replacement is found, uses original trait.
                    traitsText += "<b>" + race.getTraits().get(i).getTitle() + ".</b> ";
                    traitsText += race.getTraits().get(i).getDescription() + "<br><br>";
                } else {
                    // If replacement is found, uses replacement.
                    traitsText += "<b>" + trimReplacement(replacement.getTitle()) + " (Replaced).</b> ";
                    traitsText += replacement.getDescription() + "<br><br>";
                    containsReplacement = true;
                }
            }
            if(traitsText.length() > 0) {
                traitsText = traitsText.substring(0, traitsText.length() - 8);
            }
            tvRaceTraitDescription.setText(Html.fromHtml(traitsText));
        }

        if(subrace != null){
            tvSubraceName.setVisibility(VISIBLE);
            tvSubraceTraitDescription.setVisibility(VISIBLE);
            tvSubraceName.setText(subrace.getName());
            String traitsText = "";

            for(int i = 0; i < subrace.getTraits().size(); i++){
                if(!subrace.getTraits().get(i).getTitle().contains("Replace:")) {

                    // Only used when all traits are the same as those of the base race, like with Asmodeus tieflings.
                    if(!"".equals(subrace.getTraits().get(i).getTitle())) {
                        traitsText += "<b>" + subrace.getTraits().get(i).getTitle() + ".</b> ";
                        traitsText += subrace.getTraits().get(i).getDescription();
                    } else if (raceChoiceSpinner.getSelectedItemPosition() == 0){
                        traitsText += subrace.getTraits().get(i).getDescription();
                    }
                    traitsText += "<br><br>";
                } else {
                    containsReplacement = true;
                }
            }

            if(traitsText.length() > 0) {
                traitsText = traitsText.substring(0, traitsText.length() - 8);
                if(containsReplacement){
                    traitsText = "<i>Some traits from this subrace have replaced the base race traits.</i><br><br>" + traitsText;
                }
            } else {
                traitsText += "<i>All traits from this subrace have replaced the base race traits.</i>";
            }
            tvSubraceTraitDescription.setText(Html.fromHtml(traitsText));
        } else {
            tvSubraceName.setVisibility(GONE);
            tvSubraceTraitDescription.setVisibility(GONE);
        }
    }

    public Trait getReplacement(Trait trait){

        Trait replacement = null;

        // Checks subraces for a replacement.
        if(subrace != null) {
            for (Trait subraceTrait : subrace.getTraits()) {
                if (subraceTrait.getTitle().contains("Replace:") && subraceTrait.getTitle().contains(trait.getTitle())) {
                    replacement = subraceTrait;
                }
            }
        }

        if(raceChoiceSpinner.getVisibility() == VISIBLE){

            // If default is not selected...
            if(raceChoiceSpinner.getSelectedItemPosition() != 0) {

                // Gets variantrace
                Race variantRace = AppData.getRace(raceSpinner.getSelectedItem().toString()).getSubrace(raceChoiceSpinner.getSelectedItem().toString());

                // Checks variant for a replacement.
                for (Trait variantTrait : variantRace.getTraits()) {
                    if (variantTrait.getTitle().contains("Replace:") && variantTrait.getTitle().contains(trait.getTitle())) {
                        replacement = variantTrait;
                    }
                }
            }
        }
        return replacement;
    }

    public String trimReplacement(String text){

        if(text.contains("Replace:")){
            String[] parts = text.split("\\)");
            return parts[1];
        }

        return text;
    }

    public void raceChoiceVisible(boolean visible){
        if(visible) {
            raceChoiceSpinner.setVisibility(VISIBLE);
            tvRaceChoice.setVisibility(VISIBLE);
        } else {
            raceChoiceSpinner.setVisibility(GONE);
            tvRaceChoice.setVisibility(GONE);
        }
    }
}
