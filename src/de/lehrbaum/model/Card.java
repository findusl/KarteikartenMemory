package de.lehrbaum.model;

public class Card {
	private String text;
	private Card partner;
	
	public Card(String text){
		this.text = text;
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
