package Strategy;

import java.awt.Dimension;



public class Position 
{

	public float x;
	public float y;
	
	public Position()
	{	
		this.y =  (float) (Math.random()*((float)getScreenDimension().getHeight() )+1);
		this.x =  (float) (Math.random()*((float)getScreenDimension().getWidth()  )+1);

	}
	
	public Position( final float x, final float y)
	{
		this.x = x;
		this.y = y; 
	}
	
	static Dimension getScreenDimension()
	{
		return new Dimension(500, 500);
	}


}