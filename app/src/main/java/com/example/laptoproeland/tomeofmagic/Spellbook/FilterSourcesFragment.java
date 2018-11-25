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

public class FilterSourcesFragment extends DialogFragment {

    LinearLayout frgLayoutSources;

    private static final String TAG = "MyActivity";

    public FilterSourcesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_sources, container, false);
        frgLayoutSources = view.findViewById(R.id.frgLayoutSources);

        if(AppData.getSourcesSelected() != null) {
            for (int i = 0; i < frgLayoutSources.getChildCount(); i++) {
                LinearLayout itemLayout = (LinearLayout) frgLayoutSources.getChildAt(i);
                for (int j = 0; j < itemLayout.getChildCount(); j++) {
                    View v = itemLayout.getChildAt(j);
                    if (v instanceof CheckBox) {
                        String source = ((TextView) itemLayout.getChildAt(j - 1)).getText().toString();
                        ((CheckBox) v).setChecked(AppData.getSourcesSelected().contains(convertToShort(source)));
                    }
                }
            }
        }
        return view;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {

        ArrayList<String> selectedSources = new ArrayList<>();

        for (int i = 0; i < frgLayoutSources.getChildCount(); i++) {
            LinearLayout itemLayout = (LinearLayout) frgLayoutSources.getChildAt(i);
            for (int j = 0; j < itemLayout.getChildCount(); j++) {
                View v = itemLayout.getChildAt(j);
                if(v instanceof CheckBox){
                    if(((CheckBox) v).isChecked()){
                        String source = ((TextView)itemLayout.getChildAt(j-1)).getText().toString();
                        selectedSources.add(convertToShort(source));
                    }
                }
            }
        }
        AppData.setSourcesSelected(selectedSources);
    }

    public String convertToShort(String long_source){

        String [] sources_long = getResources().getStringArray(R.array.sources_array_long);
        String [] sources_short = getResources().getStringArray(R.array.sources_array_short);

        for(int i = 0; i < sources_long.length; i++){
            if(sources_long[i].equals(long_source)){
                String short_source = sources_short[i];
                return short_source;
            }
        }
        return long_source;
    }

    public String convertToLong(String short_source){
        String [] sources_long = getResources().getStringArray(R.array.sources_array_long);
        String [] sources_short = getResources().getStringArray(R.array.sources_array_short);

        for(int i = 0; i < sources_short.length; i++){
            if(sources_short[i].equals(short_source)){
                String long_source = sources_long[i];
                return long_source;
            }
        }
        return short_source;
    }
}
