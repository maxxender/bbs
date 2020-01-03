package com.bbs.model;

import java.io.Serializable;

public class NoteDevoir implements Serializable{
	private Matiere matiere = new Matiere();
	private double note;
	private int numero;
	private Semestre semestre;
	private static final long serialVersionUID = 1L;
	
	public NoteDevoir(){
		this.matiere = null;
		this.semestre = null;
		this.numero = 0;
		this.note = 0;
		
	}
	
	public NoteDevoir(Matiere matiere,Semestre semestre, int numero, double note) {
		this.matiere = matiere;
		this.numero = numero;
		this.semestre = semestre;
		this.note = note;
	}
	
	
	public Matiere getMatiere(){
		return this.matiere;
	}
	
	public double getNote(){
		return this.note;
	}
	
	public int getNumeroDevoir(){
		return this.numero;
	}
	
	public Semestre getSemestre(){
		return this.semestre;
	}
	
	///////////////setters/////////////////
	
	public void setNote(double newNoteDevoir){
		this.note = newNoteDevoir;
	}
	
	public String toString(){
		return this.numero + "-" + this.semestre + "-" + 
				this.matiere;
	}
}
