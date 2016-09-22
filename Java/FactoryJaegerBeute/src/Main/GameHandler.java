package Main;

import Strategy.Position;


public class GameHandler implements Runnable
{
	private Model model;
	private View view;
	
	private boolean gameRunning;

	public GameHandler(Model model, View view)
	{
		this.model = model;
		this.view = view;
		
		gameRunning = false;
		
		Thread th = new Thread(this);
		th.start();
	}

	public boolean isRunning()
	{
		return gameRunning;
	}
	
	public synchronized void startGame()
	{
		System.out.println("start game");
		
		gameRunning = true;
		this.notify();
	}
	
	public void stopGame()
	{
		System.out.println("stop game");
		
		gameRunning = false;
	}
	
	@Override
	public void run()
	{
		while( true )
		{
			if( gameRunning == false )
			{
				System.out.println("game stopped");
				this.doWait();
			}
			
			this.moveObjects();
			this.checkCollision();

			view.updateGraphic();
			
			try
			{
				Thread.sleep(50);
			}
			catch( InterruptedException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private synchronized void doWait()
	{
		try
		{
			wait();
		}
		catch( InterruptedException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized void moveObjects()
	{
		model.getHunter().jage();
		model.getVictim().fliehe();
	}
	
	private synchronized void checkCollision()
	{
		Position hunterPosition = model.getHunter().getPosition();
		Position victimPosition = model.getVictim().getPosition();
		
		double xDiff = hunterPosition.x - victimPosition.x;
		double yDiff = hunterPosition.y - victimPosition.y;
		
		double distance = Math.sqrt(xDiff*xDiff + yDiff*yDiff);
		
		if( distance < model.getHunter().getRadius() )
		{
			model.getHunter().erlege();
			model.getVictim().sterbe();
			
			gameRunning = false;
		}/*
		else
		{
			model.getHunter().jage();
			model.getVictim().fliehe();
		}*/
	}
}
