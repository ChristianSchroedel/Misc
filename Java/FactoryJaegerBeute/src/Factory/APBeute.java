package Factory;

import javax.swing.ImageIcon;

import Strategy.Position;
import Strategy.Lebewesen.Lebewesen;


public abstract class APBeute 
{
	protected Lebewesen lebewesen;

	public APBeute(Lebewesen lebewesen)
	{
		this.lebewesen = lebewesen;
	}

	public abstract void erzeugeLaut();

	public abstract void fliehe();

	public abstract ImageIcon getAussehen();

	//public abstract void setInitPosition();
	
	public abstract Position getPosition();
	
	public abstract double getRadius();

	public abstract void sterbe();

}