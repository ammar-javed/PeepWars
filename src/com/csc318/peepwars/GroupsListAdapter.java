package com.csc318.peepwars;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class GroupsListAdapter extends ArrayAdapter<String>{
	 
	private final Context mContext;
	private int nNumRows;
	private Group[] mGroups;

	public GroupsListAdapter(Activity context, Group[] groupList) {
		super(context, R.layout.fragment_groups);
		this.mContext = context;
		this.nNumRows = 0;	
		this.mGroups = groupList;
	}
	
	@Override
	public int getCount() {
		return 5;
	}
	
	public void getView(){
		this.nNumRows++;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.fragment_groups_cell, null);
		} else {
			Group i = mGroups[position];
			
		}
		
		return view;
	}
}