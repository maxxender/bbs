package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PanFichierPayment extends JPanel{
	
	private JLabel
				jlClasse = new JLabel("CLASSES DE L ELEVE"),
				jlEleve = new JLabel("NOM ET PRENOM DE L 'ELEVE");
	
	private JComboBox
					jcbClasse = new JComboBox(),
					jcbEleve = new JComboBox();
	private JPanel panNord = new JPanel(),
					panSud = new JPanel();
	private JPanel panCentre = new JPanel();
	private ClasseSerializer cs = new ClasseSerializer();
	private JTable table;
	private int xBoite = 0, yBoite = 0;
	private JButton
			jbDebiteurs = new JButton("LISTE DES ELEVES QUI DOIVENT"),
			jbSoldeurs = new JButton("LISTE DES ELEVES AYANT SOLDES");
	
	private ClasseSerializer fichier = new ClasseSerializer();
	
	public PanFichierPayment(){
		
		addClassesJC();
		jcbEleve.addActionListener(new EleveChangedListener());
		jcbClasse.addActionListener(new ClasseChangedListener());
		jcbClasse.setPreferredSize(new Dimension(200,25));
		jcbEleve.setPreferredSize(new Dimension(200,25));
		
		panNord.add(jlClasse);
		panNord.add(jcbClasse);
		panNord.add(jlEleve);
		panNord.add(jcbEleve);
		
		panSud.add(jbDebiteurs);
		panSud.add(jbSoldeurs);
		
		setDataTable((Eleve)jcbEleve.getSelectedItem());
		panCentre.setLayout(new BorderLayout());
		panCentre.add(new JScrollPane(table), BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(panNord, BorderLayout.NORTH);
		this.add(panCentre, BorderLayout.CENTER);
		this.add(panSud, BorderLayout.SOUTH);
	}
	
	
	public void addClassesJC(){
		

		Classe[] classes = new Classe[new ClasseSerializer().getListClasse().size()];
		try{
			for(int  i = 0; i < new ClasseSerializer().getListClasse().size(); i++){
				classes[i] = new ClasseSerializer().getListClasse().get(i);
			}
			jcbClasse = new JComboBox(classes);
	
			ClasseSerializer cs = new ClasseSerializer();
			jcbEleve.removeAllItems();
			for(int i = 0; i < new ClasseSerializer().getClasse(
					(Classe)jcbClasse.getSelectedItem()).getListeEleves().size(); i++){
				jcbEleve.addItem(cs.getClasse((Classe)jcbClasse.getSelectedItem()
						).getListeEleves().get(i));
			
			}
		}catch(Exception e){
			
		}

	}
	
	
	public void setDataTable(Eleve eleve){
		try{
		//Eleve eleve = (Eleve)jcbEleve.getSelectedItem();
		String[] title = {"CLASSE","MONTANT SCOLARITE","ELEVE","RESTE A PAYEZ"};
		Object[][] datas = {
				{
					((Eleve)jcbEleve.getSelectedItem()).getClasse(), eleve.getClasse().getMontantContribution(),
					eleve, eleve.getResteApayez()
				}
		};
		ModelTableau model = new ModelTableau(datas,title);
		table = new JTable(model);
		}catch(Exception e){
			
		}
	}
	
	class Boite extends JDialog{
		public Boite(JFrame parent, String title, boolean modal, JTable table){
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

	
	
	class EleveChangedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				Eleve eleve = (Eleve)jcbEleve.getSelectedItem();
				String[] title = {"CLASSE","MONTANT SCOLARITE","ELEVE","RESTE A PAYEZ"};
				Object[][] datas = {
						{
							((Eleve)jcbEleve.getSelectedItem()).getClasse(), eleve.getClasse().getMontantContribution(),
							eleve, eleve.getResteApayez()
						}
				};
				ModelTableau model = new ModelTableau(datas,title);
				table = new JTable(model);
				Boite boite = new Boite(null,"PAYMENT SCOLARITE", false,table);
				}catch(Exception exc){
					
				}
			
			
		}
	}
	
	
	class ClasseChangedListener implements ActionListener{

		public void actionPerformed(ActionEvent event){
			jcbEleve.removeAllItems();
			
			for(int i = 0; i < cs.getClasse((Classe)jcbClasse.getSelectedItem()).getListeEleves().size(); i++){
				jcbEleve.addItem(cs.getClasse((Classe)jcbClasse.getSelectedItem()).getListeEleves().get(i));
			}
		}
		
	}

	
	class ModelTableau extends AbstractTableModel{
		private Object[][] datas;
		private String[] title;
		public ModelTableau(Object[][] datas, String[] title){
			this.datas = datas;
			this.title = title;
		}
			
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.title.length;
		}

		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.datas.length;
		}

		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			return this.datas[row][col];
		}
		
		public String getColumnName(int col){
			return this.title[col];
		}
		
		
		
	}
	
	class JbDebiteursListener implements ActionListener{
		Classe classe;
		Eleve eleve;
		public void actionPerformed(ActionEvent arg){
			for(int i = 0; i < fichier.getListClasse().size(); i++){
				classe = fichier.getListClasse().get(i);
				for(int j = 0; j < classe.getListeEleves().size(); j++){
					eleve = (Eleve)classe.getListeEleves().get(j);
					if(eleve.getResteApayez() > 0){
						System.out.println(eleve);
					}
				}
			}
		}
	}

}
