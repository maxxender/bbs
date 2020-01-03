package com.bbs.model;

import java.io.Serializable;

public class NoteInterro implements Serializable{
	
	private Matiere matiere;
	private double note;
	private int numero;
	private Semestre semestre;
	
	public NoteInterro(){
		this.matiere = null;
		this.numero = 0;
		this.note = -1;
		this.semestre = null;
	}
	
	public NoteInterro(Matiere matiere,Semestre semestre,int numero, double note) {
		this.matiere = matiere;
		this.numero = numero;
		this.note = note;
		this.semestre = semestre;
	}
	
	public Matiere getMatiere(){
		return this.matiere;
	}
	
	public double getNote(){
		return this.note;
	}
	
	public int getNumeroInterro(){
		return this.numero;
	}
	
	public Semestre getSemestre(){
		return this.semestre;
	}
	
	///////////////setters/////////////////
	
	public void setNoteInterro(double newNote){
		this.note = newNote;
	}
	
	public String toString(){
		return this.semestre + "." + this.matiere + "." + this.numero; 
	}
}
