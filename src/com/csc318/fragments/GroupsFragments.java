package com.csc318.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.csc318.peepwars.Group;
import com.csc318.peepwars.GroupActivity;
import com.csc318.peepwars.R;
import com.csc318.utilities.GroupsListAdapter;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class GroupsFragments extends Fragment {
	
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private Group[] mGroups;
    //private GroupsListAdapter mAdapter;

    public GroupsFragments() {
        mGroups = new Group[]{new Group("Team winners"), new Group("Gorillaz"), new Group("Tugee"), new Group("YoloWin"),
        		new Group ("CSFaculty"), new Group("CSStudents"), new Group("CSSU")};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_groups, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String currentSelectedOption = getResources().getStringArray(R.array.drawer_options)[i];
        
        //Populate action bar
        setHasOptionsMenu(true);

//        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                        "drawable", getActivity().getPackageName());
        ListView mGroupList = (ListView) rootView.findViewById(R.id.groups_list);
        //mAdapter = new GroupsListAdapter(rootView.getContext(), R.layout.fragment_groups_cell, mGroups);
        mGroupList.setAdapter(new GroupsListAdapter(rootView.getContext(), R.layout.fragment_groups_cell, mGroups));
        // setting the listener to enable clickable groups
		mGroupList.setOnItemClickListener(new GroupItemClickListener());
        getActivity().setTitle(currentSelectedOption);
        return rootView;
    }
    
    @Override
    public void onCreateOptionsMenu(
          Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.add_new_button, menu);
    }
}

	/* The click listner for ListView in the groups fragment */
class GroupItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        selectItem(position);
    	  switchToGroupPage(position, view);
    	  }

	private static void switchToGroupPage(int position, View view) {
		// currently not using position, it might be used later
		Intent switchToGroup = new Intent(view.getContext(), GroupActivity.class);
		view.getContext().startActivity(switchToGroup);
		}
	}
