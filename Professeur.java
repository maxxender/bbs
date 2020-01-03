package com.bbs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Professeur implements Serializable{
	private String nom;
	private String prenom; 
	private ArrayList<Cours> cours = new ArrayList<Cours>();
	
	public Professeur(){
		this.nom = null;
		this.prenom = null;
	}
	
	public Professeur(String nom, String prenom){
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public ArrayList<Cours> getCours(){
		return this.cours;
	}
	
	
	///////////////////////////////////////////////////////////////////////
	
	public void setCours(Classe classe, Matiere matiere, String heure){
		this.cours.add(new Cours(matiere, classe, heure));
	}
	
}
