package Strategy.Lebewesen;

import Main.AssetsManager;
import Strategy.Position;
import Strategy.eAktion;
import Strategy.BewegungsVerhalten.Springen;
import Strategy.BewegungsVerhalten.Stehen;


public class Reh extends LebewesenImp 
{

	public Reh( )
	{
		super( AssetsManager.IMG_REH, AssetsManager.SOUND_REH );
		_bewegungsVerhalten = new Springen();
		_jagdRadius = 5; 
		_position = new Position();
	}
	
	public Reh( final Position p )
	{
		super( AssetsManager.IMG_REH, AssetsManager.SOUND_REH );
		_bewegungsVerhalten = new Springen();
		_jagdRadius = 5; 
		_position = p;
	}

	public void aktion(eAktion aktion)
	{
		if( aktion == eAktion.erlegen )
		{
			// ???
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