package de.lehrbaum.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import de.lehrbaum.view.*;

public class Controller {
	private Card[] gameCards;
	private int openSingleCard = -1; //IF a Card without pair is open the Position (in the gameCard Array) is stored here
	
	private LinkedList<Cardpair> cardPairs;
	private GameReader reader;
	
	private MainFrame window;
	private GamePanel gamePanel;
	private MainMenu menu;
	
	
	public static void main(String[] args){
		Controller mainGame = new Controller();
		mainGame.window.setPanel(mainGame.menu);
	}
	
	public Controller(){
		reader = new GameReader();
		window = new MainFrame();
		menu = new MainMenu(this);
		
	}
	
	public void startGameClicked(){
		gameCards = new Card[cardPairs.size()*2];
		
		//init the gameCards List
		int i = 0;
		for(Cardpair cards:cardPairs){
			gameCards[i++] = cards.getDescriptionCard();
			gameCards[i++] = cards.getNameCard();
		}
		//ensure that every card is invisible and not locked
		for(Card c:gameCards){
			c.isLocked=false;
		}
		
		//shuffle the cards
		Collections.shuffle(Arrays.asList(gameCards));
		
		//create GamePanel
		gamePanel = new GamePanel(this, gameCards.length);
		
		//add GamePanel to MainFrame
		window.setPanel(gamePanel);
		
	}
	
	public void createCardClicked(){
		//create CreateCardPanel
		//add it to the mainFrame
		window.setPanel(new CreateCardPanel(this));
	}
	
	public void createCardAborted(){
		//restore the game, do NOT start a new one
		window.setPanel(gamePanel);
	}
	
	public void addCard(String name, String descr){
		Cardpair newCards = new Cardpair(name, descr);
		reader.addCardPair(newCards);
		
		window.setPanel(gamePanel);
		
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
				
				testForWin();
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
	
	private void testForWin(){
		//Check if every Card is locked
		for(Card c: gameCards){
			if(!c.isLocked){
				return;
			}
		}
		window.showMessage("GEWONNEN!");
		window.setPanel(menu);
	}
}
