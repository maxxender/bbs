package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bbs.model.Classe;
import com.bbs.model.ClasseSerializer;
import com.bbs.model.Eleve;
import com.bbs.model.Matiere;
import com.bbs.model.NoteDevoir;
import com.bbs.model.Semestre;

public class PanNouveauNoteDevoir extends JPanel{
	
	private JPanel
			jpClasse = new JPanel(),
			jpEleve = new JPanel(),
			jpMatiere = new JPanel(),
			jpChoixSemestre = new JPanel(),
			jpChoixDevoirInterro = new JPanel(),
			jpChoixDevoir = new JPanel(),
			jpChoixInterro = new JPanel(),
			jpNote = new JPanel(),
			jpCentre = new JPanel(),
			jpSud = new JPanel();
	
	private JLabel
			jlClasse = new JLabel("Classe "),
			jlEleve = new JLabel("Elève  "),
			jlMatiere = new JLabel("Matière"),
			jlNote = new JLabel("Note   ");
	
	private JButton
			jbValidez = new JButton("Validez"),
			jbAnnulez = new JButton("Annulez");
	
	private ButtonGroup
			bgChoixSemestre = new ButtonGroup(),
			bgChoixNote = new ButtonGroup();
	
	private JRadioButton
			jrbPremierSemestre = new JRadioButton("PREMIER SEMESTRE"),
			jrbSecondSemestre = new JRadioButton("SECOND SEMESTRE"),
			jrbPremierDevoir = new JRadioButton("PREMIER DEVOIR"),
			jrbSecondDevoir = new JRadioButton("SECOND DEVOIR");
	
	private JTextField jtfNote = new JTextField();
	private JComboBox
			jcbClasse = new JComboBox(),
			jcbEleve = new JComboBox(),
			jcbMatiere = new JComboBox();
	private ClasseSerializer cs = new ClasseSerializer();
	
	private Font font = new Font("ITALIC", Font.BOLD, 15);
	
	public PanNouveauNoteDevoir(){
	
		jcbClasse.addActionListener(new ClasseChangeListener());
		addClasseJc();
		addMatiereInCombo();
		jbValidez.addActionListener(new ValidezListener());
		
		jpClasse.setPreferredSize(new Dimension(400,65));
		jpClasse.setBackground(Color.WHITE);
		jpClasse.setBorder(BorderFactory.createTitledBorder("Choix de la classe"));
		jcbClasse.setPreferredSize(new Dimension(200,30));
		jcbClasse.setFont(font);
		jpClasse.add(jlClasse);
		jpClasse.add(jcbClasse);
		
		jpEleve.setPreferredSize(new Dimension(400,65));
		jpEleve.setBackground(Color.WHITE);
		jpEleve.setBorder(BorderFactory.createTitledBorder("Choix de l'élève"));
		jcbEleve.setPreferredSize(new Dimension(200, 30));
		jcbEleve.setFont(font);
		jpEleve.add(jlEleve);
		jpEleve.add(jcbEleve);
		
		jpMatiere.setPreferredSize(new Dimension(400,65));
		jpMatiere.setBackground(Color.WHITE);
		jcbMatiere.setPreferredSize(new Dimension(200,30));
		jcbMatiere.setFont(font);
		jpMatiere.setBorder(BorderFactory.createTitledBorder("Matière à noté"));
		jpMatiere.add(jlMatiere);
		jpMatiere.add(jcbMatiere);
		
		jpChoixSemestre.setPreferredSize(new Dimension(400,65));
		jpChoixSemestre.setBackground(Color.WHITE);
		jpChoixSemestre.setBorder(BorderFactory.createTitledBorder("Choix du semestre"));
		jrbPremierSemestre.setFont(font);
		jrbSecondSemestre.setFont(font);
		jrbPremierSemestre.setSelected(true);
		bgChoixSemestre.add(jrbPremierSemestre);
		bgChoixSemestre.add(jrbSecondSemestre);
		jpChoixSemestre.add(jrbPremierSemestre);
		jpChoixSemestre.add(jrbSecondSemestre);
		
		jpChoixDevoir.setPreferredSize(new Dimension(800,100));
		
		jrbPremierDevoir.setSelected(true);
		bgChoixNote.add(jrbPremierDevoir);
		bgChoixNote.add(jrbSecondDevoir);
		
		jrbPremierDevoir.setFont(font);
		jrbSecondDevoir.setFont(font);
		jpChoixDevoir.add(jrbPremierDevoir);
		jpChoixDevoir.add(jrbSecondDevoir);
		
		jpChoixDevoir.setBackground(Color.WHITE);
		jpChoixDevoir.setBorder(BorderFactory.createTitledBorder("Nature" +
				" de la note"));
		
		
		jpNote.setPreferredSize(new Dimension(400,65));
		jpNote.setBackground(Color.WHITE);
		jpNote.setBorder(BorderFactory.createTitledBorder("Note"));
		jtfNote.setPreferredSize(new Dimension(200,30));
		jtfNote.setFont(font);
		jpNote.add(jlNote);
		jpNote.add(jtfNote);
		
		
		jpCentre.setLayout(new GridLayout(6,1));
		jpCentre.add(jpClasse);
		jpCentre.add(jpEleve);
		jpCentre.add(jpMatiere);
		jpCentre.add(jpChoixSemestre);
		jpCentre.add(jpChoixDevoir);
		jpCentre.add(jpNote);
		
		jpSud.setBackground(Color.WHITE);
		jpSud.add(jbValidez);
		jpSud.add(jbAnnulez);
		
		this.setLayout(new BorderLayout());
		this.add(jpCentre, BorderLayout.CENTER);
		this.add(jpSud, BorderLayout.SOUTH);
		
		
	}
	
	public void addClasseJc(){
		for(int i = 0; i < cs.getListClasse().size(); i++){
			jcbClasse.addItem(cs.getListClasse().get(i));
		}

	}
	
	public void addMatiereInCombo(){
		Classe classeChoisi = (Classe)jcbClasse.getSelectedItem();
		try{
			jcbMatiere.removeAllItems();
			for(int i = 0; i < classeChoisi.getListMatiere().size(); i++){
				jcbMatiere.addItem(classeChoisi.getListMatiere().get(i));
			}
		}catch(Exception e){
			
		}
	}
	

	
	class ClasseChangeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			jcbEleve.removeAllItems();
			jcbMatiere.removeAllItems();
			Classe classeChoix = (Classe)jcbClasse.getSelectedItem();
			for(int i = 0; i < classeChoix.getListeEleves().size(); i++){
				jcbEleve.addItem(classeChoix.getListeEleves().get(i));
			}
			for(int j = 0; j < classeChoix.getListMatiere().size(); j++){
				jcbMatiere.addItem(classeChoix.getListMatiere().get(j));
			}
		}
	}
	
	class ValidezListener implements ActionListener{
		JOptionPane jop = new JOptionPane();
		public void actionPerformed(ActionEvent e){
			if(jtfNote.getText().length() > 0){
			
				try{
					Eleve eleve = (Eleve)jcbEleve.getSelectedItem();
					Classe classe = (Classe)jcbClasse.getSelectedItem();
					Matiere matiere = (Matiere)jcbMatiere.getSelectedItem();
					double note = Double.valueOf(jtfNote.getText()).doubleValue();
					int numeroDevoir = 0;
					if(jrbPremierDevoir.isSelected()){
						numeroDevoir = 1;
					}
					else if(jrbSecondDevoir.isSelected()){
						numeroDevoir = 2;
					}
				
					Semestre semestre = null;
					if(jrbPremierSemestre.isSelected()){
						semestre = Semestre.PREMIER;
					}else{
						semestre = Semestre.SECOND;
					}
					cs.getClasse(classe).getEleve(eleve).setDevoir(
							new NoteDevoir(matiere,semestre,numeroDevoir,note));
					cs.serializer();
					jop.showMessageDialog(null, "La note a été bien insérez \n " +
							"Elève : " + eleve + "\n" + 
							"Semetre : " + semestre + "\n" + 
							"Devoir : " + numeroDevoir + "\n" + 
							"Matière : " + matiere + "\n" + 
							"Note : " + note,
							"Note entrée", JOptionPane.INFORMATION_MESSAGE);
					
				}catch(NumberFormatException nfe){
					jop.showMessageDialog(null, "Le champ note ne doit contenir" +
							" que des caractères de nombres", "ERREUR DE NOTE",
							JOptionPane.WARNING_MESSAGE);
				}
				jtfNote.setText("");
			}else{
				jop.showMessageDialog(null, "Le champ note ne peut etre vide",
						"ERREUR", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
