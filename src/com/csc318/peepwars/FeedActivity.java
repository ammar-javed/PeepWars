package com.csc318.peepwars;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.csc318.fragments.GroupsFragments;
import com.csc318.utilities.Drawer;
// import android.app.FragmentTransaction;

public class FeedActivity extends Activity {
	
	private Drawer mDrawer;
    private ArrayList<Group> mGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        mDrawer = new Drawer(this, savedInstanceState);
        mGroups = new ArrayList<Group>();
        
        mGroups.add(new Group("Gorillaz"));
        mGroups.add(new Group("Team winners"));
        mGroups.add(new Group("Tugee"));
        mGroups.add(new Group("YoloWin"));
        mGroups.add(new Group("CSFaculty"));
        mGroups.add(new Group("CSStudents"));
        mGroups.add(new Group("CSSU"));
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawer.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
    	super.onPostCreate(savedInstanceState);
    	mDrawer.onPostCreate(savedInstanceState);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig){
    	super.onConfigurationChanged(newConfig);
    	mDrawer.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    public void confirmGroup(View view){
    	Fragment fragment = new GroupsFragments();
    	
        Bundle args = new Bundle();
        args.putCharSequence("newGroup", ((EditText) findViewById(R.id.name_field)).getText());
        fragment.setArguments(args);
        
        FragmentManager fragmentManager = getFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragment.getClass().getName(), 0);
        
        if(!fragmentPopped){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getName()).commit();
        }
    }

	public ArrayList<Group> getmGroups() {
		return mGroups;
	}

	public void addGroup(Group group) {
		mGroups.add(group);
	}

}
