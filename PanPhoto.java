package com.bbs.vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanPhoto extends JPanel{
	private Image photo;
	
	public void setPhoto(Image photoEleve){
			this.photo = photoEleve;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(photo, 0, 0, 300,400,this);
	}
}
