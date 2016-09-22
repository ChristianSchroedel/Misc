package Strategy.Lebewesen;

import Main.AssetsManager;
import Strategy.Position;
import Strategy.eAktion;
import Strategy.BewegungsVerhalten.Rennen;
import Strategy.BewegungsVerhalten.Stehen;



public class Mensch extends LebewesenImp 
{

	public Mensch( )
	{
		super( AssetsManager.IMG_HUMAN, AssetsManager.SOUND_MENSCH);
		_bewegungsVerhalten = new Rennen();
		_jagdRadius = 100; 
		_position = new Position();
	}
	
	public Mensch( final Position p )
	{
		super( AssetsManager.IMG_HUMAN, AssetsManager.SOUND_MENSCH);
		_bewegungsVerhalten = new Rennen();
		_jagdRadius = 100; 
		_position = p;
	}


	public void aktion(eAktion aktion)
	{
		if( aktion == eAktion.erlegen )
		{
			this.ladeLaut( AssetsManager.SOUND_MENSCH );
			erzeugeLaut();
			_bewegungsVerhalten = new Stehen();
		}
		else if( aktion == eAktion.sterben )
		{
			ladeBild( AssetsManager.IMG_STERBEN );
			_bewegungsVerhalten = new Stehen();
		}
		else
		{
			// toDo
		}
	}

}