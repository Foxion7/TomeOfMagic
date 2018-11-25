package com.example.laptoproeland.tomeofmagic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.example.laptoproeland.tomeofmagic.Characters.CharactersActivity;
import com.example.laptoproeland.tomeofmagic.Spellbook.Spell;
import com.example.laptoproeland.tomeofmagic.Spellbook.SpellbookActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends DrawerActivity {

    ArrayList<Spell> spells = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, frameLayout);
        setTitle("Main");

        // Hides TabLayout from actionbar
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);

        if(spells.isEmpty()) {
            LoadSpells();
        }
    }

    public void openCharacters(View view) {
        Intent intent = new Intent(this, CharactersActivity.class);
        startActivity(intent);
    }

    public void openSpellbook(View view) {
        Intent intent = new Intent(this, SpellbookActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // to check current activity in the navigation drawer
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    public void LoadSpells(){

        if(AppData.gotSpells()){
            spells = AppData.getSpells();
        } else {
            String spelldataJson = loadJSONFromAsset(this);
            try {
                AddJSONDataToSpells(spelldataJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AppData.setSpells(spells);
        }
    }

    // Retrieves spelldata from json
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("SpellbookData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // Fills list with spells
    public void AddJSONDataToSpells(String jstring) throws JSONException {

        JSONObject object = new JSONObject(jstring);
        JSONArray jArray  = object.getJSONArray("spells");

        for (int i = 0; i < jArray.length(); i++)
        {
            JSONObject jsonObject = jArray.getJSONObject(i);
            JsonParser parser = new JsonParser();
            JsonElement mJson =  parser.parse(String.valueOf(jsonObject));

            Gson gson = new Gson();
            Spell spell = gson.fromJson(mJson, Spell.class);
            spells.add(spell);
        }
    }


}
