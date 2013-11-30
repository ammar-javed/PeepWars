package com.csc318.groupfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc318.peepwars.R;

public class GroupStats extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private String[] mDemoGroupStats;

    public GroupStats() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_stats, container, false);
//        TextView dummyTextView = (TextView) rootView.findViewById(R.id.group_stats_label);
//        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        // my addition, needs revision
        ListView mGroupUsersList = (ListView) rootView.findViewById(R.id.group_stats);
        mDemoGroupStats = getResources().getStringArray(R.array.demo_group_stats);
        
        mGroupUsersList.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_group_stats_cell, mDemoGroupStats));
        return rootView;
    }
}