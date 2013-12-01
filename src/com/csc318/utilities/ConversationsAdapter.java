package com.csc318.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csc318.peepwars.Message;
import com.csc318.peepwars.R;


public class ConversationsAdapter extends ArrayAdapter<Message>{
	
	Context cContext;
	int cLayoutID;
	Message[] conversationsList = null;
	
	public ConversationsAdapter(Context context, int layoutRId, Message[] messages){
		super(context, layoutRId, messages);
		this.cContext = context;
		this.cLayoutID = layoutRId;
		this.conversationsList = messages;
	}
	
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      View row = convertView;
      MessageHolder holder = null;
      
      if(row == null)
      {
          LayoutInflater inflater = ((Activity)cContext).getLayoutInflater();
          row = inflater.inflate(cLayoutID, parent, false);
          
          holder = new MessageHolder();
          holder.displayPicture = (ImageView)row.findViewById(R.id.peep_pic);
          holder.mName = (TextView)row.findViewById(R.id.peep_name);
          holder.message = (TextView)row.findViewById(R.id.peep_message);
          holder.mTime = (TextView)row.findViewById(R.id.message_time);
          
          row.setTag(holder);
      }
      else
      {
          holder = (MessageHolder)row.getTag();
      }
            
      Message message = conversationsList[position];
      holder.mName.setText(message.getFirstName() + " " + message.getLastName());
      if (message.getmDisplayPicture() == 0){
    	  holder.displayPicture.setImageDrawable(cContext.getResources().getDrawable(R.drawable.display_pic_placeholder));
      } else {
          holder.displayPicture.setImageDrawable(cContext.getResources().getDrawable(message.getmDisplayPicture()));
      }
      holder.message.setText(message.getMessage());
      holder.mTime.setText(message.getmTime());
      
      return row;
  }
  
	static class MessageHolder
	{
	    ImageView displayPicture;
	    TextView mName;
	    TextView message;
	    TextView mTime;
	}	
}
