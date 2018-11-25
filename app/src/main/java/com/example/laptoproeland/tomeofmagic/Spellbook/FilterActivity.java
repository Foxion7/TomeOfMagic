package com.example.laptoproeland.tomeofmagic.Spellbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.DrawerActivity;
import com.example.laptoproeland.tomeofmagic.R;

public class FilterActivity extends DrawerActivity {

    RadioGroup rgConcentration;
    RadioGroup rgRitual;
    RadioGroup rgComponents;
    RadioGroup rgMaterialCost;

    Spinner castingTimeSpinner;
    String castingTimeSpinnerChoice;

    Spinner rangeSpinner;
    String rangeSpinnerChoice;

    Spinner durationSpinner;
    String durationSpinnerChoice;

    LinearLayout cbgComponents;
    TextView tvGoldCost;
    CheckBox cbMaterial;

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_filter, frameLayout);
        setTitle("Filter");

        // Hides TabLayout from actionbar
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);

        FillSpinners();
        InitializeButtons();

        ImageView btnSchool = findViewById(R.id.btnSchool);
        btnSchool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FilterSchoolsFragment filterSchoolsFragment = new FilterSchoolsFragment();
                filterSchoolsFragment.show(fm, "Dialog Fragment");
            }
        });

        ImageView btnSource = findViewById(R.id.btnSource);
        btnSource.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FilterSourcesFragment filterSourcesFragment = new FilterSourcesFragment();
                filterSourcesFragment.show(fm, "Dialog Fragment");
            }
        });

        // Saves concentration radiobuttons
        rgConcentration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbConcentration = findViewById(checkedId);
                AppData.setConcentration(rbConcentration.getText().toString());
            }
        });


        // Saves ritual radiobuttons
        rgRitual.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbRitual = findViewById(checkedId);
                AppData.setRitual(rbRitual.getText().toString());
            }
        });

        // Saves components radiobuttons
        rgComponents.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton btn = findViewById(checkedId);
            String btnComponents = (String)btn.getText();

            if(btnComponents.equals("Yes")){

                // Shows more detailed options.
                ToggleChildrenActive(cbgComponents, true);
                for (int i = 0; i < cbgComponents.getChildCount(); i++) {
                    CheckBox v = (CheckBox) cbgComponents.getChildAt(i);
                    v.setChecked(true);
                }
                if(cbMaterial.isChecked()) {
                    ToggleGoldCostVisible(true);
                }
                SetRadioButton(rgMaterialCost, AppData.getComponentCost());

            } else if (btnComponents.equals("No") || btnComponents.equals("All")){

                // Hides more detailed options.
                ToggleChildrenActive(cbgComponents, false);
                for (int i = 0; i < cbgComponents.getChildCount(); i++) {
                    CheckBox v = (CheckBox) cbgComponents.getChildAt(i);
                    v.setChecked(false);
                }
                ToggleGoldCostVisible(false);
                rgMaterialCost.check(findViewById(R.id.rbAllGoldCost).getId());
            }
            AppData.setComponents(btnComponents);
            }
        });

        cbMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   ToggleGoldCostVisible(isChecked);
               }
           }
        );

        castingTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                castingTimeSpinnerChoice = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                rangeSpinnerChoice = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                durationSpinnerChoice = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    public void SetCheckboxToCurrent(){
        for (int i = 0; i < cbgComponents.getChildCount(); i++) {
            CheckBox v = (CheckBox) cbgComponents.getChildAt(i);
            if(v.getText().toString().equals("V")){
                v.setChecked(AppData.isVerbalComponent());
            } else if (v.getText().toString().equals("S")){
                v.setChecked(AppData.isSomaticComponent());
            } else if (v.getText().toString().equals("M")){
                v.setChecked(AppData.isMaterialComponent());
            }
        }
    }

    public void ToggleGoldCostVisible(boolean visible){
        if(visible){
            rgMaterialCost.setVisibility(View.VISIBLE);
            tvGoldCost.setVisibility(View.VISIBLE);
        } else {
            rgMaterialCost.setVisibility(View.GONE);
            tvGoldCost.setVisibility(View.GONE);
        }
    }

    public void SetRadioButton(RadioGroup rg, String rbtnText){
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton rbtn = (RadioButton) rg.getChildAt(i);
            if(rbtn.getText().toString().equals(rbtnText)){
                rg.check(rbtn.getId());
            }
        }
    }

    public void ToggleChildrenActive(LinearLayout parent, boolean b){
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            v.setEnabled(b);
        }
    }

    public void ResetValues(View view){

        if (AppData.getSchoolsSelected() != null) {
            AppData.getSchoolsSelected().clear();
            for (String school : getResources().getStringArray(R.array.schools_array)) {
                AppData.getSchoolsSelected().add(school);
            }
        }

        if(AppData.getSourcesSelected() != null) {
            AppData.getSourcesSelected().clear();
            for (String source : getResources().getStringArray(R.array.sources_array_short)) {
                AppData.getSourcesSelected().add(source);
            }
        }

        rgConcentration.check(R.id.rbAllConcentration);
        AppData.setConcentration("All");

        rgRitual.check(R.id.rbAllRitual);
        AppData.setRitual("All");

        rgComponents.check(R.id.rbAllComponents);
        AppData.setComponents("All");

        if(rgMaterialCost != null){
            rgMaterialCost.check(R.id.rbAllGoldCost);
        }
        AppData.setComponentCost("All");

        castingTimeSpinner.setSelection(0);
        rangeSpinner.setSelection(0);
        durationSpinner.setSelection(0);
    }

    public void FillSpinners(){

        // Fill casting time spinner
        castingTimeSpinner = findViewById(R.id.spnrCastingTime);
        ArrayAdapter<CharSequence> castingTimeSpinAdapter = ArrayAdapter.createFromResource(this,
                R.array.casting_time_array, android.R.layout.simple_spinner_item);
        castingTimeSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        castingTimeSpinner.setAdapter(castingTimeSpinAdapter);

        // Fill range spinner
        rangeSpinner = findViewById(R.id.spnrRange);
        ArrayAdapter<CharSequence> rangeSpinAdapter = ArrayAdapter.createFromResource(this,
                R.array.range_array, android.R.layout.simple_spinner_item);
        rangeSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeSpinner.setAdapter(rangeSpinAdapter);

        // Fill duration spinner
        durationSpinner = findViewById(R.id.spnrDuration);
        ArrayAdapter<CharSequence> durationSpinAdapter = ArrayAdapter.createFromResource(this,
                R.array.duration_array, android.R.layout.simple_spinner_item);
        durationSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(durationSpinAdapter);
    }

    public void InitializeButtons(){

        tvGoldCost = findViewById(R.id.tvGoldcost);
        rgMaterialCost = findViewById(R.id.rgMaterialCost);
        cbgComponents = findViewById(R.id.cbgComponents);
        cbMaterial = findViewById(R.id.cbMaterial);

        rgConcentration = findViewById(R.id.rgConcentration);
        for (int i = 0; i < rgConcentration.getChildCount() ; i++) {
            RadioButton v = (RadioButton) rgConcentration.getChildAt(i);
            if(v.getText().equals(AppData.getConcentration())){
                v.setChecked(true);
            }
        }

        rgRitual = findViewById(R.id.rgRitual);
        for (int i = 0; i < rgRitual.getChildCount() ; i++) {
            RadioButton v = (RadioButton) rgRitual.getChildAt(i);
            if(v.getText().equals(AppData.getRitual())){
                v.setChecked(true);
            }
        }

        rgComponents = findViewById(R.id.rgComponents);
        for (int i = 0; i < rgComponents.getChildCount() ; i++) {
            RadioButton v = (RadioButton) rgComponents.getChildAt(i);
            if(v.getText().equals(AppData.getComponents())){
                v.setChecked(true);
                if(v.getText().equals("Yes")){
                    ToggleChildrenActive(cbgComponents, true);
                    SetCheckboxToCurrent();
                    if(cbMaterial.isChecked()) {
                        ToggleGoldCostVisible(true);
                    }
                }
            }
        }

        for (int i = 0; i < rgMaterialCost.getChildCount() ; i++) {
            RadioButton v = (RadioButton) rgMaterialCost.getChildAt(i);
            if(v.getText().equals(AppData.getComponentCost())){
                v.setChecked(true);
            }
        }

        String[] castingTimeArray = getResources().getStringArray(R.array.casting_time_array);
        for(int i = 0; i < castingTimeArray.length; i++) {
            if(castingTimeArray[i].equals(AppData.getCastingTimeSelected())){
                castingTimeSpinner.setSelection(i);
                break;
            }
        }

        String[] rangeArray = getResources().getStringArray(R.array.range_array);
        for(int i = 0; i < rangeArray.length; i++) {
            if(rangeArray[i].equals(AppData.getRangeSelected())){
                rangeSpinner.setSelection(i);
                break;
            }
        }

        String[] durationArray = getResources().getStringArray(R.array.duration_array);
        for(int i = 0; i < durationArray.length; i++) {
            if(durationArray[i].equals(AppData.getDurationSelected())){
                durationSpinner.setSelection(i);
                break;
            }
        }
    }

    public void openSpellbook(View view) {
        CheckBox cbV = findViewById(R.id.cbVerbal);
        AppData.setVerbalComponent(cbV.isChecked());

        CheckBox cbS = findViewById(R.id.cbSomatic);
        AppData.setSomaticComponent(cbS.isChecked());

        CheckBox cbM = findViewById(R.id.cbMaterial);
        AppData.setMaterialComponent(cbM.isChecked());

        RadioButton btnComponents = findViewById(rgComponents.getCheckedRadioButtonId());
        AppData.setComponents((String) btnComponents.getText());

        if(rgMaterialCost != null) {
            RadioButton btnComponentCost = findViewById(rgMaterialCost.getCheckedRadioButtonId());
            AppData.setComponentCost((String) btnComponentCost.getText());
        }

        AppData.setCastingTimeSelected(castingTimeSpinnerChoice);
        AppData.setRangeSelected(rangeSpinnerChoice);
        AppData.setDurationSelected(durationSpinnerChoice);

        Intent intent = new Intent(this, SpellbookActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ResetValues(null);
    }
}
