package de.lehrbaum.model;

import java.awt.Point;
import java.util.LinkedList;

import de.lehrbaum.view.*;

public class Controller {
	private Card[] gameCards;
	private int openSingleCard = -1; //IF a Card without pair is open the Position (in the gameCard Array) is stored here
	
	private LinkedList<Cardpair> cardPairs;
	private GameReader reader;
	
	private MainFrame window;
	private GamePanel gamePanel;
	
	
	public static void main(String[] args){
		//Read Cardpairs
		//Create gameBoard with Cards
		//Create MainFrame
		//Start Main Menue
	}
	
	public void startGameClicked(){
		gameCards = new Card[cardPairs.size()];
		//use shuffle
		//create GamePanel
		//add GamePanel to MainFrame
	}
	
	public void createCardClicked(){
		//create CreateCardPanel
		//add it to the mainFrame
	}
	
	public void createCardAborted(){
		//restore the game, do NOT start a new one
	}
	
	public void addCard(String name, String descr){
		Cardpair newCards = new Cardpair(name, descr);
		reader.addCardPair(newCards);
	}
	
	public void fieldClicked(int position){
		if(gameCards[position].isLocked) return; //locked cards can't be removed
		
		if(openSingleCard == -1){ //No other card is open
			gamePanel.setText(position, gameCards[position].getText());
		}
		else if(openSingleCard == position){//Same Card clicked again
			gamePanel.hideField(position);
		}
		else{//Second card opened -> Test if pair
			if(gameCards[openSingleCard].isPair(gameCards[position])){//the Cards fit
				//Show the new Card and mark both as locked
				gamePanel.setText(position, gameCards[position].getText());
				gameCards[position].isLocked = true;
				gameCards[openSingleCard].isLocked = true;
				
				openSingleCard = -1;
			}
			else{ //Cards do not fit -> Show both cards for 2 secs, then make both dissappear
				gamePanel.setText(position, gameCards[position].getText());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gamePanel.hideField(position);
				gamePanel.hideField(openSingleCard);
				openSingleCard = -1;
			}
		}
	}
}
