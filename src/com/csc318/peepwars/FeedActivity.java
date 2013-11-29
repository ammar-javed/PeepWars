package com.csc318.peepwars;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
// import android.app.FragmentTransaction;

public class FeedActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
//    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
//        fm = getFragmentManager();

        //TODO: Change title to email address used during login
        mTitle = mDrawerTitle = "PeepWars";
        mPlanetTitles = getResources().getStringArray(R.array.drawer_options);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

//        // set a custom shadow that overlays the main content when the drawer opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
//        case R.id.action_websearch:
//            // create intent to perform web search for this planet
//            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
//            // catch event that there's no activity to handle intent
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
//            }
//            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
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
    	//TODO: Remove log
    	Log.w("DrawerItemClick", "" + position);
    	Fragment fragment;
    	
    	switch (position){
    	case 0:
    		fragment = new FeedFragment();
//    		fm.beginTransaction().addToBackStack("FeedFragment").commit();
    		break;
    	case 1:
    		fragment = new MessagesFragment();
//    		fm.beginTransaction().addToBackStack("MessagesFragment").commit();
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

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class FeedFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        private String[] mDemoFeed;

        public FeedFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.drawer_options)[i];

//            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
            ListView mFeedList = (ListView) rootView.findViewById(R.id.news_feed);
            mDemoFeed = getResources().getStringArray(R.array.demo_feed);
            mFeedList.setAdapter(new ArrayAdapter<String>(inflater.getContext(),
                    R.layout.fragment_feed_item, mDemoFeed));
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class MessagesFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        private String[] mDemoNames;

        public MessagesFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_conversations, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.drawer_options)[i];

//            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
            ListView mFeedList = (ListView) rootView.findViewById(R.id.conversations);
            mDemoNames = getResources().getStringArray(R.array.peep_names);
            //TODO: Remove logs
            Log.w("Layout", "" + R.layout.fragment_feed_item);
            Log.w("String Array" , "" + mDemoNames);
            Log.w("Activity Context", "" + getActivity());
            mFeedList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_conversations_cell, mDemoNames));
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PeepsFragment extends Fragment {
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

//            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
            ListView mFeedList = (ListView) rootView.findViewById(R.id.peeps);
            mDemoNames = getResources().getStringArray(R.array.peep_names);
            mFeedList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_peeps_cell, mDemoNames));
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class StatsFragment extends Fragment {
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

//            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
            ListView mStatsList = (ListView) rootView.findViewById(R.id.user_stats);
            mStats = getResources().getStringArray(R.array.demo_stats);
            mStatsList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_user_stats_cell, mStats));
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class GroupsFragments extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        private String[] mGroups;

        public GroupsFragments() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_groups, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.drawer_options)[i];

//            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
            ListView mGroupList = (ListView) rootView.findViewById(R.id.groups_list);
            mGroups = getResources().getStringArray(R.array.demo_groups);
            mGroupList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_groups_cell, mGroups));
            // setting the listener to enable clickable groups
			mGroupList.setOnItemClickListener(new GroupItemClickListener());
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    /* The click listner for ListView in the groups fragment */
    private static class GroupItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            selectItem(position);
        	  switchToGroupPage(position, view);
        }
    }
    
    private static void switchToGroupPage(int position, View view) {
    	// currently not using position, it might be used later
    	Intent switchToGroup = new Intent(view.getContext(), GroupActivity.class);
    	view.getContext().startActivity(switchToGroup);
    }
    
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class SettingsFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        private String[] mDemoSettings;

        public SettingsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_account_settings, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.drawer_options)[i];

//            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
            ListView mFeedList = (ListView) rootView.findViewById(R.id.settings);
            mDemoSettings = getResources().getStringArray(R.array.demo_settings);
            mFeedList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_account_settings_cell, mDemoSettings));
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    public static class CalendarFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public CalendarFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_user_calendar, container, false);
 
            return rootView;
        }
    }
}
