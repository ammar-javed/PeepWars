package com.csc318.utilities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc318.fragments.CalendarFragment;
import com.csc318.fragments.FeedFragment;
import com.csc318.fragments.GroupsFragments;
import com.csc318.fragments.MessagesFragment;
import com.csc318.fragments.PeepsFragment;
import com.csc318.fragments.SettingsFragment;
import com.csc318.fragments.StatsFragment;
import com.csc318.peepwars.NewGroupActivity;
import com.csc318.peepwars.R;

public class Drawer {
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Activity mActivity;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private Bundle mSavedInstanceState;
    
    public Drawer(Activity activity, Bundle savedInstanceState){
        //TODO: Change title to email address used during login
    	mActivity = activity;
        mTitle = mDrawerTitle = "PeepWars";
        mPlanetTitles = mActivity.getResources().getStringArray(R.array.drawer_options);
        mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) mActivity.findViewById(R.id.left_drawer);
        mSavedInstanceState = savedInstanceState;
        initDrawer();
        initDrawerToggle();
    }
    
    private void initDrawer(){
//      // set a custom shadow that overlays the main content when the drawer opens
//      mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
      // set up the drawer's list view with items and click listener
      mDrawerList.setAdapter(new ArrayAdapter<String>(mActivity,
              R.layout.drawer_list_item, mPlanetTitles));
      mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

      // enable ActionBar app icon to behave as action to toggle nav drawer
      mActivity.getActionBar().setDisplayHomeAsUpEnabled(true);
      mActivity.getActionBar().setHomeButtonEnabled(true);
    }
    
    private void initDrawerToggle(){
        // ActionBarDrawerToggle ties together the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                mActivity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                mActivity.getActionBar().setTitle(mTitle);
                mActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
            	mActivity.getActionBar().setTitle(mDrawerTitle);
            	mActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (mSavedInstanceState == null) {
            selectItem(0);
        }
    }
    

    /* Called whenever we call invalidateOptionsMenu() */
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return mActivity.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        Fragment fragment;
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_new:
            fragment = new NewGroupActivity();
            break;
        default:
            return false;
        }
        FragmentManager fragmentManager = mActivity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        return true;
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
       	Fragment fragment;
    	
    	switch (position){
    	case 0:
    		fragment = new FeedFragment();
    		break;
    	case 1:
    		fragment = new MessagesFragment();
    		break;
    	case 2:
    		fragment = new CalendarFragment();
    		break;
    	case 3:
    		fragment = new GroupsFragments();
    		break;
    	case 4:
    		fragment = new PeepsFragment();
    		break;
    	case 5:
    		fragment = new StatsFragment();
    		break;
    	case 6:
    		fragment = new SettingsFragment();
    		break;
    	default:
    		fragment = new FeedFragment();
    	}
        Bundle args = new Bundle();
        args.putInt(FeedFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = mActivity.getFragmentManager();
        
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragment.getClass().getName(), 0);
        		
        if(!fragmentPopped){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getName()).commit();
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        mActivity.getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    public void onPostCreate(Bundle savedInstanceState) {
        //mActivity.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
