package com.bbs.vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu jmNouveau = new JMenu("Nouveau");
	private JMenu jmFichier = new JMenu("Fichier");
	private JMenu jmApropos = new JMenu("A propos");
	
	private JMenuItem
			jmiNouveauEleve = new JMenuItem("Elève"),
			jmiNouveauNoteDevoir = new JMenuItem("Note de devoir"),
			jmiNouveauNoteInterro = new JMenuItem("Note d'interrogation"),
			jmiNouveauClasse = new JMenuItem("Classe"),
			jmiNouveauProfesseur = new JMenuItem("Professeur"),
			jmiNouveauPayment = new JMenuItem("Payment scolarité"),
			jmiNouveauMatiere = new JMenuItem("Matière"),
			jmiDeveloppeur = new JMenuItem("developpeur"),
			jmiLicense = new JMenuItem("License");
	
	private JMenuItem
			jmiFichierEleve = new JMenuItem("Eleve"),
			jmiFichierNote = new JMenuItem("Note"),
			jmiFichierClasse = new JMenuItem("Classe"),
			jmiFichierProfesseur = new JMenuItem("Professeur"),
			jmiFichierPayment = new JMenuItem("Payment Scolarité");
	
	private CardLayout cl = new CardLayout();
	private JPanel content = new JPanel();
	private PanPresentation jpPresentation = new PanPresentation();
	private PanNouveauEleve jpNouveauEleve = new PanNouveauEleve();
	private PanFichierEleve jpFichierEleve = new PanFichierEleve();
	private PanFichierNote jpFichierNote = new PanFichierNote();
	private PanNouveauNoteDevoir jpNouveauNoteDevoir = new PanNouveauNoteDevoir();
	private PanNouveauNoteInterro jpNouveauNoteInterro = new PanNouveauNoteInterro();
	private PanNouveauPayment jpNouveauPayment = new PanNouveauPayment();
	private PanNouveauClasse jpNouveauClasse = new PanNouveauClasse();
	private PanFichierClasses jpFichierClasse = new PanFichierClasses();
	private PanNouveauMatiere jpNouveauMatiere = new PanNouveauMatiere();
	private PanFichierPayment jpFichierPayment = new PanFichierPayment();
	
	public Fenetre(){
		this.setTitle("BBS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		
		jmApropos.add(jmiDeveloppeur);
		jmApropos.add(jmiLicense);
		
		jmNouveau.add(jmiNouveauEleve);
		jmNouveau.add(jmiNouveauClasse);
		jmNouveau.add(jmiNouveauPayment);
		jmNouveau.add(jmiNouveauMatiere);
		jmNouveau.add(jmiNouveauNoteDevoir);
		jmNouveau.add(jmiNouveauNoteInterro);
		jmNouveau.add(jmiNouveauProfesseur);
		
		jmiNouveauEleve.addActionListener(new JmiNouveauEleveListener());
		jmiFichierEleve.addActionListener(new JmiFichierEleveListener());
		jmiFichierNote.addActionListener(new JmiFichierNoteListener());
		jmiNouveauNoteDevoir.addActionListener(new JmiNouveauNoteDevoirListener());
		jmiNouveauNoteInterro.addActionListener(new JmiNouveauNoteInterroListener());
		jmiNouveauPayment.addActionListener(new JmiNouveauPaymentListener());
		jmiNouveauClasse.addActionListener(new JmiNouveauClasseListener());
		jmiFichierClasse.addActionListener(new JmiFichierClasseListener());
		jmiNouveauMatiere.addActionListener(new NouveauMatiereListener());
		jmiDeveloppeur.addActionListener(new JmiDeveloppeurListener());
		jmiLicense.addActionListener(new JmiLicenseListener());
		jmiFichierPayment.addActionListener(new JmiFichierPaymentListener());
		
		jmFichier.add(jmiFichierNote);
		jmFichier.add(jmiFichierEleve);
		jmFichier.add(jmiFichierClasse);
		jmFichier.add(jmiFichierPayment);
		jmFichier.add(jmiFichierProfesseur);
		
		
		menuBar.add(jmNouveau);
		menuBar.add(jmFichier);
		menuBar.add(jmApropos);
		
		content.setLayout(cl);
		content.add(jpPresentation, "jpPresentation");
		content.add(jpNouveauEleve, "jpNouveauEleve");
		content.add(jpFichierEleve, "jpFichierEleve");
		content.add(jpFichierNote, "jpFichierNote");
		content.add(jpNouveauNoteDevoir, "jpNouveauNoteDevoir");
		content.add(jpNouveauNoteInterro,"jpNouveauNoteInterro");
		content.add(jpNouveauPayment, "jpNouveauPayment");
		content.add(jpNouveauClasse, "jpNouveauClasse");
		content.add(jpFichierClasse, "jpFichierClasse");
		content.add(jpNouveauMatiere,"jpNouveauMatiere");
		content.add(jpFichierPayment, "jpFichierPayment");
		
		this.setJMenuBar(menuBar);
		this.getContentPane().add(content);
		this.setVisible(true);
	}
	
	class JmiNouveauEleveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanNouveauEleve(), "jpNouveauEleve");
			cl.show(content, "jpNouveauEleve");
		}
	}
	
	class JmiFichierEleveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanFichierEleve(), "jpFichierEleve");
			cl.show(content, "jpFichierEleve");
		}
	}
	
	class JmiDeveloppeurListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,"Developpeur du logiciel \n\n" +
					" BADAROU Matinou","Infos développeur" ,JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	class JmiLicenseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,"Opensource",
					"Infos droits de license",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class JmiFichierPaymentListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanFichierPayment(), "jpFichierPayment");
			cl.show(content, "jpFichierPayment");
		}
	}
	
	class JmiFichierNoteListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanFichierNote(), "jpFichierNote");
			cl.show(content, "jpFichierNote");
		}
	}
	
	class JmiNouveauNoteDevoirListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanNouveauNoteDevoir(), "jpNouveauNoteDevoir");
			cl.show(content, "jpNouveauNoteDevoir");
		}
	}
	
	class JmiNouveauNoteInterroListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanNouveauNoteInterro(), "jpNouveauNoteInterro");
			cl.show(content, "jpNouveauNoteInterro");
		}
	}
	
	class JmiNouveauPaymentListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanNouveauPayment(), "jpNouveauPayment");
			cl.show(content, "jpNouveauPayment");
		}
	}
	
	class JmiNouveauClasseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanNouveauClasse(), "jpNouveauClasse");
			cl.show(content, "jpNouveauClasse");
		}
	}
	
	class JmiFichierClasseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			cl.show(content, "jpFichierClasse");
		}
	}
	
	class NouveauMatiereListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			content.add(new PanNouveauMatiere(), "jpNouveauMatiere");
			cl.show(content, "jpNouveauMatiere");
		}
	}
	
}
