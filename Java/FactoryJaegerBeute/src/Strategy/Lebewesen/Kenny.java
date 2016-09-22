package Strategy.Lebewesen;


import Main.AssetsManager;
import Strategy.Position;
import Strategy.eAktion;
import Strategy.BewegungsVerhalten.Rennen;
import Strategy.BewegungsVerhalten.Stehen;



public class Kenny extends LebewesenImp 
{

	public Kenny( )
	{	
		super( AssetsManager.IMG_KENNY, AssetsManager.SOUND_KENNY );
		_bewegungsVerhalten = new Rennen();
		_jagdRadius = 20; 
		_position = new Position();
	}
	
	public Kenny(final Position p)
	{
		super( AssetsManager.IMG_KENNY, AssetsManager.SOUND_KENNY );
		_bewegungsVerhalten = new Rennen();
		_jagdRadius = 20; 
		_position = p;
	}


	public void aktion(eAktion aktion)
	{
		if( aktion == eAktion.erlegen )
		{
			this.ladeLaut( AssetsManager.SOUND_KENNY );
			erzeugeLaut();
			_bewegungsVerhalten = new Stehen();
		}
		else if( aktion == eAktion.sterben )
		{
			this.ladeLaut( AssetsManager.SOUND_KENNY );
			erzeugeLaut();
			ladeBild( AssetsManager.IMG_STERBEN );
			_bewegungsVerhalten = new Stehen();
		}
		else
		{
			// toDo
		}
	}

}
