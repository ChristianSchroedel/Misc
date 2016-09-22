/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe05b;

import aufgabe05b.Controller.Adapter;
import aufgabe05b.Model.Application;
import aufgabe05b.View.BanditView;
import logger.OhmLogger;

/**
 * Main class
 * @author christian
 */
public class Main 
{
    public Main()
    {   
        OhmLogger.setup();
        
        BanditView view = new BanditView();
        Application model = new Application();
        
        Adapter controller = new Adapter(view, model);
        controller.registerEvents();
        
        view.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Main();
    }
}
