package Factory;

import Strategy.Position;



public abstract class AFGelaende
{
	public AFGelaende()
	{

	}
	
	public abstract APBeute erstelleBeute();

	public abstract APJaeger erstelleJaeger();

	public abstract APBeute erstelleBeute(Position victimPosition);

	public abstract APJaeger erstelleJaeger(Position hunterPosition);
}