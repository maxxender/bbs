package com.bbs.model;

import java.io.Serializable;

public class Cours implements Serializable{
	private Classe classe;
	private Matiere matiere;
	private String heure;
	
	public Cours(){
		this.matiere = null;
		this.classe = null;
		this.heure = null;
	}
	
	public Cours(Matiere matiere, Classe classe, String heure){
		this.matiere = matiere;
		this.classe = classe;
		this.heure = heure;
		
	}
	
	public Matiere getMatiere(){
		return this.matiere;
	}
	
	public Classe getClasse(){
		return this.classe;
	}
	
	public String getHeure(){
		return this.heure;
	}
	
	//////////////////////////////////////////////////////////////////
	
	public void setHeure(String newHeure){
		this.heure = newHeure;
	}
}
