package com.bbs.vue;

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
import com.bbs.model.Matiere;
import com.bbs.model.TypeMatiere;

public class PanNouveauMatiere extends JPanel{
	
	private JLabel
	jlNomMatiere = new JLabel("Nom        "),
	jlClasseMatiere = new JLabel("Classe     "),
	jlCoefficientMatiere = new JLabel("Coéfficient"),
	jlTypeMatiere = new JLabel("Type       ");
	
	private JTextField
	jtfNomMatiere = new JTextField(),
	jtfCoefficientMatiere = new JTextField();
	
	private JComboBox
	jcbClasseMatiere = new JComboBox(),
	jcbTypeMatiere = new JComboBox(),
	jcbCoefficientMatiere = new JComboBox();
	
	private JPanel
	panNomMatiere = new JPanel(),
	panClasseMatiere = new JPanel(),
	panCoefficientMatiere = new JPanel(),
	panTypeMatiere = new JPanel(),
	panBoutton = new JPanel();
	
	private JButton
	validez = new JButton("VALIDEZ"),
	annulez = new JButton("ANNULEZ");
	
	private ClasseSerializer cs = new ClasseSerializer();
	private Font font = new Font("ITALIC", Font.BOLD, 15);
	
	public PanNouveauMatiere(){
		
		addClasseInJc();
		validez.addActionListener(new ValidezListener());
		
		this.panNomMatiere.setBorder(BorderFactory.createTitledBorder("Nom de la matière"));
		this.panNomMatiere.setBackground(Color.white);
		this.panNomMatiere.add(jlNomMatiere);
		this.jtfNomMatiere.setPreferredSize(new Dimension(200,30));
		this.panNomMatiere.add(jtfNomMatiere);
		jtfNomMatiere.setFont(font);
		
		jcbTypeMatiere.addItem(TypeMatiere.LITTERAIRE);
		jcbTypeMatiere.addItem(TypeMatiere.SCIENCE);
		
		this.panTypeMatiere.setBorder(BorderFactory.createTitledBorder("Type de matière"));
		this.panTypeMatiere.setBackground(Color.WHITE);
		this.panTypeMatiere.add(jlTypeMatiere);
		this.jcbTypeMatiere.setPreferredSize(new Dimension(200,30));
		this.panTypeMatiere.add(jcbTypeMatiere);
		
		
		jcbCoefficientMatiere.setFont(font);
		jcbCoefficientMatiere.addItem(1);
		jcbCoefficientMatiere.addItem(2);
		jcbCoefficientMatiere.addItem(3);
		jcbCoefficientMatiere.addItem(4);
		jcbCoefficientMatiere.setSelectedIndex(0);

		this.panCoefficientMatiere.setBorder(BorderFactory.createTitledBorder("Coéfficient de la matière"));
		this.panCoefficientMatiere.setBackground(Color.white);
		this.panCoefficientMatiere.add(jlCoefficientMatiere);
		this.jcbCoefficientMatiere.setPreferredSize(new Dimension(200,30));
		this.panCoefficientMatiere.add(jcbCoefficientMatiere);
		jcbCoefficientMatiere.setSelectedItem(0);
		
		this.panClasseMatiere.setBorder(BorderFactory.createTitledBorder("Classe de matière"));
		this.panClasseMatiere.setBackground(Color.WHITE);
		this.panClasseMatiere.add(jlClasseMatiere);
		this.jcbClasseMatiere.setPreferredSize(new Dimension(200,30));
		this.panClasseMatiere.add(jcbClasseMatiere);
		jcbClasseMatiere.setFont(font);
		
		this.panBoutton.add(validez);
		this.panBoutton.add(annulez);
		
		this.add(panClasseMatiere);
		this.add(panNomMatiere);
		this.add(panTypeMatiere);
		this.add(panCoefficientMatiere);
		this.add(panBoutton);
		this.setLayout(new GridLayout(5,1,5,5));
	}
	
	public void addClasseInJc(){
		for(int i = 0; i < cs.getListClasse().size(); i++){
			jcbClasseMatiere.addItem((Classe)cs.getListClasse().get(i));
		}
	}
	
	class ValidezListener implements ActionListener{
		JOptionPane jop = new JOptionPane();
		public void actionPerformed(ActionEvent e){
			
			
			if(jtfNomMatiere.getText().length() == 0){
				jop.showMessageDialog(null, "Le champ NOM de la matiere ne peut " +
						"être vide", "Champ vide", JOptionPane.WARNING_MESSAGE);
			}else{
				Classe choixClasse = (Classe)jcbClasseMatiere.getSelectedItem();
				cs.getClasse(choixClasse).addMatiere(new Matiere(
						jtfNomMatiere.getText(),
						(TypeMatiere) jcbTypeMatiere.getSelectedItem(),
						(Integer) jcbCoefficientMatiere.getSelectedItem()));
				cs.serializer();
				jop.showMessageDialog(null, "Nouvelle matiere créez \n" +
					"Nom de la matière : " + jtfNomMatiere.getText() + "\n" +
					"Classe : " + choixClasse + "\n" + 
					"Coéfficient de la matière : "+
					jcbCoefficientMatiere.getSelectedItem()
					+ "\n Type de la matière : " + jcbTypeMatiere.getSelectedItem(),
					"Nouvelle matière",
					JOptionPane.INFORMATION_MESSAGE);
				jtfNomMatiere.setText("");
			}
		}
	}
	
}
