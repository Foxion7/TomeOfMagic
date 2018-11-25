package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.laptoproeland.tomeofmagic.DrawerActivity;
import com.example.laptoproeland.tomeofmagic.R;

public class NewCharacterActivity extends DrawerActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_new_character, frameLayout);
        android.support.v7.app.ActionBar ab = this.getSupportActionBar();
        ab.setTitle("Create a new Character");

        viewPager = findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }
}
