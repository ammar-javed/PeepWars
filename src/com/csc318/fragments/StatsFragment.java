package com.csc318.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc318.peepwars.R;


/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class StatsFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private String[] mStats;

    public StatsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_user_stats, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.drawer_options)[i];

//        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                        "drawable", getActivity().getPackageName());
        ListView mStatsList = (ListView) rootView.findViewById(R.id.user_stats);
        mStats = getResources().getStringArray(R.array.demo_stats);
        mStatsList.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_user_stats_cell, mStats));
        getActivity().setTitle(planet);
        return rootView;
    }
}
