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
import com.bbs.model.NoteInterro;
import com.bbs.model.Semestre;

public class PanNouveauNoteInterro extends JPanel{
	
	private JPanel
			jpClasse = new JPanel(),
			jpEleve = new JPanel(),
			jpMatiere = new JPanel(),
			jpChoixSemestre = new JPanel(),
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
			jrbPremiereInterro = new JRadioButton("Première interro"),
			jrbSecondInterro = new JRadioButton("Second interro"),
			jrbTroisiemeInterro = new JRadioButton("Troisième interro"),
			jrbQuatriemeInterro = new JRadioButton("Quatrième interro");
	
	private JTextField jtfNote = new JTextField();
	private JComboBox
			jcbClasse = new JComboBox(),
			jcbEleve = new JComboBox(),
			jcbMatiere = new JComboBox();
	private ClasseSerializer cs = new ClasseSerializer();
	
	private Font font = new Font("ITALIC", Font.BOLD, 15);
	
	public PanNouveauNoteInterro(){
	
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
		
		jpChoixInterro.setPreferredSize(new Dimension(800,100));
		
		jrbPremiereInterro.setSelected(true);
		bgChoixNote.add(jrbPremiereInterro);
		bgChoixNote.add(jrbSecondInterro);
		bgChoixNote.add(jrbTroisiemeInterro);
		bgChoixNote.add(jrbQuatriemeInterro);
		
		jrbPremiereInterro.setFont(font);
		jrbSecondInterro.setFont(font);
		jrbTroisiemeInterro.setFont(font);
		jrbQuatriemeInterro.setFont(font);
		
		
		jpChoixInterro.add(jrbPremiereInterro);
		jpChoixInterro.add(jrbSecondInterro);
		jpChoixInterro.add(jrbTroisiemeInterro);
		jpChoixInterro.add(jrbQuatriemeInterro);
		
		
		jpChoixInterro.setBackground(Color.WHITE);
		jpChoixInterro.setBorder(BorderFactory.createTitledBorder("Nature" +
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
		jpCentre.add(jpChoixInterro);
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
		try{
			Classe classeChoisi = (Classe)jcbClasse.getSelectedItem();
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
		
		public int getInterroChoisi(){
			int numeroInterro = 0;
			
			if(jrbPremiereInterro.isSelected() == true)
				numeroInterro = 1;
			else if(jrbSecondInterro.isSelected() == true)
				numeroInterro = 2;
			else if(jrbTroisiemeInterro.isSelected() == true)
				numeroInterro = 3;
			else if(jrbQuatriemeInterro.isSelected() == true)
				numeroInterro = 4;
			return numeroInterro;
		}
		
		public void actionPerformed(ActionEvent e){
			if(jtfNote.getText().length() > 0){
				
				try{
					Eleve eleve = (Eleve)jcbEleve.getSelectedItem();
					Classe classe = (Classe)jcbClasse.getSelectedItem();
					Matiere matiere = (Matiere)jcbMatiere.getSelectedItem();
					double note = Double.valueOf(jtfNote.getText()).doubleValue();
					int numeroInterro = getInterroChoisi();
					Semestre semestre = (jrbPremierSemestre.isSelected()) ?
							Semestre.PREMIER : Semestre.SECOND;
					cs.getClasse(classe).getEleve(eleve).setInterro(
							new NoteInterro(matiere,semestre,numeroInterro,note));
					cs.serializer();
					jop.showMessageDialog(null, "La note a été bien insérez \n " + 
							"Elève : " + eleve + "\n" + 
							"Semestre : " + semestre + "\n" + 
							"Interrogation : " + numeroInterro + "\n" + 
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
