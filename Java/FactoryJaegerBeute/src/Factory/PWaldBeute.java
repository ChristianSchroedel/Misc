package Factory;

import javax.swing.ImageIcon;

import Strategy.Position;
import Strategy.eAktion;
import Strategy.Lebewesen.Lebewesen;


public class PWaldBeute extends APBeute 
{
	public PWaldBeute(Lebewesen lebewesen)
	{
		super(lebewesen);
	}

	public void erzeugeLaut()
	{
		lebewesen.erzeugeLaut();
	}

	public void fliehe()
	{
		lebewesen.bewege();
	}

	public ImageIcon getAussehen()
	{
		return lebewesen.getAussehen();
	}
/*
	public void setInitPosition()
	{
		lebewesen.setInitPosition();
	}
*/
	public Position getPosition()
	{
		return lebewesen.getPosition();
	}
	
	public double getRadius()
	{
		return lebewesen.getJagdRadius();
	}

	public void sterbe()
	{
		lebewesen.aktion(eAktion.sterben);
	}

}