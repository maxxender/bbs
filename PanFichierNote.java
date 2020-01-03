package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.bbs.model.Classe;
import com.bbs.model.ClasseSerializer;
import com.bbs.model.Eleve;
import com.bbs.model.Matiere;
import com.bbs.model.Semestre;

public class PanFichierNote extends JPanel{
	private JPanel panNord = new JPanel();
	private JPanel panSud = new JPanel();
	private JPanel panCentre = new JPanel();
	
	private JButton jbPremierSem = new JButton("PREMIER SEMESTRE"),
					jbSecondSem = new JButton("SECOND SEMESTRE"),
					jbBlianAnnuel = new JButton("BLIAN ANNUEL");
	private JComboBox comboClasse = new JComboBox(),
					comboEleve = new JComboBox();
	private JLabel labelClasse = new JLabel("CLASSES"),
					labelEleve = new JLabel("ELEVES");
	private ClasseSerializer cs = new ClasseSerializer();
	private JTable tableNote;
	private int xBoite, yBoite = 30;
	private int
				vueBilanAnnuel = 0,
				vueSemestre1 = 0,
				vueSemestre2 = 0;
	
	public PanFichierNote(){
		
		setComboClasse();
		setComboEleve();
		comboClasse.addActionListener(new ClasseChangedListener());
		
		comboClasse.setPreferredSize(new Dimension(200,25));
		comboEleve.setPreferredSize(new Dimension(200,25));
		panNord.add(labelClasse);
		panNord.add(comboClasse);
		panNord.add(labelEleve);
		panNord.add(comboEleve);
		
		jbPremierSem.addActionListener(new ButtonPremSemListener());
		jbSecondSem.addActionListener(new ButtonSecSemListener());
		jbBlianAnnuel.addActionListener(new ButtonBilanListener());
		
		panSud.add(jbPremierSem);
		panSud.add(jbSecondSem);
		panSud.add(jbBlianAnnuel);
		
	
		this.setLayout(new BorderLayout());
		this.add(panNord, BorderLayout.NORTH);
		this.add(panCentre, BorderLayout.CENTER);
		this.add(panSud, BorderLayout.SOUTH);
	}
		
	class TableModel extends AbstractTableModel{
		private Object[][] data;
		private String[] titre;
		public TableModel(Object[][] data, String[] titre){
			this.data = data;
			this.titre = titre;
			
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.titre.length;
		}

		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.data.length;
		}

		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			return this.data[row][col];
		}
		
		public String getColumnName(int col){
			return this.titre[col];
		}
		
	}
	
	public void setComboClasse(){
		
		if(cs.getListClasse().size() > 0){
			for(int i = 0; i < cs.getListClasse().size(); i++){
				comboClasse.addItem((Classe) cs.getListClasse().get(i));
			}
		}
	}
	
	public void setComboEleve(){
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
		comboEleve.removeAllItems();
			for(int i = 0; i < cs.getClasse(classe).getListeEleves().size(); i++){
				comboEleve.addItem((Eleve)cs.getClasse(classe).getListeEleves().get(i));
			}
		}catch(Exception e){
			
		}
	}
	
	class ClasseChangedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setComboEleve();
		}
	}
	
	class EleveChangedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	class BoiteNote extends JDialog{
		public BoiteNote(JFrame parent, String title, boolean modal, JTable table){
			super(parent,title,modal);
			this.setTitle(title);
			//this.setAlwaysOnTop(true);
			this.setSize(new Dimension(1200,400));
			xBoite += 25;
			yBoite += 40;
			this.setLocation(xBoite, yBoite);
			this.getContentPane().add(new JScrollPane(table));
			this.setVisible(true);
		}
	}
	
	public float gmps(){//get moyenne premier semestre
		Classe classe = (Classe)comboClasse.getSelectedItem();
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		float m = 0;
		for(int i = 0; i < classe.getListMatiere().size(); i++){
			m += getMoyennePremierSemestre(classe.getListMatiere().get(i));
		}
		return m / classe.getListMatiere().size();
	}
	
	public float gmss(){ //get moyennesecond semestre
		Classe classe = (Classe)comboClasse.getSelectedItem();
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		float m = 0;
		for(int i = 0; i < classe.getListMatiere().size(); i++){
			m += getMoyenneSecondSemestre(classe.getListMatiere().get(i));
		}
		return m / classe.getListMatiere().size();
	}
	
	public float gma(){ // get moyenne annuel
		return (gmps() + gmss()) / 2;
	}
	
	class ButtonBilanListener implements ActionListener{
		Classe classe = (Classe)comboClasse.getSelectedItem();
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		String title = "BILAN DES NOTES - " + eleve;
		String[] titreColone = {"MOYENNE PREMIER SEMESTRE","MOYENNE SECOND SEMESTRE","MOYENNE ANNUELLE"};
		public void actionPerformed(ActionEvent e){
			Object[][] datas = {
					{gmps(),gmss(),gma()
					}
			};
			TableModel model = new TableModel(datas,titreColone);
			tableNote = new JTable(model);
			
			if(vueBilanAnnuel == 0){
				panCentre.add(tableNote);
			}else{
				BoiteNote boite = new BoiteNote(null,title,false,tableNote);
			}
			vueBilanAnnuel++;
		}
	}
	
	
	class ButtonPremSemListener implements ActionListener{
		String title[] = {"Matière", "Eval 1", "Eval 2", "Eval 3", "Eval 4","Moyenne eval" ,
				"Examen 1", "Examen 2", "Moyenne semestrielle"
		};

		Classe classe = (Classe)comboClasse.getSelectedItem();
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		public void actionPerformed(ActionEvent e){
			Classe classe = (Classe)comboClasse.getSelectedItem();
			Object data[][] = new Object[cs.getClasse(classe).getListMatiere().size()][9];
			for(int i = 0 ; i < classe.getListMatiere().size(); i++){
				data[i][0] = getMatiere(i);
				data[i][1] = getEvaluationSemestre1(1,getMatiere(i));
				data[i][2] = getEvaluationSemestre1(2,getMatiere(i));
				data[i][3] = getEvaluationSemestre1(3,getMatiere(i));
				data[i][4] = getEvaluationSemestre1(4,getMatiere(i));
				data[i][5] = getMoyenneEvaluationSemestre1(getMatiere(i));
				data[i][6] = getDevoir1Semestre1(getMatiere(i));
				data[i][7] = getDevoir2Semestre1(getMatiere(i));
				data[i][8] = getMoyennePremierSemestre(getMatiere(i));
			}
			
			String titre = "NOTES - PREMIER SEMESTRE - " + (Eleve)comboEleve.getSelectedItem();
			
			
			
			TableModel model = new TableModel(data,title);
			tableNote = new JTable(model);
			if(vueSemestre1 == 0){
				panCentre.repaint();
				panCentre.setLayout(new BorderLayout());
				panCentre.setBorder(BorderFactory.createBevelBorder(1));
				panCentre.add(new JScrollPane(tableNote), BorderLayout.CENTER);
			}else{
				
				BoiteNote boite = new BoiteNote(null,titre,false,tableNote);
			}
			vueSemestre1++;
		}
		
	
	}
	
	
	class ButtonSecSemListener implements ActionListener{
		String title[] = {"Matière", "Eval 1", "Eval 2", "Eval 3", "Eval 4","Moyenne eval" ,
				"Examen 1", "Examen 2", "Moyenne semestrielle"
		};

		Classe classe = (Classe)comboClasse.getSelectedItem();
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		public void actionPerformed(ActionEvent e){
			Classe classe = (Classe)comboClasse.getSelectedItem();
			Object data[][] = new Object[cs.getClasse(classe).getListMatiere().size()][9];
			for(int i = 0 ; i < classe.getListMatiere().size(); i++){
				data[i][0] = getMatiere(i);
				data[i][1] = getEvaluationSemestre2(1,getMatiere(i));
				data[i][2] = getEvaluationSemestre2(2,getMatiere(i));
				data[i][3] = getEvaluationSemestre2(3,getMatiere(i));
				data[i][4] = getEvaluationSemestre2(4,getMatiere(i));
				data[i][5] = getMoyenneEvaluationSemestre2(getMatiere(i));
				data[i][6] = getDevoir1Semestre2(getMatiere(i));
				data[i][7] = getDevoir2Semestre2(getMatiere(i));
				data[i][8] = getMoyenneSecondSemestre(getMatiere(i));
			}
			
			String titre = "NOTES - SECOND SEMESTRE - " + (Eleve)comboEleve.getSelectedItem();
			TableModel model = new TableModel(data,title);
			tableNote = new JTable(model);
			/*if(vueSemestre2 == 0){
				panCentre.repaint();
				panCentre.setLayout(new BorderLayout());
				panCentre.setBorder(BorderFactory.createBevelBorder(1));
				panCentre.add(new JScrollPane(tableNote), BorderLayout.CENTER);
			}else{
			*/
				BoiteNote boite = new BoiteNote(null,titre,false,tableNote);
			//}
			vueSemestre2++;
		}
		
	
	}
	
	
	
	public Matiere getMatiere(int i){
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getListMatiere().get(i);
		}catch(Exception e){
			return null;
		}
	}
	
	public double getEvaluationSemestre1(int numero, Matiere matiere){
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		
			double note = cs.getClasse(classe).getEleve(eleve).
				getInterro(Semestre.PREMIER, matiere, numero).getNote();
			if(note == -1){
				return 0/-1;
			}else{
				return note;
			}

	}
	
	public double getEvaluationSemestre2(int numero, Matiere matiere){
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		
			double note =  cs.getClasse(classe).getEleve(eleve).
				getInterro(Semestre.SECOND, matiere, numero).getNote();
			if(note == -1){
				return 0/-1;
			}else{
				return note;
			}
	}
	
	public double getMoyenneEvaluationSemestre1(Matiere matiere){//get moyenne des interros premier semestre
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		return cs.getClasse(classe).getEleve(eleve).getMoyIntPremSem(matiere);
	}
	
	public double getMoyenneEvaluationSemestre2(Matiere matiere){//moyenne interros second semestre
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		return cs.getClasse(classe).getEleve(eleve).getMoyIntSecSem(matiere);
	}
	
	public double getDevoir1Semestre1(Matiere matiere){  //get note devoir 1 semestre 1
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getEleve(eleve).getDevoir(Semestre.PREMIER,
				matiere, 1).getNote();
		}catch(Exception e){
			return 0;
		}
	}
	
	public double getDevoir1Semestre2(Matiere matiere){// get note devoir 1 semestre 2
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getEleve(eleve).getDevoir(Semestre.SECOND,
				matiere, 1).getNote();
		}catch(Exception e){
			return 0;
		}
	}

	
	public double getDevoir2Semestre1(Matiere matiere){// devoir 2 semestre 1
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getEleve(eleve).getDevoir(Semestre.PREMIER,
				matiere, 2).getNote();
		}catch(Exception e){
			return 0;
		}
	}
	
	public double getDevoir2Semestre2(Matiere matiere){ // devoir 2 semestre 2
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getEleve(eleve).getDevoir(Semestre.SECOND,
				matiere, 2).getNote();
		}catch(Exception e){
			return 0;
		}
	}
	
	public double getMoyennePremierSemestre(Matiere matiere){ // affiche moyenne semestre 1
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getEleve(eleve).getMoyennePremierSemestre(matiere);
		}catch(Exception e){
			return 0;
		}
	}
	
	public double getMoyenneSecondSemestre(Matiere matiere){ // affiche moyenne semestre 2
		Eleve eleve = (Eleve)comboEleve.getSelectedItem();
		Classe classe = (Classe)comboClasse.getSelectedItem();
		try{
			return cs.getClasse(classe).getEleve(eleve).getMoyenneSecondSemestre(matiere);
		}catch(Exception e){
			return 0;
		}
	}

}
