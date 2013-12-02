package com.csc318.utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csc318.peepwars.NewsItem;
import com.csc318.peepwars.R;



public class FeedAdapter extends ArrayAdapter<NewsItem>{
	
	Context nContext;
	int nLayoutID;
	NewsItem[] News = null;
	
	public FeedAdapter(Context context, int layoutRId, NewsItem[] messages){
		super(context, layoutRId, messages);
		this.nContext = context;
		this.nLayoutID = layoutRId;
		this.News = messages;
	}
	
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      View row = convertView;
      FeedItemHolder holder = null;
      
      if(row == null)
      {
          LayoutInflater inflater = ((Activity)nContext).getLayoutInflater();
          row = inflater.inflate(nLayoutID, parent, false);
          
          holder = new FeedItemHolder();
          holder.displayPicture = (ImageView)row.findViewById(R.id.feed_pic);
          holder.message = (TextView)row.findViewById(R.id.news_feed_item);
          
          row.setTag(holder);
      }
      else
      {
          holder = (FeedItemHolder)row.getTag();
      }
            
      NewsItem newsItem = News[position];
      if (newsItem.getnPicture() == 0){
    	  holder.displayPicture.setImageDrawable(nContext.getResources().getDrawable(R.drawable.display_pic_placeholder));
      } else {
          holder.displayPicture.setImageDrawable(nContext.getResources().getDrawable(newsItem.getnPicture()));
      }
      Log.d("hi", newsItem.getnMessage());
      holder.message.setText(newsItem.getnMessage());
      
      return row;
  }
  
	static class FeedItemHolder
	{
	    ImageView displayPicture;
	    TextView message;
	}	
}
