package Strategy.BewegungsVerhalten;

import java.awt.Dimension;

import Strategy.Position;


public abstract class BewegungsVerhalten 
{
	private class Pair
	{
		Pair(float x, float y){this.x = x; this.y = y; }
		public float x;
		public float y;
	};
	
	
	private int vektorPos;
	private  Pair[] _positionsVektor = null;
	protected int bewegungsGeschwindigkeit; 
		
	public Position bewege(final Position p)
	{
		vektorPos++;
		
		if(_positionsVektor == null )
		{
			_positionsVektor = getPath(p);
		}
		
		if( vektorPos == _positionsVektor.length)
		{			
			_positionsVektor = getPath(p);
			vektorPos=1;
		}
		
		return new Position(_positionsVektor[vektorPos].x, _positionsVektor[vektorPos].y);
	}
	
	private Pair[] getPath( final Position p )
	{
		int steps = bewegungsGeschwindigkeit;
		
		float y2 =  (float)( Math.random() * ( (float) getScreenDimension().getHeight())+1);
		float x2 =  (float)( Math.random() * ( (float) getScreenDimension().getWidth())+1);
		float m, t; 
		
		m = ( y2 - p.y ) / ( x2 - p.x );
		t = p.y - ( m * p.x );
		
		float vektorLaenge = Math.abs(x2 - p.x);
		
		float abstand = vektorLaenge / steps; 
			
		Pair[] positionVector = new Pair[steps];
		

		for( int i = 0; i < positionVector.length; i++)
		{
			float newX;
			
			if( x2 < p.x )
			{
				newX = p.x - i*abstand;
			}
			else
			{
				newX = p.x + i*abstand;
			}
			
			
			float newY = m * newX + t;
			 
			 positionVector[i] = new Pair( newX, newY );
		}
		
		return positionVector;
	}
	

	
	
	static Dimension getScreenDimension()
	{
		//return GrafikHandler.grafikDimension;
		return new Dimension(500, 500);
	}
	
}