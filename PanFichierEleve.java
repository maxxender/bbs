package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bbs.model.Classe;
import com.bbs.model.ClasseSerializer;
import com.bbs.model.Eleve;

	public class PanFichierEleve extends JPanel implements Serializable{
	
	private JPanel
			jpIdentite = new JPanel(),
			jpNord = new JPanel(),
			jpNord1 = new JPanel(),
			jpNord2 = new JPanel(),
			jpCentre = new JPanel(),
			jpNom = new JPanel(),
			jpDateInscription = new JPanel(),
			jpDateDernierPaiement = new JPanel(),
			jpMontantDernierPaiement = new JPanel();
	private PanPhoto jpPhoto = new PanPhoto();
	private PanIdentification panIdentification = new PanIdentification();
	
	private JLabel
			jlPhoto = new JLabel("Photo"),
			jlIdentification = new JLabel("identification"),
			jlDateInscription = new JLabel("Date inscription"),
			jlDateDernierPaiement = new JLabel("Date dernier paiement"),
			jlMontantDernierPaiement = new JLabel("Montant du dernier paiement"),
			jlIdentite = new JLabel("Identité"),
			jlClasses = new JLabel("Classes"),
			jlEleves = new JLabel("Nom Prénom");

	private JComboBox
			jcbClasses = new JComboBox(),
			jcbEleves = new JComboBox();
	private Eleve eleveChoisi;
	private Image photo;
	
	private int classeIndex = 0;

	private ClasseSerializer cs = new ClasseSerializer();
	public PanFichierEleve(){
		

		addClassesJC();
		jcbClasses.addActionListener(new ClasseChange());
		
		jcbClasses.addActionListener(new ClasseChange());
		jcbEleves.addActionListener(new EleveChangeListener());
		
		jcbClasses.setPreferredSize(new Dimension(220,25));
		jcbEleves.setPreferredSize(new Dimension(220,25));
		
		jpNord.setLayout(new GridLayout(1,2));
		jpNord.setBackground(Color.WHITE);
		jpNord1.add(jlClasses);
		jpNord1.add(jcbClasses);
		jpNord2.add(jlEleves);
		jpNord2.add(jcbEleves);
		jpNord.add(jpNord1);
		jpNord.add(jpNord2);
		
		panIdentification.setBackground(Color.WHITE);
		panIdentification.setBorder(BorderFactory.createBevelBorder(1));
		panIdentification.setPreferredSize(new Dimension(200,50));
		panIdentification.add(jlIdentification);
		
		jpDateInscription.setBackground(Color.WHITE);
		jpDateInscription.setBorder(BorderFactory.createBevelBorder(1));
		jpDateInscription.setPreferredSize(new Dimension(200,50));
		jpDateInscription.add(jlDateInscription);
		
		jpDateDernierPaiement.setBackground(Color.WHITE);
		jpDateDernierPaiement.setBorder(BorderFactory.createBevelBorder(1));
		jpDateDernierPaiement.setPreferredSize(new Dimension(200,50));
		jpDateDernierPaiement.add(jlDateDernierPaiement);
		
		jpMontantDernierPaiement.setBackground(Color.WHITE);
		jpMontantDernierPaiement.setBorder(BorderFactory.createBevelBorder(1));
		jpMontantDernierPaiement.setPreferredSize(new Dimension(200,50));
		jpMontantDernierPaiement.add(jlMontantDernierPaiement);
		
		jpIdentite.setLayout(new GridLayout(4,1));
		jpIdentite.add(panIdentification);
		jpIdentite.add(jpDateInscription);
		jpIdentite.add(jpDateDernierPaiement);
		jpIdentite.add(jpMontantDernierPaiement);
		
		jpPhoto.setBackground(Color.WHITE);
		jpPhoto.setBorder(BorderFactory.createTitledBorder("Photo"));
		
		jpCentre.setLayout(new GridLayout(1, 2));
		jpCentre.add(jpPhoto);
		jpCentre.add(jpIdentite);
		
		this.setLayout(new BorderLayout());
		this.add(jpCentre, BorderLayout.CENTER);
		this.add(jpNord, BorderLayout.NORTH);
		
	}
	
	public void addClassesJC(){
		

		Classe[] classes = new Classe[new ClasseSerializer().getListClasse().size()];
		try{
			for(int  i = 0; i < new ClasseSerializer().getListClasse().size(); i++){
				classes[i] = new ClasseSerializer().getListClasse().get(i);
			}
			jcbClasses = new JComboBox(classes);
	
			ClasseSerializer cs = new ClasseSerializer();
			jcbEleves.removeAllItems();
			for(int i = 0; i < new ClasseSerializer().getClasse(
					(Classe)jcbClasses.getSelectedItem()).getListeEleves().size(); i++){
				jcbEleves.addItem(cs.getClasse((Classe)jcbClasses.getSelectedItem()
						).getListeEleves().get(i));
			
			}
		if(jcbEleves.getSelectedItem() != null){
			eleveChoisi = (Eleve) jcbEleves.getSelectedItem();
			jpPhoto.setPhoto(eleveChoisi.getPhoto());
			jpPhoto.repaint();
			panIdentification.setDescription("Monsieur " + eleveChoisi);
			panIdentification.repaint();
			panIdentification.setForeground(Color.WHITE);
		}
		}catch(Exception e){
			
		}

	}
	
	class ClasseActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
		}
		
	}
	

	
	public void addPhoto(){
			this.photo = eleveChoisi.getPhoto();
	}
	
	class EleveChangeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(jcbEleves.getSelectedItem() != null){
				eleveChoisi = (Eleve) jcbEleves.getSelectedItem();
			//	jpPhoto.setPhoto(eleveChoisi.getPhoto());
				jpPhoto.repaint();
				panIdentification.setDescription("Monsieur " + eleveChoisi);
				panIdentification.repaint();
				panIdentification.setForeground(Color.WHITE);
			}
			
		}
	}
	
	class ClasseChange implements ActionListener{

		public void actionPerformed(ActionEvent event){
			jcbEleves.removeAllItems();
			
			for(int i = 0; i < cs.getClasse((Classe)jcbClasses.getSelectedItem()).getListeEleves().size(); i++){
				jcbEleves.addItem(cs.getClasse((Classe)jcbClasses.getSelectedItem()).getListeEleves().get(i));
			}
	
		}
		

	}

}
