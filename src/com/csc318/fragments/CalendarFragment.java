package com.csc318.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc318.peepwars.R;

public class CalendarFragment extends Fragment {
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
