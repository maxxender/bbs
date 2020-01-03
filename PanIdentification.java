package com.bbs.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanIdentification extends JPanel{
	
	private String description = "";
	private Font font = new Font("ITALIC", Font.BOLD, 20);
	
	
	public void setDescription(String newDescription){
		this.description = newDescription;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void paintComponent(Graphics g){
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString(description, 100, 50);
	}

}
