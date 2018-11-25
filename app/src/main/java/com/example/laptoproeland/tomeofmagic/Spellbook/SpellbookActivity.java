package com.example.laptoproeland.tomeofmagic.Spellbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.DrawerActivity;
import com.example.laptoproeland.tomeofmagic.R;

import java.io.Serializable;
import java.util.ArrayList;

public class SpellbookActivity extends DrawerActivity {

    ArrayList<Spell> spells = new ArrayList<>();

    private SpellAdapter adapter;
    private Spinner classSpinner;
    private Spinner levelSpinner;

    ArrayAdapter<CharSequence> classSpinAdapter;
    ArrayAdapter<CharSequence> levelSpinAdapter;

    private String selectedClass = "All classes";
    private String selectedLevel = "All levels";
    private String query = "";

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_spellbook, frameLayout);
        setTitle("Spellbook");

        // Hides TabLayout from actionbar
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);

        spells.addAll(AppData.getSpells());
        FillSpinners();
        InitializeListView();

        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedLevel = parent.getItemAtPosition(position).toString();
                {
                    AppData.setLevelSelected(selectedLevel);
                    FilterSpells();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedClass = parent.getItemAtPosition(position).toString();
                {
                    AppData.setClassSelected(selectedClass);
                    FilterSpells();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        ImageView filter = findViewById(R.id.filterImage);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FilterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Opens activity with currently selected spell info
    public void OpenSpellDescription(Spell spell){
        Intent intent = new Intent(this, SpellDescriptionActivity.class);
        intent.putExtra("SelectedSpell", (Serializable) spell);
        startActivity(intent);
    }

    public void InitializeListView(){
        // Prepares adapter and display of spells
        adapter = new SpellAdapter(this, spells);
        final ListView listView = findViewById(R.id.lvSpells);
        listView.setAdapter(adapter);

        // Handling of click on spell
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spell currentSpell = spells.get(position);
                OpenSpellDescription(currentSpell);
            }
        });

        if(!AppData.getClassSelected().equals("All classes")){
            int spinnerPosition = classSpinAdapter.getPosition(AppData.getClassSelected());
            classSpinner.setSelection(spinnerPosition);
        }

        if(!AppData.getLevelSelected().equals("All levels")){
            int spinnerPosition = levelSpinAdapter.getPosition(AppData.getLevelSelected());
            levelSpinner.setSelection(spinnerPosition);
        }
    }

    // Fills level and class spinners
    public void FillSpinners(){
        // Fill class spinner
        classSpinner = findViewById(R.id.class_spinner);
        classSpinAdapter = ArrayAdapter.createFromResource(this,
                R.array.spellbook_classes_array, android.R.layout.simple_spinner_item);
        classSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classSpinAdapter);

        // Fill level spinner
        levelSpinner = findViewById(R.id.level_spinner);
        levelSpinAdapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        levelSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelSpinAdapter);
    }

    public void FilterSpells(){
        if(query.equals("") || query == null) {
            adapter.getFilter().filter(selectedClass + "|" + selectedLevel);
        } else {
            adapter.getFilter().filter(selectedClass + "|" + selectedLevel + "|" + query);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(2).setChecked(true);
        FilterSpells();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                query = searchQuery;
                FilterSpells();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                query = searchQuery;
                FilterSpells();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
