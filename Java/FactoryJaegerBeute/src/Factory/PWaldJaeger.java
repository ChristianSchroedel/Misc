package Factory;

import javax.swing.ImageIcon;

import Strategy.Position;
import Strategy.eAktion;
import Strategy.Lebewesen.Lebewesen;


public class PWaldJaeger extends APJaeger 
{
	public PWaldJaeger(Lebewesen lebewesen)
	{
		super(lebewesen);
	}

	public void erlege()
	{
		lebewesen.aktion(eAktion.erlegen);
	}

	public void erzeugeLaut()
	{
		lebewesen.erzeugeLaut();
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

	public void jage()
	{
		lebewesen.bewege();
	}

}