package com.csc318.groupfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc318.peepwars.R;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class GroupHome extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private String[] mDemoGroupUsers;

    public GroupHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_home, container, false);
//        TextView dummyTextView = (TextView) rootView.findViewById(R.id.group_home_label);
//        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        // my addition, needs revision
        ListView mGroupUsersList = (ListView) rootView.findViewById(R.id.group_home);
        mDemoGroupUsers = getResources().getStringArray(R.array.demo_group_users);
        
        mGroupUsersList.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_group_home_cell, mDemoGroupUsers));
        return rootView;
    }
}