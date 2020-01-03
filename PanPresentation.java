package com.bbs.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanPresentation extends JPanel{
	

	
	public PanPresentation(){
		
	}
	
	public void paintComponent(Graphics g){
		Font font = new Font("Courier", Font.BOLD, 150);
		g.setFont(font);
		g.setColor(Color.RED);
		try{
			Image img = ImageIO.read(new File("file/photos/364.jpg"));
			g.drawImage(img,0,0,this.getWidth(), this.getHeight(), this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
