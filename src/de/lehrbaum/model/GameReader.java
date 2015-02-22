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
	private final String fileLocation = System.getProperty("user.home")+ "/AppData/MedicineMemory/cardpairs.ser";

	private File file;
	
	public GameReader(){
		System.out.println(fileLocation);
		file = new File (fileLocation);
		if(!file.exists()){
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				addCardPair(new LinkedList<Cardpair>());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//file = new File(getClass().getResource(fileLocation).getFile());
	}
	
	public LinkedList<Cardpair> getCardPairs(){
		LinkedList<Cardpair> list = new LinkedList<Cardpair>();
		try{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream fileIn = new ObjectInputStream(fis);
			
			list = (LinkedList<Cardpair>) fileIn.readObject();
			
			fileIn.close();
			fis.close();
		}catch(Exception e){ e.printStackTrace(); }
		return list;
	}
	
	public void addCardPair(LinkedList<Cardpair> cardPairs){
		try{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream fileOut = new ObjectOutputStream(fos);
			
			fileOut.writeObject(cardPairs);
			
			fileOut.close();
			fos.close();
		}catch(Exception ex){ ex.printStackTrace(); }
	}
}
