package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PanNouveauPayment extends JPanel{
	
	private JLabel
			jlClasse = new JLabel("Classe "),
			jlEleve = new JLabel("Elève  "),
			jlMontant = new JLabel("Montant");
	
	private JComboBox
			jcbClasse = new JComboBox(),
			jcbEleve = new JComboBox();
	
	private JButton
			jbValidez = new JButton("Validez"),
			jbAnnulez = new JButton("Annulez");
	
	private JPanel
			jpClasse = new JPanel(),
			jpEleve = new JPanel(),
			jpMontant = new JPanel(),
			jpCentre = new JPanel(),
			jpSud = new JPanel();
	
	private JTextField jtfMontant = 
			new JTextField();
	private ClasseSerializer cs = new ClasseSerializer();
	private Font font = new Font("ITALIC", Font.BOLD, 15);
	
	public PanNouveauPayment(){
		
		addClasseInJcc();
		addEleveInJce();
		jcbClasse.addActionListener(new ClasseChangeListener());
		jbValidez.addActionListener(new ValidezListener());
		
		jpClasse.setPreferredSize(new Dimension(300,65));
		jpClasse.setBackground(Color.WHITE);
		jpClasse.setBorder(BorderFactory.createTitledBorder("Classe de l'élève"));
		jcbClasse.setPreferredSize(new Dimension(200,30));
		jcbClasse.setFont(font);
		jpClasse.add(jlClasse);
		jpClasse.add(jcbClasse);
		
		jpEleve.setPreferredSize(new Dimension(300,65));
		jpEleve.setBackground(Color.WHITE);
		jpEleve.setBorder(BorderFactory.createTitledBorder("Nom et prénom de l'élève"));
		jcbEleve.setPreferredSize(new Dimension(200,30));
		jcbEleve.setFont(font);
		jpEleve.add(jlEleve);
		jpEleve.add(jcbEleve);
		
		jpMontant.setPreferredSize(new Dimension(300,65));
		jpMontant.setBackground(Color.WHITE);
		jpMontant.setBorder(BorderFactory.createTitledBorder("Montant à payez"));
		jtfMontant.setPreferredSize(new Dimension(200,30));
		jtfMontant.setFont(font);
		jpMontant.add(jlMontant);
		jpMontant.add(jtfMontant);
		
		jpCentre.setLayout(new GridLayout(3,1));
		jpCentre.add(jpClasse);
		jpCentre.add(jpEleve);
		jpCentre.add(jpMontant);
		
		jpSud.setBackground(Color.WHITE);
		jpSud.add(jbValidez);
		jpSud.add(jbAnnulez);
		
		this.setLayout(new BorderLayout());
		this.add(jpCentre, BorderLayout.CENTER);
		this.add(jpSud, BorderLayout.SOUTH);
	}
	
	public void addClasseInJcc(){
		Classe classeChoisi = (Classe) jcbClasse.getSelectedItem();
		for(int i = 0; i < cs.getListClasse().size(); i++){
			this.jcbClasse.addItem((Classe)cs.getListClasse().get(i));
			
		}
	
	}
	
	public void addEleveInJce(){
		Classe classeChoisi = (Classe) jcbClasse.getSelectedItem();
	
			if(cs.getListClasse().size() > 0){
				for(int i = 0; i < cs.getClasse(classeChoisi).getListeEleves()
						.size(); i++){
					jcbEleve.addItem(cs.getClasse(classeChoisi).getListeEleves()
							.get(i));
					
				}
			}

	}
	
	class ClasseChangeListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			Classe classeChoisi = (Classe) jcbClasse.getSelectedItem();
			jcbEleve.removeAllItems();
			for(int i = 0; i < cs.getClasse(classeChoisi).getListeEleves().size(); i++){
				jcbEleve.addItem(cs.getClasse(classeChoisi).getListeEleves().get(i));
			}
		}
	}
	
	class ValidezListener implements ActionListener{
		public void actionPerformed(ActionEvent e){

			Eleve eleveChoisi = ((Eleve) jcbEleve.getSelectedItem());
		
			JOptionPane jop = new JOptionPane();
			try{
				double montant = Double.valueOf(jtfMontant.getText()).doubleValue();
				
				if(montant > eleveChoisi.getResteApayez() ){
					jop.showMessageDialog(null, "Le montant que vous avez \n entrée comme paiement \n" +
							" est supérieur au reste due par " + eleveChoisi + "\n" +
									" Veuillez réessayez", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}else{
					
					cs.getClasse(((Classe)jcbClasse.getSelectedItem()))
					.getEleve( ((Eleve)eleveChoisi)).setPaiement(montant);
					cs.serializer();
					jop.showMessageDialog(null, "Paiement éffectuez pour \n" +
							eleveChoisi.toString() +"\n" + 
							"Classe : " + eleveChoisi.getClasse().toString() + "\n"+ 
							"Montant payé : " + montant + "\n" + 
							"Reste à payez : " + eleveChoisi.getResteApayez(),
							"Paiement éffectuez", 
							JOptionPane.INFORMATION_MESSAGE);
					jtfMontant.setText("");
				}
			

			
			}catch(NumberFormatException nfe){
					jop.showMessageDialog(null,"Vous n'avez rien entrée comme montant\n" +
							"ou vous n'avez pas entrée un nombre\n" +
							"Veuillez réessayez ", "ERREUR", JOptionPane.ERROR_MESSAGE);
				
			}
		
			
	}
			
		
	}
	
}
