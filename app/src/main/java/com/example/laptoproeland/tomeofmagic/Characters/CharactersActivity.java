package com.example.laptoproeland.tomeofmagic.Characters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.Characters.NewCharacter.NewCharacterActivity;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Background;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Race;
import com.example.laptoproeland.tomeofmagic.Characters.Properties.Talent;
import com.example.laptoproeland.tomeofmagic.DrawerActivity;
import com.example.laptoproeland.tomeofmagic.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CharactersActivity extends DrawerActivity {

    private ArrayList <Character> characters;
    private CharacterAdapter adapter;
    private ArrayList<Race> races  = new ArrayList<>();
    private ArrayList<Talent> talents = new ArrayList<>();
    private ArrayList<Background> backgrounds = new ArrayList<>();

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_characters, frameLayout);
        setTitle("Characters");

        // Hides TabLayout from actionbar
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // to check current activity in the navigation drawer
        navigationView.getMenu().getItem(1).setChecked(true);

        characters = new ArrayList<>();

        LoadRaces();
        LoadTalents();
        LoadBackgrounds();

        // Dummydata
        Character testChar01 = new Character("Phyrrus", AppData.getRace("Elf").getSubrace("High Elf"), new Class("Wizard", 1), 10, 10, 10, 10, 10, 10);
        Character testChar02 = new Character("Gorro", AppData.getRace("Half-orc"), new Class("Barbarian", 1), 10, 10, 10, 10, 10, 10);
        Character testChar03 = new Character("Ereon", AppData.getRace("Human").getSubrace("Regular Human"), new Class("Paladin", 1), 10, 10, 10, 10, 10, 10);
        Character testChar04 = new Character("Hjollmir", AppData.getRace("Dwarf").getSubrace("Hill Dwarf"), new Class("Cleric", 1), 10, 10, 10, 10, 10, 10);
        Character testChar05 = new Character("Fengur", AppData.getRace("Elf").getSubrace("Wood Elf"), new Class("Ranger", 1), 10, 10, 10, 10, 10, 10);

        testChar01.levelUp(new Class("Fighter"));

        addCharacter(testChar01);
        addCharacter(testChar02);
        addCharacter(testChar03);
        addCharacter(testChar04);
        addCharacter(testChar05);

        if(races != null) {
            InitializeListView();
        }
    }

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void InitializeListView(){
        // Prepares adapter and display of spells
        adapter = new CharacterAdapter(this, characters);
        final ListView listView = findViewById(R.id.lvCharacters);
        listView.setAdapter(adapter);

        // Handling of click on spell
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Character currentCharacter = characters.get(position);
                OpenCharacterDescription(currentCharacter);
            }
        });

        Button btnNewCharacter = findViewById(R.id.btnNewCharacter);

        btnNewCharacter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CharactersActivity.this, NewCharacterActivity.class);
                CharactersActivity.this.startActivity(myIntent);
            }
        });

    }

    // Opens activity with currently selected character info
    public void OpenCharacterDescription(Character character){
        Intent intent = new Intent(this, CharacterDescriptionActivity.class);
        intent.putExtra("SelectedCharacter", character);
        startActivity(intent);
    }

    public void LoadRaces(){

        String raceDataJson = loadJSONFromAsset(this, "RaceData.json");
        try {
            AddJSONDataToRaces(raceDataJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppData.setRaces(races);
    }

    public void LoadTalents() {

        String talentDataJson = loadJSONFromAsset(this, "TalentData.json");
        try {
            AddJSONDataToTalents(talentDataJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppData.setTalents(talents);
    }

    public void LoadBackgrounds(){

        String backgroundDataJson = loadJSONFromAsset(this, "BackgroundData.json");
        try {
            AddJSONDataToBackgrounds(backgroundDataJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppData.setBackgrounds(backgrounds);
    }

    // Retrieves data from json
    public String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
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

    public void AddJSONDataToRaces(String jstring) throws JSONException {

        JSONObject object = new JSONObject(jstring);
        JSONArray jArray  = object.getJSONArray("races");

        for (int i = 0; i < jArray.length(); i++)
        {
            JSONObject jsonObject = jArray.getJSONObject(i);
            JsonParser parser = new JsonParser();
            JsonElement mJson =  parser.parse(String.valueOf(jsonObject));

            Gson gson = new Gson();
            Race race = gson.fromJson(mJson, Race.class);
            races.add(race);
        }
    }

    public void AddJSONDataToTalents(String jstring) throws JSONException {

        JSONObject object = new JSONObject(jstring);
        JSONArray jArray  = object.getJSONArray("talents");

        for (int i = 0; i < jArray.length(); i++)
        {
            JSONObject jsonObject = jArray.getJSONObject(i);
            JsonParser parser = new JsonParser();
            JsonElement mJson =  parser.parse(String.valueOf(jsonObject));

            Gson gson = new Gson();
            Talent talent = gson.fromJson(mJson, Talent.class);
            talents.add(talent);
        }
    }

    public void AddJSONDataToBackgrounds(String jstring) throws JSONException {

        JSONObject object = new JSONObject(jstring);
        JSONArray jArray  = object.getJSONArray("backgrounds");

        for (int i = 0; i < jArray.length(); i++)
        {
            JSONObject jsonObject = jArray.getJSONObject(i);
            JsonParser parser = new JsonParser();
            JsonElement mJson =  parser.parse(String.valueOf(jsonObject));

            Gson gson = new Gson();
            Background background = gson.fromJson(mJson, Background.class);

            fillBackground(background);
            backgrounds.add(background);
        }
    }

    private void fillBackground(Background background){
        if(talents != null){
            if("Acolyte".equals(background.getName())){
                background.addTalent(AppData.getTalent("Insight"));
                background.addTalent(AppData.getTalent("Religion"));
                background.addTalent(AppData.getTalent("One of your choice", Talent.Type.Language));
                background.addTalent(AppData.getTalent("One of your choice", Talent.Type.Language));
            } else if ("Charlatan".equals(background.getName())){
                background.addTalent(AppData.getTalent("Deception"));
                background.addTalent(AppData.getTalent("Sleight of Hand"));
                background.addTalent(AppData.getTalent("Disguise kit"));
                background.addTalent(AppData.getTalent("Forgery kit"));
            } else if ("Criminal".equals(background.getName())){
                background.addTalent(AppData.getTalent("Deception"));
                background.addTalent(AppData.getTalent("Stealth"));
                background.addTalent(AppData.getTalent("One type of gaming set"));
                background.addTalent(AppData.getTalent("Thieves' tools"));
            } else if ("Entertainer".equals(background.getName())){
                background.addTalent(AppData.getTalent("Acrobatics"));
                background.addTalent(AppData.getTalent("Performance"));
                background.addTalent(AppData.getTalent("Disguise kit"));
                background.addTalent(AppData.getTalent("One type of musical instrument"));
            } else if ("Folk Hero".equals(background.getName())){
                background.addTalent(AppData.getTalent("Animal Handling"));
                background.addTalent(AppData.getTalent("Survival"));
                background.addTalent(AppData.getTalent("One type of artisan's tools"));
                background.addTalent(AppData.getTalent("Vehicles (land)"));
            } else if ("Guild Artisan".equals(background.getName())){
                background.addTalent(AppData.getTalent("Insight"));
                background.addTalent(AppData.getTalent("Persuasion"));
                background.addTalent(AppData.getTalent("One type of artisan's tools"));
                background.addTalent(AppData.getTalent("One of your choice"));
            } else if ("Hermit".equals(background.getName())){
                background.addTalent(AppData.getTalent("Medicine"));
                background.addTalent(AppData.getTalent("Religion"));
                background.addTalent(AppData.getTalent("Herbalism kit"));
                background.addTalent(AppData.getTalent("One of your choice"));
            } else if ("Noble".equals(background.getName())){
                background.addTalent(AppData.getTalent("History"));
                background.addTalent(AppData.getTalent("Persuasion"));
                background.addTalent(AppData.getTalent("One type of gaming set"));
                background.addTalent(AppData.getTalent("One of your choice"));
            } else if ("Outlander".equals(background.getName())){
                background.addTalent(AppData.getTalent("Athletics"));
                background.addTalent(AppData.getTalent("Survival"));
                background.addTalent(AppData.getTalent("One type of musical instrument"));
                background.addTalent(AppData.getTalent("One of your choice"));
            } else if ("Sage".equals(background.getName())){
                background.addTalent(AppData.getTalent("Arcana"));
                background.addTalent(AppData.getTalent("History"));
                background.addTalent(AppData.getTalent("One of your choice"));
                background.addTalent(AppData.getTalent("One of your choice"));
            } else if ("Sailor".equals(background.getName())){
                background.addTalent(AppData.getTalent("Athletics"));
                background.addTalent(AppData.getTalent("Perception"));
                background.addTalent(AppData.getTalent("Navigator's tools"));
                background.addTalent(AppData.getTalent("Vehicles (water)"));
            } else if ("Soldier".equals(background.getName())){
                background.addTalent(AppData.getTalent("Athletics"));
                background.addTalent(AppData.getTalent("Intimidation"));
                background.addTalent(AppData.getTalent("One type of gaming set"));
                background.addTalent(AppData.getTalent("Vehicles (land)"));
            }
        }
    }
}
