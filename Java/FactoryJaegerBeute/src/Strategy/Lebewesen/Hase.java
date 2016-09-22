package Strategy.Lebewesen;

import Main.AssetsManager;
import Strategy.Position;
import Strategy.eAktion;
import Strategy.BewegungsVerhalten.Hoppeln;

public class Hase extends LebewesenImp 
{

	public Hase( )
	{
		super( AssetsManager.IMG_KENNY, AssetsManager.SOUND_KENNY);
		_bewegungsVerhalten = new Hoppeln();
		_jagdRadius = 3;
		_position = new Position();
	}
	
	public Hase( final Position p )
	{
		super( AssetsManager.IMG_KENNY, AssetsManager.SOUND_KENNY);
		_bewegungsVerhalten = new Hoppeln();
		_jagdRadius = 3;
		_position = p;
	}
	public void aktion(eAktion aktion)
	{

	}

	public void bewege()
	{

	}

}