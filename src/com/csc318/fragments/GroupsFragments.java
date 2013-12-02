package com.csc318.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.csc318.peepwars.FeedActivity;
import com.csc318.peepwars.Group;
import com.csc318.peepwars.GroupActivity;
import com.csc318.peepwars.NewGroupActivity;
import com.csc318.peepwars.R;
import com.csc318.utilities.GroupsListAdapter;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class GroupsFragments extends Fragment {
	
    public static final String ARG_PLANET_NUMBER = "planet_number";

    //private GroupsListAdapter mAdapter;

    public GroupsFragments() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_groups, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String currentSelectedOption = getResources().getStringArray(R.array.drawer_options)[i];
        
        CharSequence name = getArguments().getCharSequence("newGroup");
        if (name != null){
        	((FeedActivity)getActivity()).addGroup(new Group(name.toString()));
        }
        

        
        //Populate action bar
        setHasOptionsMenu(true);
        
        ListView mGroupList = (ListView) rootView.findViewById(R.id.groups_list);
        mGroupList.setAdapter(new GroupsListAdapter(rootView.getContext(), R.layout.fragment_groups_cell, ((FeedActivity)getActivity()).getmGroups()));
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Fragment fragment;
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new:
                fragment = new NewGroupActivity();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
