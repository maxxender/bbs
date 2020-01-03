package com.bbs.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClasseSerializer{
	private ArrayList<Classe> listClasses = new ArrayList<Classe>();
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	File fichierClasse = new File("file/classes.txt");
	
	public ClasseSerializer(){
		//File fichierClasse = new File("file/classes.txt");
		if(fichierClasse.length() > 0){
			try {
				ois = new ObjectInputStream(
						new BufferedInputStream(
								new FileInputStream(
										new File("file/classes.txt"))));
				this.listClasses = (ArrayList<Classe>)ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(ClassNotFoundException e2){
				e2.printStackTrace();
			}
			
		}
	}
	
	public void serializer(){
		try{
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(
									new File("file/classes.txt"))));
			oos.writeObject(this.listClasses);
			oos.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}
	
	public void addClasse(Classe newClasse){
		this.listClasses.add(newClasse);
		/*for(int i = 0; i < this.listClasses.size(); i++){
			System.out.println(this.listClasses.get(i).toString());
		}
		*/
	
	}
	
	public Classe getClasse(Classe classe){
		Classe classeRetournee = null;
		for(int i = 0; i < this.listClasses.size(); i++){
			if(classe.toString().equals(listClasses.get(i).toString()))
				classeRetournee = this.listClasses.get(i);
		}
		return classeRetournee;
	}
	
	public ArrayList<Classe> getListClasse(){
		return this.listClasses;
	}
	
	
}
