package com.csc318.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc318.peepwars.R;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class PeepsFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private String[] mDemoNames;

    public PeepsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
		View rootView = inflater.inflate(R.layout.fragment_peeps, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.drawer_options)[i];
        
        //Populate action bar
        setHasOptionsMenu(true);

//        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                        "drawable", getActivity().getPackageName());
        ListView mFeedList = (ListView) rootView.findViewById(R.id.peeps);
        mDemoNames = getResources().getStringArray(R.array.peep_names);
        mFeedList.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_peeps_cell, mDemoNames));
        getActivity().setTitle(planet);
        return rootView;
    }
    
    @Override
    public void onCreateOptionsMenu(
          Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.add_new_button, menu);
    }
}