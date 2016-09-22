package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener
{
	private View view;
	private Model model;
	private GameHandler game = null;

	public Controller(View view, Model model)
	{
		this.view = view;
		this.model = model;
		
		this.game = new GameHandler(model, view);
	}
	
	public void setListener()
	{
		view.getBtnSteppe().addActionListener(this);
		view.getBtnWald().addActionListener(this);
		view.getBtnStartGame().addActionListener(this);
		view.getBtnStopGame().addActionListener(this);
		view.getBtnRestartGame().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();
		
		if( src == view.getBtnSteppe() )
		{
			view.setBackgroundImage(AssetsManager.getImage(AssetsManager.IMG_STEPPE).getImage());
			model.produceSteppe(model.getHunter().getPosition(), model.getVictim().getPosition());
		}
		else if( src == view.getBtnWald() )
		{
			view.setBackgroundImage(AssetsManager.getImage(AssetsManager.IMG_WOODS).getImage());
			model.produceWald(model.getHunter().getPosition(), model.getVictim().getPosition());
		}
		else if( src == view.getBtnStartGame() )
		{
			if( !game.isRunning() )
				game.startGame();
		}
		else if( src == view.getBtnStopGame() )
		{
			if( game.isRunning() )
				game.stopGame();
		}
		else if( src == view.getBtnRestartGame() )
		{
			view.setBackgroundImage(AssetsManager.getImage(AssetsManager.IMG_WOODS).getImage());
			model.produceWald();
		}
	}

}
