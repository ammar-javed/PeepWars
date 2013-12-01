package com.csc318.utilities;

import com.csc318.peepwars.Group;
import com.csc318.peepwars.R;
import com.csc318.peepwars.R.drawable;
import com.csc318.peepwars.R.id;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupsListAdapter extends ArrayAdapter<Group>{
	
	Context gContext;
	int gLayoutID;
	Group[] gList = null;
	
	public GroupsListAdapter(Context context, int layoutRId, Group[] groups){
		super(context, layoutRId, groups);
		this.gContext = context;
		this.gLayoutID = layoutRId;
		this.gList = groups;
	}
	
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      View row = convertView;
      GroupHolder holder = null;
      
      if(row == null)
      {
          LayoutInflater inflater = ((Activity)gContext).getLayoutInflater();
          row = inflater.inflate(gLayoutID, parent, false);
          
          holder = new GroupHolder();
          holder.imgIcon = (ImageView)row.findViewById(R.id.group_dp);
          holder.txtTitle = (TextView)row.findViewById(R.id.group_name);
          
          row.setTag(holder);
      }
      else
      {
          holder = (GroupHolder)row.getTag();
      }
      
      Log.d("ADAPTER", "Position = " + position);
      
      Group group = gList[position];
      holder.txtTitle.setText(group.getgName());
      if (group.getgPicture() == null){
    	  holder.imgIcon.setImageDrawable(gContext.getResources().getDrawable(R.drawable.display_pic_placeholder));
      } else {
          holder.imgIcon.setImageDrawable(group.getgPicture());
      }
      
      return row;
  }
  
	static class GroupHolder
	{
	    ImageView imgIcon;
	    TextView txtTitle;
	}	
}