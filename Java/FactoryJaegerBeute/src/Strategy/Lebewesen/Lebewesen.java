package Strategy.Lebewesen;

import javax.swing.ImageIcon;

import Strategy.Position;
import Strategy.eAktion;


public interface Lebewesen 
{

	
	public abstract void aktion(eAktion aktion);

	public abstract void bewege();

	public abstract void erzeugeLaut();

	public abstract ImageIcon getAussehen();
	
	public abstract Position getPosition();
	
	public abstract double getJagdRadius();

}