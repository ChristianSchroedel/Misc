package Factory;

import Strategy.Position;
import Strategy.Lebewesen.Kenny;
import Strategy.Lebewesen.Mensch;


public class KFWald extends AFGelaende 
{
	public KFWald()
	{

	}

	public APBeute erstelleBeute()
	{
		return new PWaldBeute(new Kenny());
	}

	public APJaeger erstelleJaeger()
	{
		return new PWaldJaeger(new Mensch());
	}

	public APBeute erstelleBeute(Position victimPosition) 
	{
		return new PWaldBeute(new Kenny(victimPosition));
	}

	public APJaeger erstelleJaeger(Position hunterPosition) 
	{
		return new PWaldJaeger(new Mensch(hunterPosition));
	}

}