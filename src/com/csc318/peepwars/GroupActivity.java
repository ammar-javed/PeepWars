package com.csc318.peepwars;

import java.util.Locale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GroupActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            Fragment fragment;
            switch(position) {
            	case 0 : fragment = new GroupHomeFragment();
            			 break;
            	case 1 : fragment = new GroupConvFragment();
            			 break;
            	case 2 : fragment = new GroupStatsFragment();
   			 			 break;
            	default : fragment = new GroupHomeFragment();
            			  break;
            }
            Bundle args = new Bundle();
            args.putInt(GroupHomeFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class GroupHomeFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        private String[] mDemoGroupUsers;

        public GroupHomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_group_home, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.group_home_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            // my addition, needs revision
            ListView mGroupUsersList = (ListView) rootView.findViewById(R.id.group_home);
            mDemoGroupUsers = getResources().getStringArray(R.array.demo_group_users);
            
            mGroupUsersList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_group_home_cell, mDemoGroupUsers));
            return rootView;
        }
    }
    
    public static class GroupConvFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        private String[] mDemoGroupConv;

        public GroupConvFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_group_conversations, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.group_conv_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            // my addition, needs revision
            ListView mGroupUsersList = (ListView) rootView.findViewById(R.id.group_conversations);
            mDemoGroupConv = getResources().getStringArray(R.array.demo_group_convs);
            
            mGroupUsersList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_group_conversations_cell, mDemoGroupConv));
            return rootView;
        }
    }
    
    public static class GroupStatsFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        private String[] mDemoGroupStats;

        public GroupStatsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_group_stats, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.group_stats_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            // my addition, needs revision
            ListView mGroupUsersList = (ListView) rootView.findViewById(R.id.group_stats);
            mDemoGroupStats = getResources().getStringArray(R.array.demo_group_stats);
            
            mGroupUsersList.setAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_group_stats_cell, mDemoGroupStats));
            return rootView;
        }
    }
}
