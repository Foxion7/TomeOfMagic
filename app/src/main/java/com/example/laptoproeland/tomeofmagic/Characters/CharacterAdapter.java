package com.example.laptoproeland.tomeofmagic.Characters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.R;

import java.util.ArrayList;

public class CharacterAdapter extends ArrayAdapter<Character>{

    private ArrayList<Character> characterList = new ArrayList<>();


    public CharacterAdapter(Context context, ArrayList<Character> characters) {
        super(context, 0, characters);
        characterList.addAll(characters);
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Character character = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.character_listitem, parent, false);
        }

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvRace = view.findViewById(R.id.tvRace);
        TextView tvClassesAndLevels = view.findViewById(R.id.tvClassesAndLevels);
        ImageView ivPicture = view.findViewById(R.id.ivPicture);

        if (character != null) {
            tvName.setText(character.getName());
            tvTitle.setText(character.getTitle());
            if(character.getRace() != null) {
                tvRace.setText(character.getRace().getName());
            }
            tvClassesAndLevels.setText(character.getClassesAndLevels());
        }

        return view;
    }
}
