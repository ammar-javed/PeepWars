package com.csc318.peepwars;

import android.graphics.drawable.Drawable;

public class Group {
	
	private String gName;
	private Drawable gPicture;
	
	public Group(String name){
		this.gName = name;
		gPicture = null;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public Drawable getgPicture() {
		return gPicture;
	}

	public void setgPicture(Drawable gPicture) {
		this.gPicture = gPicture;
	}

}
