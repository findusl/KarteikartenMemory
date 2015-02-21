package de.lehrbaum.model;

import java.io.IOException;
import java.io.Serializable;

public class Cardpair implements Serializable {
	private static final long serialVersionUID = 6613692491108675626L;
	private Card nameCard, descrCard;
	
	
	public Cardpair(String name, String descr){
		nameCard = new Card(name);
		descrCard = new Card(descr, nameCard);
	}
	
	public Card getDescriptionCard(){
		return descrCard;
	}
	
	public Card getNameCard(){
		return nameCard;
	}
	
	 private void writeObject(java.io.ObjectOutputStream out) throws IOException{
		 out.writeObject(nameCard.getText());
		 out.writeObject(descrCard.getText());
	 }
	 
	 private void readObject(java.io.ObjectInputStream in)throws IOException, ClassNotFoundException{
		 nameCard = new Card((String)in.readObject());
		 descrCard = new Card((String)in.readObject(), nameCard);
	 }
}
