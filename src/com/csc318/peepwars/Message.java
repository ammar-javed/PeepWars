package com.csc318.peepwars;


public class Message {
	
	private int mDisplayPicture = 0;
	private String firstName;
	private String lastName;
	private String message;
	private String mTime;
	
	public Message(String fname, String lname, String m, String time){
		setFirstName(fname);
		setLastName(lname);
		message = m;
		setmTime(time);
	}

	public int getmDisplayPicture() {
		return mDisplayPicture;
	}

	public void setmDisplayPicture(int mDisplayPicture) {
		this.mDisplayPicture = mDisplayPicture;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

}
