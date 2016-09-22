package Main;

public class Main
{
	public Main() 
	{
		AssetsManager.loadImages();
		AssetsManager.loadSounds();
		
		Model model = new Model();
		View view = new View(model);
		
		Controller controller = new Controller(view, model);
		controller.setListener();
	}
	
	public static void main( String args[])
	{
		new Main();
	}
}
