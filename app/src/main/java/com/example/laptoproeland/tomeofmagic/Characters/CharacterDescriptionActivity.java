package com.example.laptoproeland.tomeofmagic.Characters;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.DrawerActivity;
import com.example.laptoproeland.tomeofmagic.R;

public class CharacterDescriptionActivity extends DrawerActivity {

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_character_description);

        getLayoutInflater().inflate(R.layout.activity_character_description, frameLayout);
        setTitle("CharacterDescription");

        // Hides TabLayout from actionbar
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);

        // Gets spell from list
        Intent i = getIntent();
        character = (Character)i.getSerializableExtra("SelectedCharacter");

        android.support.v7.app.ActionBar ab = this.getSupportActionBar();

        if(character != null) {
            if (ab != null) {
                ab.setTitle(character.getName());
                ab.setSubtitle(character.getTitle());
            }
            FillDescription();
        }
    }

    public void FillDescription(){
        // May need an unique variable
        TextView tv = findViewById(R.id.tvName);
        tv.setText(Html.fromHtml("<b>Name: </b>" + character.getName()));

        tv = findViewById(R.id.tvRace);
        tv.setText(Html.fromHtml("<b>Race: </b>" + character.getRace().getName()));

        tv = findViewById(R.id.tvClassesAndLevels);
        tv.setText(Html.fromHtml("<b>Class: </b>" + character.getClassesAndLevels()));

//        tv = findViewById(R.id.tvDescription);
//        tv.setText(Html.fromHtml(formatText(character.getDescription())));

    }

    public String formatText(String description){
        description = description.replaceAll("\\*","\u2022 ");
        return description;
    }
}
