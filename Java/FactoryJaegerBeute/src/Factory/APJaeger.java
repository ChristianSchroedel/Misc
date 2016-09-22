package Factory;

import javax.swing.ImageIcon;

import Strategy.Position;
import Strategy.Lebewesen.Lebewesen;


public abstract class APJaeger
{
	protected Lebewesen lebewesen;

	public APJaeger(Lebewesen lebewesen)
	{
		this.lebewesen = lebewesen;
	}

	public abstract void erlege();

	public abstract void erzeugeLaut();

	public abstract ImageIcon getAussehen();

	//public abstract void setInitPosition();
	
	public abstract Position getPosition();
	
	public abstract double getRadius();

	public abstract void jage();

}