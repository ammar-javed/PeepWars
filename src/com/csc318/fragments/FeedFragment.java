package com.csc318.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csc318.peepwars.NewsItem;
import com.csc318.peepwars.R;
import com.csc318.utilities.FeedAdapter;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class FeedFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private NewsItem[] mDemoFeed;
    private FeedAdapter fAdapter;

    public FeedFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.drawer_options)[i];
        
        ListView mFeedList = (ListView) rootView.findViewById(R.id.news_feed);
        
        mDemoFeed = new NewsItem[]{
        		new NewsItem(0, "hi"),
        		new NewsItem(0, ""),
        		new NewsItem(0, ""),
        		new NewsItem(0, ""),
        		new NewsItem(0, ""),
        		new NewsItem(0, "")
        };
        String[] news = getResources().getStringArray(R.array.demo_feed);
        int j;
        for (j = 0; j < 6; j++){
        	mDemoFeed[j].setnMessage(news[j]);
        	Log.d("Feed", news[j]);
        }
        fAdapter = new FeedAdapter(rootView.getContext(), R.layout.fragment_feed_item, mDemoFeed);
        mFeedList.setAdapter(fAdapter);
        getActivity().setTitle(planet);
        return rootView;
    }
}