package com.csc318.peepwars;

public class NewsItem {
	
	private int nPicture;
	private String nMessage;
	
	public NewsItem(int id, String message){
		setnPicture(id);
		setnMessage(message);
	}

	public int getnPicture() {
		return nPicture;
	}

	public void setnPicture(int pic) {
		this.nPicture = pic;
	}

	public String getnMessage() {
		return nMessage;
	}

	public void setnMessage(String mes) {
		this.nMessage = mes;
	}
}
