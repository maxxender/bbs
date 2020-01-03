package com.bbs.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Eleve implements Serializable{
	private String nom, prenom;
	private Sexe sexe;
	private Image photo;
	private ArrayList<NoteDevoir> notesDevoirs = new ArrayList<NoteDevoir>();
	private ArrayList<NoteInterro> notesInterros = new ArrayList<NoteInterro>();
	private static final long serialVersionUID = 1L;
	private Classe classe = new Classe();
	private double resteApayez;
	private ArrayList historiquePaiement;
	private LocalDateTime datePaiement;
	private String jourPaiement;
	private String heurePaiement;
	private ArrayList joursPaiement = new ArrayList();
	private ArrayList heuresPaiement = new ArrayList();
	public ArrayList paiements = new ArrayList();
	private ArrayList restesApayer = new ArrayList();
	private double moyPremSem = 0;
	private double moySecSem = 0;
	private double moyAnnuel = 0;
	
	private NoteInterro[] interrogations = {};
	
	public Eleve(){
		this.nom = null;
		this.prenom = null;
		this.sexe = null;
		
	}
	
	public Eleve(String nom, String prenom, Sexe sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.resteApayez = classe.getMontantContribution();
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	
	public void setClasse(Classe newClasse){
		this.classe = newClasse;
		this.resteApayez = this.classe.getMontantContribution();
	}
	
	
	public void setPaiement(double montant){
		this.resteApayez = resteApayez - montant;
		datePaiement = LocalDateTime.now();
		int jour = this.datePaiement.getDayOfMonth();
		int mois = this.datePaiement.getMonthValue();
		int annee = this.datePaiement.getYear();
		int heure = this.datePaiement.getHour();
		int minute = this.datePaiement.getMinute();
		this.jourPaiement = jour + "/" + mois + "/" + annee;
		this.heurePaiement = heure + "h " + minute + "min";
		this.joursPaiement.add(jourPaiement);
		this.heuresPaiement.add(heurePaiement);
		this.paiements.add(montant);
		this.restesApayer.add(resteApayez);
	}
	
	public void setPhoto(String fichier){
		try{
			this.photo = ImageIO.read(new File(fichier));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void setInterro(NoteInterro note){
		this.notesInterros.add(note);
	}
	
	public NoteInterro getInterro(Semestre semestre, Matiere matiere, int numero){
		NoteInterro note = new NoteInterro();
		for(int i = 0; i < this.notesInterros.size(); i++){
			NoteInterro noteThis = this.notesInterros.get(i);
			if(noteThis.getSemestre().equals(semestre) &&
					noteThis.getMatiere().equals(matiere) &&
					noteThis.getNumeroInterro() == numero){
				note = noteThis;
			}
		}
			return note;
		
	}
	
	public void setDevoir(NoteDevoir newNote){
		this.notesDevoirs.add(newNote);
	}
	
	public NoteDevoir getDevoir(Semestre semestre, Matiere matiere, int numero){
		NoteDevoir note = null;
		for(int i = 0; i < this.notesDevoirs.size(); i++){
			NoteDevoir thisNote = this.notesDevoirs.get(i);
			if(thisNote.getSemestre().equals(semestre) &&
					thisNote.getMatiere().equals(matiere) &&
					thisNote.getNumeroDevoir() == numero){
				note = thisNote;
			}
		}
		return note;
	}
	
	public double getMoyennePremierSemestre(Matiere matiere){
		double 
		sommeDevoirs = 0, 
		sommeInterros = 0,
		moyenneInterros,
		moyenneDevoirs;
		int nbInterro = 0;
		double
		interro1 = this.getInterro(Semestre.PREMIER, matiere, 1).getNote(),
		interro2 = this.getInterro(Semestre.PREMIER, matiere, 2).getNote(),
		interro3 = this.getInterro(Semestre.PREMIER, matiere, 3).getNote(),
		interro4 = this.getInterro(Semestre.PREMIER, matiere, 4).getNote();
		
		if(interro1 != -1){
			sommeInterros += interro1;
			nbInterro++;
		}
		if(interro2 != -1){
			sommeInterros += interro2;
			nbInterro++;
		}
		if(interro3 != -1){
			sommeInterros += interro3;
			nbInterro++;
		}
		if(interro4 != -1){
			sommeInterros += interro4;
			nbInterro++;
		}
		
		sommeDevoirs = this.getDevoir(Semestre.PREMIER, matiere, 1).getNote() + 
				this.getDevoir(Semestre.PREMIER, matiere, 2).getNote();
		
		moyenneInterros = sommeInterros / nbInterro;
		
		moyenneDevoirs = sommeDevoirs / 2;
		this.moyPremSem = (moyenneInterros + 
				this.getDevoir(Semestre.PREMIER, matiere, 1).getNote() + 
				this.getDevoir(Semestre.PREMIER, matiere, 2).getNote() ) / 3;
		
		
		return this.moyPremSem;
	}
	
	public double getMoyIntPremSem(Matiere matiere){
		int nbInterro = 0;
		double sommeInterros = 0;
		double 
		interro1 = this.getInterro(Semestre.PREMIER, matiere, 1).getNote(),
		interro2 = this.getInterro(Semestre.PREMIER, matiere, 2).getNote(),
		interro3 = this.getInterro(Semestre.PREMIER, matiere, 3).getNote(),
		interro4 = this.getInterro(Semestre.PREMIER, matiere, 4).getNote();
		
		if(interro1 != -1){
			sommeInterros += interro1;
			nbInterro++;
		}
		if(interro2 != -1){
			sommeInterros += interro2;
			nbInterro++;
		}
		if(interro3 != -1){
			sommeInterros += interro3;
			nbInterro++;
		}
		if(interro4 != -1){
			sommeInterros += interro4;
			nbInterro++;
		}
		
		return sommeInterros / nbInterro;
	}
	
	public double getMoyIntSecSem(Matiere matiere){
		int nbInterro = 0;
		double sommeInterros = 0;
		double 
		interro1 = this.getInterro(Semestre.SECOND, matiere, 1).getNote(),
		interro2 = this.getInterro(Semestre.SECOND, matiere, 2).getNote(),
		interro3 = this.getInterro(Semestre.SECOND, matiere, 3).getNote(),
		interro4 = this.getInterro(Semestre.SECOND, matiere, 4).getNote();
		
		if(interro1 != -1){
			sommeInterros += interro1;
			nbInterro++;
		}
		if(interro2 != -1){
			sommeInterros += interro2;
			nbInterro++;
		}
		if(interro3 != -1){
			sommeInterros += interro3;
			nbInterro++;
		}
		if(interro4 != -1){
			sommeInterros += interro4;
			nbInterro++;
		}
		
		return sommeInterros / nbInterro;
	}
	
	
	public double getMoyenneSecondSemestre(Matiere matiere){
		double 
		sommeDevoirs = 0, 
		sommeInterros = 0,
		moyenneInterros,
		moyenneDevoirs;
		int nbInterro = 0;
		double 
		interro1 = this.getInterro(Semestre.SECOND, matiere, 1).getNote(),
		interro2 = this.getInterro(Semestre.SECOND, matiere, 2).getNote(),
		interro3 = this.getInterro(Semestre.SECOND, matiere, 3).getNote(),
		interro4 = this.getInterro(Semestre.SECOND, matiere, 4).getNote();
		
		if(interro1 != -1){
			sommeInterros += interro1;
			nbInterro++;
		}
		if(interro2 != -1){
			sommeInterros += interro2;
			nbInterro++;
		}
		if(interro3 != -1){
			sommeInterros += interro3;
			nbInterro++;
		}
		if(interro4 != -1){
			sommeInterros += interro4;
			nbInterro++;
		}
		
		moyenneInterros = sommeInterros / nbInterro;
		
		moyenneDevoirs = sommeDevoirs / 2;
		this.moySecSem= (moyenneInterros + 
				this.getDevoir(Semestre.SECOND, matiere, 1).getNote() + 
				this.getDevoir(Semestre.SECOND, matiere, 2).getNote() ) / 3;
		
		
		return this.moySecSem;
	}
	
	public double getMoyTotalPremSem(){
		Matiere matiere;
		for(int i = 0; i < this.classe.getListMatiere().size(); i++){
			matiere = this.classe.getListMatiere().get(i);
			this.moyPremSem += this.getMoyennePremierSemestre(matiere);
		}
		return this.moyPremSem;
	}
	
	public double getMoyTotalSecSem(){
		//Matiere matiere;
		for(int i = 0; i < this.classe.getListMatiere().size(); i++){
			//matiere = this.classe.getListMatiere().get(i);
			this.moySecSem += this.getMoyenneSecondSemestre(classe.getListMatiere().get(i));
		}
		return this.moySecSem;
	}
	
	public double getMoyenneAnnuel(){
		Matiere matiere;
		for(int i = 0; i < this.classe.getListMatiere().size(); i++){
			matiere = this.classe.getListMatiere().get(i);
			this.moyAnnuel = this.getMoyennePremierSemestre(matiere) + 
					this.getMoyenneSecondSemestre(matiere);
		}
		return this.moyAnnuel;
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	
	
	public String getNom(){
		return this.nom;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public Classe getClasse(){
		return this.classe;
	}
	
	public Sexe getSexe(){
		return this.sexe;
	}
	

	
	public double getResteApayez(){
		return this.resteApayez;
	}
	
	public String getJourPaiement(){
		return this.jourPaiement;
	}
	
	public String getHeurePaiement(){
		return this.heurePaiement;
	}
	
	public ArrayList getJoursPaiement(){
		return this.joursPaiement;
	}
	
	public ArrayList getHeuresPaiement(){
		return this.heuresPaiement;
	}
	
	public ArrayList getPaiements(){
		return this.paiements;
	}
	 
	public ArrayList getRestesApayer(){
		return this.restesApayer;
	}
	
	public Image getPhoto(){
		return this.photo;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////
	public String toString(){
		return this.nom.toUpperCase() + " " + this.prenom;
	}
	
	public void setPayezContrib(double montant){
		
	}
}
