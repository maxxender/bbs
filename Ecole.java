package com.bbs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ecole implements Serializable{
	
	private ArrayList classes = new ArrayList();
	
	
	
	
	public ArrayList getClasses(){
		return this.classes;
	}
	
	public void addClasse(Classe classe){
		this.classes.add(classe);
	}
}
