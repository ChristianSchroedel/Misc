package Strategy.Lebewesen;

import Main.AssetsManager;
import Strategy.Position;
import Strategy.eAktion;
import Strategy.BewegungsVerhalten.Rennen;
import Strategy.BewegungsVerhalten.Stehen;



public class Tiger extends LebewesenImp 
{

	public Tiger(  )
	{
		super( AssetsManager.IMG_TIGER, AssetsManager.SOUND_TIGER );
		_bewegungsVerhalten = new Rennen();
		_jagdRadius = 50; 
		_position = new Position();
	}
	public Tiger( final Position p )
	{
		super( AssetsManager.IMG_TIGER, AssetsManager.SOUND_TIGER );
		_bewegungsVerhalten = new Rennen();
		_jagdRadius = 50; 
		_position = p;
	}

	public void aktion(eAktion aktion)
	{
		if( aktion == eAktion.erlegen )
		{
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