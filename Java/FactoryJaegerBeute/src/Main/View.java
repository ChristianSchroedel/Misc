package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.OverlayLayout;


@SuppressWarnings("serial")
public class View extends JFrame
{
	private JButton btnSteppe;
	private JButton btnWald;
	
	private JButton btnStartGame;
	private JButton btnStopGame;
	
	private JButton btnRestartGame;
	
	private GrafikHandler graphics;
	private BackGround backgroundImage;
	
	public View(Model model)
	{
		Container mainContainer = this.getContentPane();
		mainContainer.setBackground(Color.WHITE);
		mainContainer.setLayout(new BorderLayout());
		
		Container gameContainer = new Container();
		gameContainer.setLayout(new OverlayLayout(gameContainer));
		
		graphics = new GrafikHandler(model);
		graphics.setOpaque(false);
		gameContainer.add(graphics);
		
		backgroundImage = new BackGround(AssetsManager.getImage(AssetsManager.IMG_WOODS).getImage());
		backgroundImage.setOpaque(false);
		gameContainer.add(backgroundImage);

		mainContainer.add(gameContainer, BorderLayout.CENTER);
		
		Container buttons = new Container();
		buttons.setLayout(new FlowLayout());

		btnSteppe = new JButton();
		btnSteppe.setText("Steppe");
		
		btnWald = new JButton();
		btnWald.setText("Wald");
		
		btnStartGame = new JButton();
		btnStartGame.setText("Start");
		
		btnStopGame = new JButton();
		btnStopGame.setText("Stop");
		
		btnRestartGame = new JButton();
		btnRestartGame.setText("Restart");
		
		buttons.add(btnSteppe);
		buttons.add(btnWald);
		buttons.add(btnStartGame);
		buttons.add(btnStopGame);
		buttons.add(btnRestartGame);
		
		mainContainer.add(buttons, BorderLayout.NORTH);
		
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JButton getBtnSteppe()
	{
		return btnSteppe;
	}
	
	public JButton getBtnWald()
	{
		return btnWald;
	}
	
	public JButton getBtnStartGame()
	{
		return btnStartGame;
	}
	
	public JButton getBtnStopGame()
	{
		return btnStopGame;
	}
	
	public JButton getBtnRestartGame()
	{
		return btnRestartGame;
	}
	
	public void updateGraphic()
	{
		graphics.updateGraphic();
	}
	
	public void setBackgroundImage(Image image)
	{
		backgroundImage.setImage(image);
	}
}
