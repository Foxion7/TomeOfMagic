package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        if(position == 0){
            fragment = new NewCharacterRaceFragment();
        } else if (position == 1){
            fragment = new NewCharacterClassFragment();
        } else if (position == 2){
            fragment = new NewCharacterBackgroundFragment();
        } else if (position == 3){
            fragment = new NewCharacterAbilitiesFragment();
        } else if (position == 4){
            fragment = new NewCharacterGearFragment();
        } else {
            fragment = new NewCharacterFinishFragment();
        }

        position += 1;
        Bundle bundle = new Bundle();
        bundle.putString("tabPosition", "" + position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String tabName = "[TabName]";

        if(position == 0){
            tabName = "Race";
        } else if (position == 1){
            tabName = "Class";
        } else if (position == 2){
            tabName = "Back\nground";
        } else if (position == 3){
            tabName = "Abilities";
        } else if (position == 4){
            tabName = "Gear";
        } else if (position == 5){
            tabName = "Finish";
        }

        return tabName;
    }
}
