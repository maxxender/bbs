package com.bbs.model;

import java.io.Serializable;

public class Matiere implements Serializable{
	private String nom;
	private TypeMatiere typeMatiere;
	private int coefficient = 1;
	
	
	public Matiere(){
		this.nom = null;
		this.typeMatiere = null;
		this.coefficient = 1;
	}
	
	public Matiere(String nom, TypeMatiere type, int coefficient){
		this.nom = nom;
		this.typeMatiere = type;
		this.coefficient = coefficient;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public TypeMatiere getType(){
		return this.typeMatiere;
	}
	
	public int getCoefficient(){
		return this.coefficient;
	}
	
	public void setCoefficient(int newCoefficient){
		this.coefficient = newCoefficient;
	}
	
	public String toString(){
		return this.nom.toUpperCase();
	}
}
