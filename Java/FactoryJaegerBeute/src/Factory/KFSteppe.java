package Factory;

import Strategy.Position;
import Strategy.Lebewesen.Reh;
import Strategy.Lebewesen.Tiger;


public class KFSteppe extends AFGelaende
{
	public KFSteppe()
	{

	}

	public APBeute erstelleBeute()
	{
		return new PSteppenBeute(new Reh());
	}

	public APJaeger erstelleJaeger()
	{
		return new PSteppenJaeger(new Tiger());
	}

	public APBeute erstelleBeute(Position victimPosition) 
	{
		return new PSteppenBeute(new Reh(victimPosition));
	}

	public APJaeger erstelleJaeger(Position hunterPosition) 
	{
		return new PSteppenJaeger(new Tiger(hunterPosition));
	}

}