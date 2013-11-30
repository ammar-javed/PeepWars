package com.csc318.fragments;

import com.csc318.peepwars.R;
import com.csc318.peepwars.R.array;
import com.csc318.peepwars.R.id;
import com.csc318.peepwars.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class FeedFragment extends Fragment {
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

//        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                        "drawable", getActivity().getPackageName());
        ListView mFeedList = (ListView) rootView.findViewById(R.id.news_feed);
        mDemoFeed = getResources().getStringArray(R.array.demo_feed);
        mFeedList.setAdapter(new ArrayAdapter<String>(inflater.getContext(),
                R.layout.fragment_feed_item, mDemoFeed));
        getActivity().setTitle(planet);
        return rootView;
    }
}