package de.lehrbaum.model;

import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class GameReader {
	private final String fileLocation = "/cards/cardpairs.ser";

	private File file;
	
	public GameReader(){
		file = new File(fileLocation);
	}
	
	public LinkedList<Cardpair> getCardPairs(){
		LinkedList<Cardpair> list = new LinkedList<Cardpair>();
		try{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream fileIn = new ObjectInputStream(fis);
			
			
			Cardpair p;
			while((p= (Cardpair) fileIn.readObject()) != null){
				list.add(p);
			}
			
			fileIn.close();
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
