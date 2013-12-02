package com.csc318.peepwars;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc318.utilities.HideKeyboardListener;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class NewGroupActivity extends Fragment {

    public NewGroupActivity() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_new_group, container, false);
		HideKeyboardListener.setupUI(getActivity(), rootView);
        return rootView;
    }
    
    @Override
    public void onStop(){
    	onDestroy();
    }
}