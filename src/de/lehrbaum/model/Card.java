package de.lehrbaum.model;

public class Card {
	private String text;
	private Card partner;
	
	 //true if the partner of this card was found and it should stay on the field
	boolean isLocked;
	
	public Card(String text){
		this.text = text;
		isLocked = false;
	}
	
	public Card(String text, Card pair){
		this.text = text;
		pair.partner = this;
		partner = pair;
	}
	
	public String getText(){
		return text;
	}
	
	public boolean isPair(Card pair){
		return pair == partner; 
	}
}
