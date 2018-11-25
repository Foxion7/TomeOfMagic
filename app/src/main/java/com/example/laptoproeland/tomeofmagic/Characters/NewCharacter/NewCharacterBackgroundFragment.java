package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Background;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Talent;
import com.example.laptoproeland.tomeofmagic.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCharacterBackgroundFragment extends Fragment {

    Spinner backgroundSpinner;

    View view;

    public NewCharacterBackgroundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_character_background, container, false);

        backgroundSpinner = view.findViewById(R.id.background_spinner);
        ArrayAdapter<CharSequence> backgroundSpinAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.background_array, android.R.layout.simple_spinner_item);
        backgroundSpinAdapter.setDropDownViewResource(R.layout.new_character_spinner_listitem);
        backgroundSpinner.setAdapter(backgroundSpinAdapter);

        updateProficiencyFields(backgroundSpinner.getSelectedItem().toString());

        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateProficiencyFields(backgroundSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        TextView tv = view.findViewById(R.id.tvBackgroundTraitsDescription);
        tv.setText("Backgroundtraits go here....");
        tv.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

    public void updateProficiencyFields(String b){

        Background background = AppData.getBackground(b);

        View loSkills = view.findViewById(R.id.loNewCharacterSkills);
        View loTools = view.findViewById(R.id.loNewCharacterTools);
        View loLanguages = view.findViewById(R.id.loNewCharacterLanguages);

        CheckBox cbSkill1 = view.findViewById(R.id.cbBackgroundskill1);
        CheckBox cbSkill2 = view.findViewById(R.id.cbBackgroundskill2);

        CheckBox cbTool1 = view.findViewById(R.id.cbBackgroundtool1);
        CheckBox cbTool2 = view.findViewById(R.id.cbBackgroundtool2);

        CheckBox cbLanguage1 = view.findViewById(R.id.cbBackgroundlanguage1);
        CheckBox cbLanguage2 = view.findViewById(R.id.cbBackgroundlanguage2);

        boolean hasTool = false;
        boolean hasLanguage = false;

        boolean skill1Set = false;
        boolean skill2Set = false;

        boolean tool1Set = false;
        boolean tool2Set = false;

        boolean language1Set = false;
        boolean language2Set = false;

        ArrayList<Talent> talents = null;

        if (background != null) {
            talents = background.getTalents();
        }

        if(talents != null) {
            for (Talent talent : talents) {
                if (talent.getType() == Talent.Type.Equipment) {
                    if(!tool1Set){
                        cbTool1.setText(talent.getName());
                        tool1Set = true;
                    } else if(!tool2Set){
                        cbTool2.setText(talent.getName());
                        tool2Set = true;
                    }
                    hasTool = true;
                } else if (talent.getType() == Talent.Type.Language) {
                    if(!language1Set){
                        cbLanguage1.setText(talent.getName());
                        language1Set = true;
                    } else if(!language2Set){
                        cbLanguage2.setText(talent.getName());
                        language2Set = true;
                    }
                    hasLanguage= true;
                } else {
                    if(!skill1Set){
                        cbSkill1.setText(talent.getName());
                        skill1Set = true;
                    } else if(!skill2Set){
                        cbSkill2.setText(talent.getName());
                        skill2Set = true;
                    }
                }
            }
        }

        if(hasTool){
            loTools.setVisibility(View.VISIBLE);
        } else {
            loTools.setVisibility(View.GONE);
        }

        if(hasLanguage){
            loLanguages.setVisibility(View.VISIBLE);
        } else {
            loLanguages.setVisibility(View.GONE);
        }
    }
}
