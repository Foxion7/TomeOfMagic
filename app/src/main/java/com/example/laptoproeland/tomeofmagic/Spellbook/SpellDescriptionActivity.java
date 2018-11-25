package com.example.laptoproeland.tomeofmagic.Spellbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.DrawerActivity;
import com.example.laptoproeland.tomeofmagic.R;
import com.example.laptoproeland.tomeofmagic.Spellbook.Spell;

import static android.content.ContentValues.TAG;

public class SpellDescriptionActivity extends DrawerActivity {

    private Spell spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_spell_description, frameLayout);
        setTitle("SpellDescription");

        // Hides TabLayout from actionbar
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);

        // Gets spell from list
        Intent i = getIntent();
        spell = (Spell)i.getSerializableExtra("SelectedSpell");

        if(spell != null) {
            android.support.v7.app.ActionBar ab = this.getSupportActionBar();
            if (ab != null) {
                ab.setTitle(spell.getName());
                ab.setSubtitle("Level " + spell.getLevel() + " " + spell.getSchool());
            }
            FillDescription();
        }
    }

    public void FillDescription(){

        TextView tv = findViewById(R.id.tvCastingTime);
        tv.setText(Html.fromHtml("<b>Casting time: </b>" + spell.getCasting_time()));

        tv = findViewById(R.id.tvRange);
        tv.setText(Html.fromHtml("<b>Range: </b>" + spell.getRange()));

        tv = findViewById(R.id.tvComponents);
        tv.setText(Html.fromHtml("<b>Components: </b>" + spell.getComponents()));

        tv = findViewById(R.id.tvDuration);
        tv.setText(Html.fromHtml("<b>Duration: </b>" + spell.getDuration()));

        tv = findViewById(R.id.tvDescription);
        tv.setText(Html.fromHtml(formatText(spell.getDescription())));

        tv = findViewById(R.id.tvAtHigherLevels);
        tv.setText(Html.fromHtml("<b>At Higher Levels: </b>" + spell.getAt_higher_levels()));

        tv = findViewById(R.id.tvClasses);
        tv.setText(Html.fromHtml("<b>Learnable by: </b>" + spell.getClasses()));

        tv = findViewById(R.id.tvSources);
        tv.setText(Html.fromHtml("<b>Source: </b>" + spell.getSourcesText()));
    }

    public String formatText(String description){
        description = description.replaceAll("\\*","\u2022 ");
        return description;
    }
}
