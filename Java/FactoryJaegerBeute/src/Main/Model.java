package Main;

import Factory.AFGelaende;
import Factory.APBeute;
import Factory.APJaeger;
import Factory.KFSteppe;
import Factory.KFWald;
import Strategy.Position;


public class Model
{
	private AFGelaende factory;
	
	private APJaeger hunter;
	private APBeute victim;
	
	public Model()
	{
		factory = new KFWald();
		this.makeProducts();
	}
	
	public void produceSteppe()
	{
		factory = new KFSteppe();
		this.makeProducts();
	}

	
	public void produceSteppe(Position hunterPosition, Position victimPosition)
	{
		factory = new KFSteppe();
		this.makeProducts(hunterPosition, victimPosition);
	}
	
	public void produceWald()
	{
		factory = new KFWald();
		this.makeProducts();
	}
	
	public void produceWald(Position hunterPosition, Position victimPosition)
	{
		factory = new KFWald();
		this.makeProducts(hunterPosition, victimPosition);
	}
	
	private void makeProducts()
	{
		hunter = factory.erstelleJaeger();
		System.out.println("New hunter produced");
		victim = factory.erstelleBeute();
		System.out.println("New victim produced");
	}
	
	private void makeProducts(Position hunterPosition, Position victimPosition)
	{
		hunter = factory.erstelleJaeger(hunterPosition);
		System.out.println("New hunter produced");
		victim = factory.erstelleBeute(victimPosition);
		System.out.println("New victim produced");
	}
	
	public APBeute getVictim()
	{
		return victim;
	}
	
	public APJaeger getHunter()
	{
		return hunter;
	}
}
