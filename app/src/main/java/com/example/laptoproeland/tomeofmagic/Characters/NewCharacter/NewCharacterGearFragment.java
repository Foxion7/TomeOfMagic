package com.example.laptoproeland.tomeofmagic.Characters.NewCharacter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laptoproeland.tomeofmagic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCharacterGearFragment extends Fragment {


    public NewCharacterGearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_character_gear, container, false);
    }

}
