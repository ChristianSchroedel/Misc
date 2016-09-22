package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BackGround extends JPanel
{
	private Image image;
	
	public BackGround(Image image)
	{
		this.image = image;
	}
	
	public void setImage(Image image)
	{
		this.image = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(image, 0, 0, this);
	}
}
