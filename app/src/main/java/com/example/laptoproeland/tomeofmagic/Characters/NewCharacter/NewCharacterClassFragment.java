package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.Characters.Class;
import com.example.laptoproeland.tomeofmagic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCharacterClassFragment extends Fragment {

    Spinner classSpinner;
    Spinner subclassSpinner;

    TextView tvClassTraitsDescription;

    public NewCharacterClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_character_class, container, false);

        tvClassTraitsDescription = view.findViewById(R.id.tvClassTraitsDescription);
        tvClassTraitsDescription.setMovementMethod(new ScrollingMovementMethod());

        classSpinner = view.findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> classSpinAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.classes_array, android.R.layout.simple_spinner_item);
        classSpinAdapter.setDropDownViewResource(R.layout.new_character_spinner_listitem);
        classSpinner.setAdapter(classSpinAdapter);

        subclassSpinner = view.findViewById(R.id.subclass_spinner);
        updateSubclassSpinner(R.array.subclass_barbarian_array);

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(classSpinner.getSelectedItem().toString().equals("Barbarian")){
                    updateSubclassSpinner(R.array.subclass_barbarian_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Bard")){
                    updateSubclassSpinner(R.array.subclass_bard_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Cleric")){
                    updateSubclassSpinner(R.array.subclass_cleric_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Druid")){
                    updateSubclassSpinner(R.array.subclass_druid_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Fighter")){
                    updateSubclassSpinner(R.array.subclass_fighter_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Monk")){
                    updateSubclassSpinner(R.array.subclass_monk_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Paladin")){
                    updateSubclassSpinner(R.array.subclass_paladin_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Ranger")){
                    updateSubclassSpinner(R.array.subclass_ranger_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Rogue")){
                    updateSubclassSpinner(R.array.subclass_rogue_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Sorcerer")){
                    updateSubclassSpinner(R.array.subclass_sorcerer_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Warlock")){
                    updateSubclassSpinner(R.array.subclass_warlock_array);
                } else if(classSpinner.getSelectedItem().toString().equals("Wizard")){
                    updateSubclassSpinner(R.array.subclass_wizard_array);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return view;
    }
    public void updateSubclassSpinner(int subclassArray){
        ArrayAdapter<CharSequence> subclassSpinAdapter = ArrayAdapter.createFromResource(getContext(),
                subclassArray, android.R.layout.simple_spinner_item);
        subclassSpinAdapter.notifyDataSetChanged();
        subclassSpinAdapter.setDropDownViewResource(R.layout.new_character_spinner_listitem);
        subclassSpinner.setAdapter(subclassSpinAdapter);

        updateClassTraits();
    }

    public void updateClassTraits(){
        Class currentClass = AppData.getClass(classSpinner.getSelectedItem().toString());

        tvClassTraitsDescription.setText(currentClass.getName());
    }

}
