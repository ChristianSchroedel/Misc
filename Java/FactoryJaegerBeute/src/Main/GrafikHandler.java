package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Factory.APBeute;
import Factory.APJaeger;

@SuppressWarnings("serial")
public class GrafikHandler extends JPanel
{	
	private Model model;

	private int iconWidth;
	private int iconHeight;
	
	public static Dimension grafikDimension;
	
	public GrafikHandler(Model model)
	{
		this.model = model;
		this.setBackground(Color.WHITE);
		
		this.iconWidth = model.getHunter().getAussehen().getIconWidth();
		this.iconHeight = model.getHunter().getAussehen().getIconHeight();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		grafikDimension = new Dimension(this.getWidth(), this.getHeight());

		APJaeger hunter = model.getHunter();
		APBeute victim = model.getVictim();
		
		int xPosHunter = (int) hunter.getPosition().x - iconWidth/2;
		int yPosHunter = (int) hunter.getPosition().y - iconHeight/2;
		
		int xPosVictim = (int) victim.getPosition().x - iconWidth/2;
		int yPosVictim = (int) victim.getPosition().y - iconHeight/2;
		
		ImageIcon imgHunter = hunter.getAussehen();
		ImageIcon imgVictim = victim.getAussehen();

		g2.drawImage(imgHunter.getImage(), xPosHunter, yPosHunter, this);
		g2.drawImage(imgVictim.getImage(), xPosVictim, yPosVictim, this);
	}
	
	public void updateGraphic()
	{
		this.repaint();
	}
	
	/*
	private void initObjects()
	{
		frameWidth = this.getWidth();
		frameHeight = this.getHeight();
		
		xPosHunter = Math.random() * frameWidth;
		yPosHunter = Math.random() * frameHeight;
		
		if( xPosHunter >= frameWidth-widthHunter )
			xPosHunter -= widthHunter;
		if( yPosHunter >= frameHeight-heightHunter )
			yPosHunter -= heightHunter;
		
		System.out.println("xPosHunter: " + xPosHunter);
		System.out.println("yPosHunter: " + yPosHunter);
		
		xPosVictim = Math.random() * frameWidth;
		yPosVictim = Math.random() * frameHeight;
		
		if( xPosVictim >= frameWidth-widthVictim )
			xPosVictim -= widthVictim;
		if( yPosVictim >= frameHeight-heightVictim )
			yPosVictim -= heightVictim;

		System.out.println("xPosVictim: " + xPosVictim);
		System.out.println("yPosVictim: " + yPosVictim);
		
		initialized = true;
	}
	*/
}
