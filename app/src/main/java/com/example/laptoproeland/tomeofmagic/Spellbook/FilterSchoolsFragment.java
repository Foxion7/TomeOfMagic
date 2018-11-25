package com.example.laptoproeland.tomeofmagic.Spellbook;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.laptoproeland.tomeofmagic.AppData;
import com.example.laptoproeland.tomeofmagic.R;

import java.util.ArrayList;

public class FilterSchoolsFragment extends DialogFragment {

    LinearLayout frgLayoutSchools;

    public FilterSchoolsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_schools, container, false);
        frgLayoutSchools = view.findViewById(R.id.frgLayoutSchools);

        if(AppData.getSchoolsSelected() != null) {
            for (int i = 0; i < frgLayoutSchools.getChildCount(); i++) {
                LinearLayout itemLayout = (LinearLayout) frgLayoutSchools.getChildAt(i);
                for (int j = 0; j < itemLayout.getChildCount(); j++) {
                    View v = itemLayout.getChildAt(j);
                    if (v instanceof CheckBox) {
                        String school = ((TextView) itemLayout.getChildAt(j - 1)).getText().toString();
                        school = school.substring(0, school.length() - 1);
                        ((CheckBox) v).setChecked(AppData.getSchoolsSelected().contains(school));
                    }
                }
            }
        }
        return view;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {

        ArrayList<String> selectedSchools = new ArrayList<>();

        for (int i = 0; i < frgLayoutSchools.getChildCount(); i++) {
            LinearLayout itemLayout = (LinearLayout) frgLayoutSchools.getChildAt(i);
            for (int j = 0; j < itemLayout.getChildCount(); j++) {
                View v = itemLayout.getChildAt(j);
                if(v instanceof CheckBox){
                    if(((CheckBox) v).isChecked()){
                        String school = ((TextView)itemLayout.getChildAt(j-1)).getText().toString();
                        selectedSchools.add(school.substring(0, school.length() - 1));
                    }
                }
            }
        }
        AppData.setSchoolsSelected(selectedSchools);
    }
}
