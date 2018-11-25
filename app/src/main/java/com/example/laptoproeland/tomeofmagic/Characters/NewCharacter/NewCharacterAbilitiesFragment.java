package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCharacterAbilitiesFragment extends Fragment {

    View view;
    TextView tvStrengthScore;
    TextView tvDexterityScore;
    TextView tvConstitutionScore;
    TextView tvIntelligenceScore;
    TextView tvWisdomScore;
    TextView tvCharismaScore;

    // Pointbuy
    int pointbuyPointMax = 27;
    int pointbuyPointMin = 0;
    int pointbuyPoints = 27;
    int maxScore = 15;
    int minScore = 8;

    TextView tvPointbuyPoints;

    Button btnIncreaseStrength;
    Button btnIncreaseDexterity;
    Button btnIncreaseConstitution;
    Button btnIncreaseIntelligence;
    Button btnIncreaseWisdom;
    Button btnIncreaseCharisma;

    Button btnDecreaseStrength;
    Button btnDecreaseDexterity;
    Button btnDecreaseConstitution;
    Button btnDecreaseIntelligence;
    Button btnDecreaseWisdom;
    Button btnDecreaseCharisma;

    // Standard Array
    int old15StandardArray = 0;
    int old14StandardArray = 1;
    int old13StandardArray = 2;
    int old12StandardArray = 3;
    int old10StandardArray = 4;
    int old8StandardArray = 5;

    Spinner standardArray15Spinner;
    Spinner standardArray14Spinner;
    Spinner standardArray13Spinner;
    Spinner standardArray12Spinner;
    Spinner standardArray10Spinner;
    Spinner standardArray8Spinner;

    // Rolling
    int old1stInRowRolling = 0;
    int old2ndInRowRolling = 1;
    int old3rdInRowRolling = 2;
    int old4thInRowRolling = 3;
    int old5thInRowRolling = 4;
    int old6thInRowRolling = 5;

    Spinner rolling15Spinner;
    Spinner rolling14Spinner;
    Spinner rolling13Spinner;
    Spinner rolling12Spinner;
    Spinner rolling10Spinner;
    Spinner rolling8Spinner;

    public NewCharacterAbilitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_character_abilities, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tloStats);
        (view.findViewById(R.id.tab_pointbuy_content)).setVisibility(View.VISIBLE);

        startPointBuy();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if("Pointbuy".equals(tab.getText().toString())){
                    (view.findViewById(R.id.tab_pointbuy_content)).setVisibility(View.VISIBLE);
                    (view.findViewById(R.id.tab_standard_array_content)).setVisibility(View.GONE);
                    (view.findViewById(R.id.tab_roll_content)).setVisibility(View.GONE);
                    startPointBuy();
                } else if ("Standard Array".equals(tab.getText().toString())){
                    (view.findViewById(R.id.tab_standard_array_content)).setVisibility(View.VISIBLE);
                    (view.findViewById(R.id.tab_pointbuy_content)).setVisibility(View.GONE);
                    (view.findViewById(R.id.tab_roll_content)).setVisibility(View.GONE);
                    startStandardArray();
                } else if ("Roll".equals(tab.getText().toString())){
                    (view.findViewById(R.id.tab_roll_content)).setVisibility(View.VISIBLE);
                    (view.findViewById(R.id.tab_pointbuy_content)).setVisibility(View.GONE);
                    (view.findViewById(R.id.tab_standard_array_content)).setVisibility(View.GONE);
                    startRolling();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }
    // Pointbuy
    public void startPointBuy(){
        btnIncreaseStrength = view.findViewById(R.id.tvStrengthScoreIncrease);
        btnIncreaseDexterity = view.findViewById(R.id.tvDexterityScoreIncrease);
        btnIncreaseConstitution = view.findViewById(R.id.tvConstitutionScoreIncrease);
        btnIncreaseIntelligence = view.findViewById(R.id.tvIntelligenceScoreIncrease);
        btnIncreaseWisdom = view.findViewById(R.id.tvWisdomScoreIncrease);
        btnIncreaseCharisma = view.findViewById(R.id.tvCharismaScoreIncrease);

        btnDecreaseStrength = view.findViewById(R.id.tvStrengthScoreDecrease);
        btnDecreaseDexterity = view.findViewById(R.id.tvDexterityScoreDecrease);
        btnDecreaseConstitution = view.findViewById(R.id.tvConstitutionScoreDecrease);
        btnDecreaseIntelligence = view.findViewById(R.id.tvIntelligenceScoreDecrease);
        btnDecreaseWisdom = view.findViewById(R.id.tvWisdomScoreDecrease);
        btnDecreaseCharisma = view.findViewById(R.id.tvCharismaScoreDecrease);

        tvStrengthScore = view.findViewById(R.id.tvStrengthScorePointbuy);
        tvDexterityScore = view.findViewById(R.id.tvDexterityScorePointbuy);
        tvConstitutionScore = view.findViewById(R.id.tvConstitutionScorePointbuy);
        tvIntelligenceScore = view.findViewById(R.id.tvIntelligenceScorePointbuy);
        tvWisdomScore = view.findViewById(R.id.tvWisdomScorePointbuy);
        tvCharismaScore = view.findViewById(R.id.tvCharismaScorePointbuy);

        tvPointbuyPoints = view.findViewById(R.id.tvPointbuyPoints);

        checkRemainingPoints();

        btnIncreaseStrength.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAbility(tvStrengthScore);
            }
        });

        btnIncreaseStrength.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseAbilityMultiple(tvStrengthScore);
                return true;
            }
        });

        btnDecreaseStrength.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAbility(tvStrengthScore);
            }
        });

        btnDecreaseStrength.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseAbilityMultiple(tvStrengthScore);
                return true;
            }
        });

        // Dexterity
        btnIncreaseDexterity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAbility(tvDexterityScore);
            }
        });

        btnIncreaseDexterity.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseAbilityMultiple(tvDexterityScore);
                return true;
            }
        });

        btnDecreaseDexterity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAbility(tvDexterityScore);
            }
        });

        btnDecreaseDexterity.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseAbilityMultiple(tvDexterityScore);
                return true;
            }
        });

        // Constitution
        btnIncreaseConstitution.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAbility(tvConstitutionScore);
            }
        });

        btnIncreaseConstitution.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseAbilityMultiple(tvConstitutionScore);
                return true;
            }
        });

        btnDecreaseConstitution.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAbility(tvConstitutionScore);
            }
        });

        btnDecreaseConstitution.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseAbilityMultiple(tvConstitutionScore);
                return true;
            }
        });

        // Intelligence
        btnIncreaseIntelligence.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAbility(tvIntelligenceScore);
            }
        });

        btnIncreaseIntelligence.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseAbilityMultiple(tvIntelligenceScore);
                return true;
            }
        });

        btnDecreaseIntelligence.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAbility(tvIntelligenceScore);
            }
        });

        btnDecreaseIntelligence.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseAbilityMultiple(tvIntelligenceScore);
                return true;
            }
        });

        // Wisdom
        btnIncreaseWisdom.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAbility(tvWisdomScore);
            }
        });

        btnIncreaseWisdom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseAbilityMultiple(tvWisdomScore);
                return true;
            }
        });

        btnDecreaseWisdom.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAbility(tvWisdomScore);
            }
        });

        btnDecreaseWisdom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseAbilityMultiple(tvWisdomScore);
                return true;
            }
        });

        // Charisma
        btnIncreaseCharisma.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseAbility(tvCharismaScore);
            }
        });

        btnIncreaseCharisma.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseAbilityMultiple(tvCharismaScore);
                return true;
            }
        });

        btnDecreaseCharisma.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseAbility(tvCharismaScore);
            }
        });

        btnDecreaseCharisma.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseAbilityMultiple(tvCharismaScore);
                return true;
            }
        });
    }

    public void increaseAbility(TextView tvAbility){

        int currentScore = Integer.parseInt(tvAbility.getText().toString());

        if(currentScore < maxScore) {
            if (currentScore >= 13 && pointbuyPoints >= pointbuyPointMin + 2) {
                pointbuyPoints -= 2;
                currentScore++;
                tvAbility.setText(String.valueOf(currentScore));
                tvPointbuyPoints.setText(String.valueOf(pointbuyPoints));

            } else if (currentScore < 13 && pointbuyPoints >= pointbuyPointMin + 1) {
                pointbuyPoints -= 1;
                currentScore++;
                tvAbility.setText(String.valueOf(currentScore));
                tvPointbuyPoints.setText(String.valueOf(pointbuyPoints));
            }
            checkRemainingPoints();
        }
    }

    public void increaseAbilityMultiple(TextView tvAbility){

        int increment = maxScore - Integer.parseInt(tvAbility.getText().toString());

        for(int i = 0; i < increment; i++){
            increaseAbility(tvAbility);
        }
    }

    public void decreaseAbility(TextView tvAbility){

        int currentScore = Integer.parseInt(tvAbility.getText().toString());

        if(currentScore > minScore) {
            if (currentScore > 13 && pointbuyPoints <= pointbuyPointMax - 2) {
                pointbuyPoints += 2;
                currentScore--;
                tvAbility.setText(String.valueOf(currentScore));
                tvPointbuyPoints.setText(String.valueOf(pointbuyPoints));

            } else if (currentScore <= 13 && pointbuyPoints <= pointbuyPointMax - 1) {
                pointbuyPoints += 1;
                currentScore--;
                tvAbility.setText(String.valueOf(currentScore));
                tvPointbuyPoints.setText(String.valueOf(pointbuyPoints));
            }
            checkRemainingPoints();
        }
    }

    public void decreaseAbilityMultiple(TextView tvAbility){

        int increment = Integer.parseInt(tvAbility.getText().toString()) - minScore;

        for(int i = 0; i < increment; i++){
            decreaseAbility(tvAbility);
        }
    }

    public void checkRemainingPoints(){

        int strengthScore = Integer.parseInt(tvStrengthScore.getText().toString());
        int dexterityScore = Integer.parseInt(tvDexterityScore.getText().toString());
        int constitutionScore = Integer.parseInt(tvConstitutionScore.getText().toString());
        int intelligenceScore = Integer.parseInt(tvIntelligenceScore.getText().toString());
        int wisdomScore = Integer.parseInt(tvWisdomScore.getText().toString());
        int charismaScore = Integer.parseInt(tvCharismaScore.getText().toString());

        // If no points remain, disable increasing for all abilities.
        if(pointbuyPoints == pointbuyPointMin){
            Log.d("", "LOG - case 1");

            btnIncreaseStrength.setEnabled(false);
            btnIncreaseDexterity.setEnabled(false);
            btnIncreaseConstitution.setEnabled(false);
            btnIncreaseIntelligence.setEnabled(false);
            btnIncreaseWisdom.setEnabled(false);
            btnIncreaseCharisma.setEnabled(false);

            // If 1 point remains, disable all increasing for abilities that would require more than 1 point.
        } else if (pointbuyPoints == pointbuyPointMin + 1){
            Log.d("", "LOG - case 3");

            if(strengthScore >= 13 || strengthScore == maxScore){
                btnIncreaseStrength.setEnabled(false);
                btnDecreaseStrength.setEnabled(false);
            } else {
                btnIncreaseStrength.setEnabled(true);
            }

            if(dexterityScore >= 13 || dexterityScore == maxScore){
                btnIncreaseDexterity.setEnabled(false);
            } else {
                btnIncreaseDexterity.setEnabled(true);
            }

            if(constitutionScore >= 13 || constitutionScore == maxScore){
                btnIncreaseConstitution.setEnabled(false);
            } else {
                btnIncreaseConstitution.setEnabled(true);
            }

            if(intelligenceScore >= 13 || intelligenceScore == maxScore){
                btnIncreaseIntelligence.setEnabled(false);
            } else {
                btnIncreaseIntelligence.setEnabled(true);
            }

            if(wisdomScore >= 13 || wisdomScore == maxScore){
                btnIncreaseWisdom.setEnabled(false);
            } else {
                btnIncreaseWisdom.setEnabled(true);
            }

            if(charismaScore >= 13 || charismaScore == maxScore){
                btnIncreaseCharisma.setEnabled(false);
            } else {
                btnIncreaseCharisma.setEnabled(true);
            }
            // If more than 1 points remains, enables increasing all abilities that aren't max value.
        } else {

            if(strengthScore < maxScore){
                btnIncreaseStrength.setEnabled(true);
            } else {
                btnIncreaseStrength.setEnabled(false);
            }

            if(dexterityScore < maxScore){
                btnIncreaseDexterity.setEnabled(true);
            } else {
                btnIncreaseDexterity.setEnabled(false);
            }

            if(constitutionScore < maxScore){
                btnIncreaseConstitution.setEnabled(true);
            } else {
                btnIncreaseConstitution.setEnabled(false);
            }

            if(intelligenceScore < maxScore){
                btnIncreaseIntelligence.setEnabled(true);
            } else {
                btnIncreaseIntelligence.setEnabled(false);
            }

            if(wisdomScore < maxScore){
                btnIncreaseWisdom.setEnabled(true);
            } else {
                btnIncreaseWisdom.setEnabled(false);
            }

            if(charismaScore < maxScore){
                btnIncreaseCharisma.setEnabled(true);
            } else {
                btnIncreaseCharisma.setEnabled(false);
            }
        }

        // If any abilityscore is above minimum, enables decreasing.
        if(strengthScore > minScore) {
            btnDecreaseStrength.setEnabled(true);
        } else {
            btnDecreaseStrength.setEnabled(false);
        }

        if(dexterityScore > minScore) {
            btnDecreaseDexterity.setEnabled(true);
        } else {
            btnDecreaseDexterity.setEnabled(false);
        }

        if(constitutionScore > minScore) {
            btnDecreaseConstitution.setEnabled(true);
        } else {
            btnDecreaseConstitution.setEnabled(false);
        }

        if(intelligenceScore > minScore) {
            btnDecreaseIntelligence.setEnabled(true);
        } else {
            btnDecreaseIntelligence.setEnabled(false);
        }

        if(wisdomScore > minScore) {
            btnDecreaseWisdom.setEnabled(true);
        } else {
            btnDecreaseWisdom.setEnabled(false);
        }

        if(charismaScore > minScore) {
            btnDecreaseCharisma.setEnabled(true);
        } else {
            btnDecreaseCharisma.setEnabled(false);
        }

    }

    // Standard Array
    public void startStandardArray(){

        standardArray15Spinner = view.findViewById(R.id.standardarray_15_spinner);
        ArrayAdapter<CharSequence> standardArray15SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        standardArray15SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        standardArray15Spinner.setAdapter(standardArray15SpinAdapter);
        standardArray15Spinner.setSelection(0);

        standardArray14Spinner = view.findViewById(R.id.standardarray_14_spinner);
        ArrayAdapter<CharSequence> standardArray14SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        standardArray14SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        standardArray14Spinner.setAdapter(standardArray14SpinAdapter);
        standardArray14Spinner.setSelection(1);

        standardArray13Spinner = view.findViewById(R.id.standardarray_13_spinner);
        ArrayAdapter<CharSequence> standardArray13SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        standardArray13SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        standardArray13Spinner.setAdapter(standardArray13SpinAdapter);
        standardArray13Spinner.setSelection(2);

        standardArray12Spinner = view.findViewById(R.id.standardarray_12_spinner);
        ArrayAdapter<CharSequence> standardArray12SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        standardArray12SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        standardArray12Spinner.setAdapter(standardArray12SpinAdapter);
        standardArray12Spinner.setSelection(3);

        standardArray10Spinner = view.findViewById(R.id.standardarray_10_spinner);
        ArrayAdapter<CharSequence> standardArray10SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        standardArray10SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        standardArray10Spinner.setAdapter(standardArray10SpinAdapter);
        standardArray10Spinner.setSelection(4);

        standardArray8Spinner = view.findViewById(R.id.standardarray_8_spinner);
        ArrayAdapter<CharSequence> standardArray8SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        standardArray8SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        standardArray8Spinner.setAdapter(standardArray8SpinAdapter);
        standardArray8Spinner.setSelection(5);

        standardArray15Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesStandardArray();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        standardArray14Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesStandardArray();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        standardArray13Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesStandardArray();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        standardArray12Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesStandardArray();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        standardArray10Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesStandardArray();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        standardArray8Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesStandardArray();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void checkForDoublesStandardArray(){

        int position = standardArray15Spinner.getSelectedItemPosition();

        if (position == standardArray14Spinner.getSelectedItemPosition()) {
            standardArray14Spinner.setSelection(old15StandardArray);
        } else if (position == standardArray13Spinner.getSelectedItemPosition()){
            standardArray13Spinner.setSelection(old15StandardArray);
        } else if (position == standardArray12Spinner.getSelectedItemPosition()){
            standardArray12Spinner.setSelection(old15StandardArray);
        } else if (position == standardArray10Spinner.getSelectedItemPosition()){
            standardArray10Spinner.setSelection(old15StandardArray);
        } else if (position == standardArray8Spinner.getSelectedItemPosition()){
            standardArray8Spinner.setSelection(old15StandardArray);
        }
        old15StandardArray = position;

        position = standardArray14Spinner.getSelectedItemPosition();

        if (position == standardArray15Spinner.getSelectedItemPosition()) {
            standardArray15Spinner.setSelection(old14StandardArray);
        } else if (position == standardArray13Spinner.getSelectedItemPosition()){
            standardArray13Spinner.setSelection(old14StandardArray);
        } else if (position == standardArray12Spinner.getSelectedItemPosition()){
            standardArray12Spinner.setSelection(old14StandardArray);
        } else if (position == standardArray10Spinner.getSelectedItemPosition()){
            standardArray10Spinner.setSelection(old14StandardArray);
        } else if (position == standardArray8Spinner.getSelectedItemPosition()){
            standardArray8Spinner.setSelection(old14StandardArray);
        }
        old14StandardArray = position;

        position = standardArray13Spinner.getSelectedItemPosition();

        if (position == standardArray15Spinner.getSelectedItemPosition()) {
            standardArray15Spinner.setSelection(old13StandardArray);
        } else if (position == standardArray14Spinner.getSelectedItemPosition()){
            standardArray14Spinner.setSelection(old13StandardArray);
        } else if (position == standardArray12Spinner.getSelectedItemPosition()){
            standardArray12Spinner.setSelection(old13StandardArray);
        } else if (position == standardArray10Spinner.getSelectedItemPosition()){
            standardArray10Spinner.setSelection(old13StandardArray);
        } else if (position == standardArray8Spinner.getSelectedItemPosition()){
            standardArray8Spinner.setSelection(old13StandardArray);
        }
        old13StandardArray = position;

        position = standardArray12Spinner.getSelectedItemPosition();

        if (position == standardArray15Spinner.getSelectedItemPosition()) {
            standardArray15Spinner.setSelection(old12StandardArray);
        } else if (position == standardArray14Spinner.getSelectedItemPosition()){
            standardArray14Spinner.setSelection(old12StandardArray);
        } else if (position == standardArray13Spinner.getSelectedItemPosition()){
            standardArray13Spinner.setSelection(old12StandardArray);
        } else if (position == standardArray10Spinner.getSelectedItemPosition()){
            standardArray14Spinner.setSelection(old10StandardArray);
        } else if (position == standardArray8Spinner.getSelectedItemPosition()){
            standardArray14Spinner.setSelection(old8StandardArray);
        }
        old12StandardArray = position;

        position = standardArray10Spinner.getSelectedItemPosition();

        if (position == standardArray15Spinner.getSelectedItemPosition()) {
            standardArray15Spinner.setSelection(old10StandardArray);
        } else if (position == standardArray14Spinner.getSelectedItemPosition()){
            standardArray14Spinner.setSelection(old10StandardArray);
        } else if (position == standardArray13Spinner.getSelectedItemPosition()){
            standardArray13Spinner.setSelection(old10StandardArray);
        } else if (position == standardArray12Spinner.getSelectedItemPosition()){
            standardArray12Spinner.setSelection(old10StandardArray);
        } else if (position == standardArray8Spinner.getSelectedItemPosition()){
            standardArray8Spinner.setSelection(old10StandardArray);
        }
        old10StandardArray = position;

        position = standardArray8Spinner.getSelectedItemPosition();

        if (position == standardArray15Spinner.getSelectedItemPosition()) {
            standardArray15Spinner.setSelection(old8StandardArray);
        } else if (position == standardArray14Spinner.getSelectedItemPosition()){
            standardArray14Spinner.setSelection(old8StandardArray);
        } else if (position == standardArray13Spinner.getSelectedItemPosition()){
            standardArray13Spinner.setSelection(old8StandardArray);
        } else if (position == standardArray12Spinner.getSelectedItemPosition()){
            standardArray12Spinner.setSelection(old8StandardArray);
        } else if (position == standardArray10Spinner.getSelectedItemPosition()){
            standardArray10Spinner.setSelection(old8StandardArray);
        }
        old8StandardArray = position;
    }

    // Rolling
    public void startRolling(){

        rolling15Spinner = view.findViewById(R.id.rolling_15_spinner);
        ArrayAdapter<CharSequence> rolling15SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        rolling15SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        rolling15Spinner.setAdapter(rolling15SpinAdapter);
        rolling15Spinner.setSelection(0);

        rolling14Spinner = view.findViewById(R.id.rolling_14_spinner);
        ArrayAdapter<CharSequence> rolling14SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        rolling14SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        rolling14Spinner.setAdapter(rolling14SpinAdapter);
        rolling14Spinner.setSelection(1);

        rolling13Spinner = view.findViewById(R.id.rolling_13_spinner);
        ArrayAdapter<CharSequence> rolling13SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        rolling13SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        rolling13Spinner.setAdapter(rolling13SpinAdapter);
        rolling13Spinner.setSelection(2);

        rolling12Spinner = view.findViewById(R.id.rolling_12_spinner);
        ArrayAdapter<CharSequence> rolling12SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        rolling12SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        rolling12Spinner.setAdapter(rolling12SpinAdapter);
        rolling12Spinner.setSelection(3);

        rolling10Spinner = view.findViewById(R.id.rolling_10_spinner);
        ArrayAdapter<CharSequence> rolling10SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        rolling10SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        rolling10Spinner.setAdapter(rolling10SpinAdapter);
        rolling10Spinner.setSelection(4);

        rolling8Spinner = view.findViewById(R.id.rolling_8_spinner);
        ArrayAdapter<CharSequence> rolling8SpinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.abilities_array, R.layout.tab_standard_array_ability_listitem);
        rolling8SpinAdapter.setDropDownViewResource(R.layout.tab_standard_array_ability_listitem);
        rolling8Spinner.setAdapter(rolling8SpinAdapter);
        rolling8Spinner.setSelection(5);

        ImageButton btnRandomize4D6 = view.findViewById(R.id.btnRandomize4D6_1);
        btnRandomize4D6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ((TextView)view.findViewById(R.id.tv15Rolling)).setText(String.valueOf(getRandomNumber(4)));
                ((TextView)view.findViewById(R.id.tv14Rolling)).setText(String.valueOf(getRandomNumber(4)));
                ((TextView)view.findViewById(R.id.tv13Rolling)).setText(String.valueOf(getRandomNumber(4)));
                ((TextView)view.findViewById(R.id.tv12Rolling)).setText(String.valueOf(getRandomNumber(4)));
                ((TextView)view.findViewById(R.id.tv10Rolling)).setText(String.valueOf(getRandomNumber(4)));
                ((TextView)view.findViewById(R.id.tv8Rolling)).setText(String.valueOf(getRandomNumber(4)));
            }
        });

        ImageButton btnRandomize3D6 = view.findViewById(R.id.btnRandomize3D6);
        btnRandomize3D6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ((TextView)view.findViewById(R.id.tv15Rolling)).setText(String.valueOf(getRandomNumber(3)));
                ((TextView)view.findViewById(R.id.tv14Rolling)).setText(String.valueOf(getRandomNumber(3)));
                ((TextView)view.findViewById(R.id.tv13Rolling)).setText(String.valueOf(getRandomNumber(3)));
                ((TextView)view.findViewById(R.id.tv12Rolling)).setText(String.valueOf(getRandomNumber(3)));
                ((TextView)view.findViewById(R.id.tv10Rolling)).setText(String.valueOf(getRandomNumber(3)));
                ((TextView)view.findViewById(R.id.tv8Rolling)).setText(String.valueOf(getRandomNumber(3)));
            }
        });

        rolling15Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesRolling();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        rolling14Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesRolling();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        rolling13Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesRolling();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        rolling12Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesRolling();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        rolling10Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesRolling();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        rolling8Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                checkForDoublesRolling();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void checkForDoublesRolling(){

        int position = rolling15Spinner.getSelectedItemPosition();

        if (position == rolling14Spinner.getSelectedItemPosition()) {
            rolling14Spinner.setSelection(old1stInRowRolling);
        } else if (position == rolling13Spinner.getSelectedItemPosition()){
            rolling13Spinner.setSelection(old1stInRowRolling);
        } else if (position == rolling12Spinner.getSelectedItemPosition()){
            rolling12Spinner.setSelection(old1stInRowRolling);
        } else if (position == rolling10Spinner.getSelectedItemPosition()){
            rolling10Spinner.setSelection(old1stInRowRolling);
        } else if (position == rolling8Spinner.getSelectedItemPosition()){
            rolling8Spinner.setSelection(old1stInRowRolling);
        }
        old1stInRowRolling = position;

        position = rolling14Spinner.getSelectedItemPosition();

        if (position == rolling15Spinner.getSelectedItemPosition()) {
            rolling15Spinner.setSelection(old2ndInRowRolling);
        } else if (position == rolling13Spinner.getSelectedItemPosition()){
            rolling13Spinner.setSelection(old2ndInRowRolling);
        } else if (position == rolling12Spinner.getSelectedItemPosition()){
            rolling12Spinner.setSelection(old2ndInRowRolling);
        } else if (position == rolling10Spinner.getSelectedItemPosition()){
            rolling10Spinner.setSelection(old2ndInRowRolling);
        } else if (position == rolling8Spinner.getSelectedItemPosition()){
            rolling8Spinner.setSelection(old2ndInRowRolling);
        }
        old2ndInRowRolling = position;

        position = rolling13Spinner.getSelectedItemPosition();

        if (position == rolling15Spinner.getSelectedItemPosition()) {
            rolling15Spinner.setSelection(old3rdInRowRolling);
        } else if (position == rolling14Spinner.getSelectedItemPosition()){
            rolling14Spinner.setSelection(old3rdInRowRolling);
        } else if (position == rolling12Spinner.getSelectedItemPosition()){
            rolling12Spinner.setSelection(old3rdInRowRolling);
        } else if (position == rolling10Spinner.getSelectedItemPosition()){
            rolling10Spinner.setSelection(old3rdInRowRolling);
        } else if (position == rolling8Spinner.getSelectedItemPosition()){
            rolling8Spinner.setSelection(old3rdInRowRolling);
        }
        old3rdInRowRolling = position;

        position = rolling12Spinner.getSelectedItemPosition();

        if (position == rolling15Spinner.getSelectedItemPosition()) {
            rolling15Spinner.setSelection(old4thInRowRolling);
        } else if (position == rolling14Spinner.getSelectedItemPosition()){
            rolling14Spinner.setSelection(old4thInRowRolling);
        } else if (position == rolling13Spinner.getSelectedItemPosition()){
            rolling13Spinner.setSelection(old4thInRowRolling);
        } else if (position == rolling10Spinner.getSelectedItemPosition()){
            rolling10Spinner.setSelection(old4thInRowRolling);// check standardarray
        } else if (position == rolling8Spinner.getSelectedItemPosition()){
            rolling8Spinner.setSelection(old4thInRowRolling);
        }
        old4thInRowRolling = position;

        position = rolling10Spinner.getSelectedItemPosition();

        if (position == rolling15Spinner.getSelectedItemPosition()) {
            rolling15Spinner.setSelection(old5thInRowRolling);
        } else if (position == rolling14Spinner.getSelectedItemPosition()){
            rolling14Spinner.setSelection(old5thInRowRolling);
        } else if (position == rolling13Spinner.getSelectedItemPosition()){
            rolling13Spinner.setSelection(old5thInRowRolling);
        } else if (position == rolling12Spinner.getSelectedItemPosition()){
            rolling12Spinner.setSelection(old5thInRowRolling);
        } else if (position == rolling8Spinner.getSelectedItemPosition()){
            rolling8Spinner.setSelection(old5thInRowRolling);
        }
        old5thInRowRolling = position;

        position = rolling8Spinner.getSelectedItemPosition();

        if (position == rolling15Spinner.getSelectedItemPosition()) {
            rolling15Spinner.setSelection(old6thInRowRolling);
        } else if (position == rolling14Spinner.getSelectedItemPosition()){
            rolling14Spinner.setSelection(old6thInRowRolling);
        } else if (position == rolling13Spinner.getSelectedItemPosition()){
            rolling13Spinner.setSelection(old6thInRowRolling);
        } else if (position == rolling12Spinner.getSelectedItemPosition()){
            rolling12Spinner.setSelection(old6thInRowRolling);
        } else if (position == rolling10Spinner.getSelectedItemPosition()){
            rolling10Spinner.setSelection(old6thInRowRolling);
        }
        old6thInRowRolling = position;
    }

    public int getRandomNumber(int diceAmount){

        CheckBox checkBox = view.findViewById(R.id.cbReroll1);
        boolean reroll1 = checkBox.isChecked();

        int total = 0;
        List<Integer> results = new ArrayList<Integer>();

        for(int i = 0; i < diceAmount; i++) {
            int result = (int)(Math.random() * 6 + 1);
            if(reroll1 && result == 1){
                result = (int)(Math.random() * 6 + 1);
            }
            results.add(result);
        }

        if(diceAmount > 3){
            while(results.size() > 3){
                results.remove(results.indexOf(Collections.min(results)));
            }
        }

        for(int i : results){
            total += i;
        }

        return total;
    }
}
