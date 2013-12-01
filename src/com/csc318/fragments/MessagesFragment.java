package com.csc318.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csc318.peepwars.Message;
import com.csc318.peepwars.R;
import com.csc318.utilities.ConversationsAdapter;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class MessagesFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private Message[] mDemoMessages;
    private ConversationsAdapter mAdapter;

    public MessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_conversations, container, false);
        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String drawerOptions = getResources().getStringArray(R.array.drawer_options)[i];
        
        //Populate action bar
        setHasOptionsMenu(true);

    	mDemoMessages = new Message[]{
    			new Message("Ramma", "Devaj", 
    					getResources().getString(R.string.message_one), "3 mins"),
				new Message("Irunib", "Egamagatiplaw", 
						getResources().getString(R.string.message_five), "2 hrs"),
				new Message("Lastav", "Timan", 
						getResources().getString(R.string.message_three), "Fri"),
    			new Message("Smehs", "Helas", 
    					getResources().getString(R.string.message_four), "Fri"),
    			new Message("Ben", "Lee", 
    					getResources().getString(R.string.message_two), "Mon"),
    			new Message("Munira", "Lila", 
    					getResources().getString(R.string.message_one), "Mon"),
    			new Message("Ammar", "Javed", 
    					getResources().getString(R.string.message_six), "Sun"),
    			new Message("Adolf", "Toral", 
    					getResources().getString(R.string.message_five), "1 Week"),
    			new Message("Sangah", "Han", 
    					getResources().getString(R.string.message_two), "1 Week"),
    			new Message("Siamak", "Freydoonnejad", 
    					getResources().getString(R.string.message_four), "1 Week"),
    			new Message("Keyvan", "Mosharraf", 
    					getResources().getString(R.string.message_three), "1 Week"),
    			new Message("Aashni", "Shah", 
    					getResources().getString(R.string.message_two), "2 Weeks"),
				new Message("Justin", "Jim", 
						getResources().getString(R.string.message_six), "2 Weeks"),
    			new Message("Van", "Dang", 
    					getResources().getString(R.string.message_four), "2 Weeks"),
    	};
        ListView mFeedList = (ListView) rootView.findViewById(R.id.conversations);
        mAdapter = new ConversationsAdapter(rootView.getContext(), R.layout.fragment_conversations_cell, mDemoMessages);
        mFeedList.setAdapter(mAdapter);
        getActivity().setTitle(drawerOptions);
        return rootView;
    }
    
    @Override
    public void onCreateOptionsMenu(
          Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.add_new_button, menu);
    }

}