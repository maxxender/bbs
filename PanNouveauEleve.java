package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bbs.model.Classe;
import com.bbs.model.ClasseSerializer;
import com.bbs.model.Eleve;
import com.bbs.model.Sexe;

public class PanNouveauEleve extends JPanel implements Serializable{
	
	private JLabel
			jlNom = new JLabel("Nom"),
			jlPrenom = new JLabel("Pr�nom"),
			jlPhoto = new JLabel("Photo"),
			jlClasse = new JLabel("Classe"),
			jlSsexe = new JLabel("Sexe");
	

	private JComboBox jcbClasse = new JComboBox(),
					jcbSexe = new JComboBox();
	private JPanel
			jpCentre = new JPanel(),
			jpSouth = new JPanel(),
			jpNom = new JPanel(),
			jpPrenom = new JPanel(),
			jpPhoto = new JPanel(),
			jpClasse = new JPanel(),
			jpSexe = new JPanel();

	private JButton 
			JBvalidez = new JButton("Validez"),
			JBannulez = new JButton("Annulez");

	private JTextField 
			jtfNom = new JTextField(),
			jtfPrenom = new JTextField();
	

	public PanNouveauEleve(){
		
		this.addClasseJC();
		this.JBvalidez.addActionListener(new ValidezListener());

		jpNom.setBackground(Color.white);
		jpNom.setPreferredSize(new Dimension(220, 60));
		jpNom.add(jlNom);
		jtfNom.setPreferredSize(new Dimension(100,25));
		jtfNom.setFont(new Font("ITALIC", Font.BOLD, 15));
		jpNom.add(jtfNom);
		jpNom.setBorder(BorderFactory.createTitledBorder("Nom du l'�l�ve"));
		
		jpPrenom.setBackground(Color.white);
		jpPrenom.setPreferredSize(new Dimension(220,60));
		jpPrenom.add(jlPrenom);
		jtfPrenom.setPreferredSize(new Dimension(120,25));
		jtfPrenom.setFont(new Font("ITALIC", Font.BOLD, 15));
		jpPrenom.add(jtfPrenom);
		jpPrenom.setBorder(BorderFactory.createTitledBorder("Pr�nom de l'�l�ve"));
		
		jpSexe.setBackground(Color.white);
		jpSexe.setPreferredSize(new Dimension(220, 60));
		jpSexe.add(jlSsexe);
		jcbSexe.addItem(Sexe.Masculin);
		jcbSexe.addItem(Sexe.Feminin);
		jcbSexe.setPreferredSize(new Dimension(120,25));
		jcbSexe.setFont(new Font("ITALIC", Font.BOLD, 15));
		jpSexe.add(jcbSexe);
		jpSexe.setBorder(BorderFactory.createTitledBorder("Sexe de l'�l�ve"));
		
		jpClasse.setBackground(Color.white);
		jpClasse.setPreferredSize(new Dimension(220,60));
		jpClasse.add(jlClasse);

		jcbClasse.setPreferredSize(new Dimension(120,25));
		jcbClasse.setFont(new Font("ITALIC", Font.BOLD, 15));
		jpClasse.add(jcbClasse);
		jpClasse.setBorder(BorderFactory.createTitledBorder("Classe du l'�l�ve"));
		
		jpCentre.setLayout(new GridLayout(2,2));
		jpCentre.add(jpNom);
		jpCentre.add(jpPrenom);
		jpCentre.add(jpClasse);
		jpCentre.add(jpSexe);
		
		
		jpSouth.add(JBvalidez);
		jpSouth.add(JBannulez);
		
		this.setLayout(new BorderLayout());
		this.add(jpCentre, BorderLayout.CENTER);
		this.add(jpSouth, BorderLayout.SOUTH);
	}

	public void addClasseJC(){
		ClasseSerializer cs = new ClasseSerializer();
		try{
			for(int i = 0; i < cs.getListClasse().size(); i++){
				jcbClasse.addItem(cs.getListClasse().get(i));
			}
		}catch(Exception e){
			
		}
	}
	
	public void raz(){
		this.jtfNom.setText("");
		this.jtfPrenom.setText("");
	}
	
	class ValidezListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			Classe classe = (Classe) jcbClasse.getSelectedItem();
			Sexe sexe = (Sexe) jcbSexe.getSelectedItem();
			String nom = jtfNom.getText();
			String prenom = jtfPrenom.getText();
			
			JOptionPane jop = new JOptionPane();
			if(nom.length() < 1 && prenom.length() > 0){
				jop.showMessageDialog(null, "Le champ nom ne peut etre vide \n" +
						"Veuillez r�essayez", "ERREUR", JOptionPane.ERROR_MESSAGE);
			}
			else if(prenom.length() < 1 && nom.length() > 0){
				jop.showMessageDialog(null, "Le champ Pr�nom ne peut etre vide \n" +
						"Veuillez r�essayez", "ERREUR", JOptionPane.ERROR_MESSAGE);
			}
			else if(prenom.length() < 1 && nom.length() < 1){
				jop.showMessageDialog(null, "Vous n'avez entr�e ni le NOM " +
						"ni le PRENOM\n" +
						"Veuillez r�essayez", "ERREUR", JOptionPane.ERROR_MESSAGE);
			}else{
				Eleve eleve = new Eleve(nom,prenom,sexe);
				eleve.setClasse(classe);
				//es.addEleve(eleve);
				//es.serializerEleve();
				ClasseSerializer cs = new ClasseSerializer();
				cs.getClasse(classe).ajoutezEleve(eleve);
				cs.serializer();
				jop.showMessageDialog(null, "Nouvelle inscription \n" +
						"Nom �l�ve : " + nom + "\n" +
						"Pr�nom �l�ve : " + prenom + "\n" + 
						"classe �l�ve : " + jcbClasse.getSelectedItem()
						, "Inscription r�ussie", 
						JOptionPane.INFORMATION_MESSAGE);
				raz();
			}
		}
	}

}