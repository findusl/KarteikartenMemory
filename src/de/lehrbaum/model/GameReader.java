package de.lehrbaum.model;

import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

public class GameReader {
	private final String localCards = System.getProperty("user.home")+ "/AppData/MedicineMemory/cardpairs.ser";
	private final String mobileCards = "/cards/cardpairs.ser";
			
	private File fileLocal;
	private File fileMobile;
	
	public GameReader(){
		fileLocal = new File (localCards);
		fileMobile = new File(getClass().getResource(mobileCards).getPath());
		
		if(!fileLocal.exists()){
			try {
				fileLocal.getParentFile().mkdirs();
				fileLocal.createNewFile();
				addCardPair(new LinkedList<Cardpair>());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//file = new File(getClass().getResource(fileLocation).getFile());
	}
	
	public LinkedList<Cardpair> getCardPairs(){
		LinkedList<Cardpair> list = new LinkedList<Cardpair>();
		//First: read mobile Cards
		if(fileMobile.exists()){
			try{
				FileInputStream fis = new FileInputStream(fileMobile);
				ObjectInputStream fileIn = new ObjectInputStream(fis);
				
				list = (LinkedList<Cardpair>) fileIn.readObject();
				
				fileIn.close();
				fis.close();
			}catch(Exception e){ e.printStackTrace(); }
		}
		//Then: read local Cards
		try{
			FileInputStream fis = new FileInputStream(fileLocal);
			ObjectInputStream fileIn = new ObjectInputStream(fis);
			
			list.addAll((LinkedList<Cardpair>) fileIn.readObject());
			
			fileIn.close();
			fis.close();
		}catch(Exception e){ e.printStackTrace(); }
		return list;
	}
	
	public void addCardPair(LinkedList<Cardpair> cardPairs){
		try{
			FileOutputStream fos = new FileOutputStream(fileLocal);
			ObjectOutputStream fileOut = new ObjectOutputStream(fos);
			
			fileOut.writeObject(cardPairs);
			
			fileOut.close();
			fos.close();
		}catch(Exception ex){ ex.printStackTrace(); }
	}
}
