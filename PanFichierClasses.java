package com.bbs.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.bbs.model.Classe;
import com.bbs.model.ClasseSerializer;

public class PanFichierClasses extends JPanel{
	
	private JTable tableau;
	private ClasseSerializer cs = new ClasseSerializer();
	private ArrayList<Classe> lcs = cs.getListClasse();
	private int init = 0;
	private Object[][] datas;
	
	public PanFichierClasses(){
		
		this.setBackground(Color.WHITE);
		String titre[] = {
				"Nom classe", "nombre garcons", "Nombre filles", "Total"
		};
		
		datas = new Object[cs.getListClasse().size()][4];
		setDatas();
		
		Model model = new Model(datas,titre);
		tableau = new JTable(model);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tableau), BorderLayout.CENTER);
	}
	
	public void setDatas(){
		
		for(int i = 0 ; i < new ClasseSerializer().getListClasse().size(); i++){
			datas[i][0] = cs.getListClasse().get(i);
			datas[i][1] = cs.getListClasse().get(i).getNbreGarcon();
			datas[i][2] = cs.getListClasse().get(i).getNbreFille();
			datas[i][3] = cs.getListClasse().get(i).getNbreEleves();
		}
	}
	
	
	class Model extends AbstractTableModel{
		
		private Object[][] datas;
		private String [] colones;
		
		public Model(Object datas[][], String  colones[]){
			this.datas = datas;
			this.colones = colones;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return colones.length;
		}

		public int getRowCount() {
			// TODO Auto-generated method stub
			return datas.length;
		}

		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return datas[arg0][arg1];
		}
		
		public String getColumnName(int col){
			return colones[col];
		}
		
	}
}
