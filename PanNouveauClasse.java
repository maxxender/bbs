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
import com.bbs.model.Niveau;

public class PanNouveauClasse extends JPanel{
	
	private JPanel
			jpNomClasse = new JPanel(),
			jpMontantContribution = new JPanel(),
			jpNiveauEnseignement = new JPanel(),
			jpCentre = new JPanel(),
			jpSud = new JPanel();
	
	private JLabel
			jlNomClasse = new JLabel("Nom"),
			jlMontantContribution = new JLabel("Montant"),
			jlNiveauEnseignement = new JLabel("Niveau");
	
	private JComboBox jcbNiveau = new JComboBox();

	private JTextField
			jtfNomClasse = new JTextField(),
			jtfMontantContribution = new JTextField();
	private JButton
			jbValidez = new JButton("Validez"),
			jbAnnulez = new JButton("Annulez");
	
	private Font font = new Font("ITALIC", Font.BOLD, 15);
	
	public PanNouveauClasse(){
		
		jcbNiveau.setPreferredSize(new Dimension(120,30));
		jtfNomClasse.setPreferredSize(new Dimension(120,30));
		jtfMontantContribution.setPreferredSize(new Dimension(120,30));
		
		
		
		jpNiveauEnseignement.setBackground(Color.WHITE);
		jpNiveauEnseignement.setBorder(BorderFactory.createTitledBorder("Niveau de la classe"));
		jpNiveauEnseignement.add(jlNiveauEnseignement);
		jcbNiveau.addItem(Niveau.PRIMAIRE);
		jcbNiveau.addItem(Niveau.SECONDAIRE);
		jpNiveauEnseignement.add(jcbNiveau);
		
		jpNomClasse.setBackground(Color.WHITE);
		jpNomClasse.setBorder(BorderFactory.createTitledBorder("Nom de la classe"));
		jpNomClasse.add(jlNomClasse);
		jtfNomClasse.setFont(font);
		jpNomClasse.add(jtfNomClasse);
		
		jpMontantContribution.setBackground(Color.WHITE);
		jpMontantContribution.setBorder(BorderFactory.createTitledBorder("Montant de la contribution"));
		jpMontantContribution.add(jlMontantContribution);
		jtfMontantContribution.setFont(font);
		jpMontantContribution.add(jtfMontantContribution);
		
		jpCentre.setLayout(new GridLayout(3,1));
		jpCentre.add(jpNiveauEnseignement);
		jpCentre.add(jpNomClasse);
		jpCentre.add(jpMontantContribution);
		
		
		jbValidez.addActionListener(new ValidezListener());
		
		jpSud.add(jbValidez);
		jpSud.add(jbAnnulez);
		
		this.setLayout(new BorderLayout());
		this.add(jpCentre, BorderLayout.CENTER);
		this.add(jpSud, BorderLayout.SOUTH);
	}
	
	public void raz(){
		this.jtfMontantContribution.setText("");
		this.jtfNomClasse.setText("");
	}
	
	
	
	class ValidezListener implements ActionListener{
		ClasseSerializer cs = new ClasseSerializer();
		
		public void actionPerformed(ActionEvent e){
		
			String nomClasse = jtfNomClasse.getText();
			JOptionPane jop = new JOptionPane();
		
		
			if(nomClasse.length() > 0){
				try{
					Niveau niveau = (Niveau)jcbNiveau.getSelectedItem();
					Integer entreeMontant = new Integer(jtfMontantContribution.getText());
					int montant = entreeMontant.intValue();
					cs.addClasse(new Classe(nomClasse,niveau,montant));
					cs.serializer();
					raz();
					
					jop.showMessageDialog(null, "Nouvelle classe créez \n" +
							"Nom de la classe : " + nomClasse + "\n" + 
							"Montant contribution : " + montant,
							"Nouvelle classe", JOptionPane.INFORMATION_MESSAGE);
					
				}catch(NumberFormatException nfe){
					jop.showMessageDialog(null, "Le montant de contribution \n" +
							"que vous avez entrée n'est pas valide. \n" +
							"Veuillez réessayez svp", "ERREUR"
							,JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				jop.showMessageDialog(null, "Le nom de la classe ne peut pas etre" +
						" vide \n Veuillez réessayez svp", "ERREUR", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
}
