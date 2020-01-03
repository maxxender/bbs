package com.bbs.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Classe implements Serializable{

	private String nom;
	private Niveau niveau;
	private ArrayList<Eleve> listEleves = new ArrayList<Eleve>();
	private ArrayList<Eleve> listGarcons = new ArrayList<Eleve>();
	private ArrayList<Eleve> listFilles = new ArrayList<Eleve>();
	private ArrayList<Matiere> listMatiere = new ArrayList<Matiere>();
	private ArrayList<Professeur> listProf = new ArrayList<Professeur>();
	private double montantContribution;
	public ArrayList lesClasses = new ArrayList();
	
	private static final long serialVersionUID = 1L;
	
	public Classe(){
		this.nom = null;
		this.niveau = null;
		this.montantContribution = 0;
		
	}
	
	public Classe(String nom,  Niveau niveau, double montantContribution){
		this.nom = nom;
		this.niveau = niveau;
		this.montantContribution = montantContribution;

	}
	
	//getters
	
	public String getNom(){
		return this.nom;
	}
	
	public String getNiveau(){
		return this.niveau.toString();
	}
	
	public ArrayList getListeEleves(){
		return this.listEleves;
	}
	
	public ArrayList<Eleve> getListeGarcons(){
		return this.listGarcons;
	}
	
	public ArrayList getListFilles(){
		return this.listFilles;
	}
	
	public int getNbreEleves(){
		return this.listEleves.size();
	}
	
	public int getNbreGarcon(){
		return this.listGarcons.size();
	}
	
	public int getNbreFille(){
		return this.listFilles.size();
	}
	
	public double getMontantContribution(){
		return this.montantContribution;
	}
	
	public Eleve getEleve(Eleve eleve){
		Eleve eleveRechercher = null;
		for(int i = 0; i < this.listEleves.size(); i++){
			if(eleve.toString().equals(listEleves.get(i).toString())){
				eleveRechercher = this.listEleves.get(i);
			}
		}
		return eleveRechercher;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	public void ajoutezEleve(Eleve eleve){
		ClasseSerializer cs = new ClasseSerializer();
		
		if(eleve.getSexe().equals(Sexe.Masculin)){
			this.listGarcons.add(eleve);
		}
		else if(eleve.getSexe().equals(Sexe.Feminin)){
			this.listFilles.add(eleve);
		}
		this.listEleves.add(eleve);
		cs.addClasse(this);
		cs.serializer();
	}
	
	public void retirezEleve(Eleve eleve){
		this.listEleves.remove(eleve);
	}
	
	public void addMatiere(Matiere matiere){
		this.listMatiere.add(matiere);
	}
	
	public ArrayList<Matiere> getListMatiere(){
		return this.listMatiere;
	}
	
	public String toString(){
		return this.nom;
	}
	
}