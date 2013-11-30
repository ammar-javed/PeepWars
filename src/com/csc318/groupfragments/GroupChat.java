package com.csc318.groupfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc318.peepwars.R;

public class GroupChat extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private String[] mDemoGroupConv;

    public GroupChat() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_conversations, container, false);
//        TextView dummyTextView = (TextView) rootView.findViewById(R.id.group_conv_label);
//        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        // my addition, needs revision
        ListView mGroupUsersList = (ListView) rootView.findViewById(R.id.group_conversations);
        mDemoGroupConv = getResources().getStringArray(R.array.demo_group_convs);
        
        mGroupUsersList.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_group_conversations_cell, mDemoGroupConv));
        return rootView;
    }
}