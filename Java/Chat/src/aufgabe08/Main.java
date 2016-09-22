/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe08;

import controller.ConnectController;
import controller.GraphicController;
import controller.ReceiveController;
import controller.SendController;
import logger.OhmLogger;
import model.ChatApplication;
import view.ChatView;

/**
 *
 * @author christian
 */
public class Main 
{
    public Main()
    {
        OhmLogger.setup();
        
        OhmLogger.info("setup chat program");
        
        ChatView view = new ChatView();
        view.setVisible(true);
        
        ChatApplication model = new ChatApplication();
        view.setModelToDrawingPane(model);
        
        GraphicController graphicController = new GraphicController(view, model);
        ConnectController connector = new ConnectController(view, model);
        ReceiveController receiver = new ReceiveController(view, model);
        SendController sender = new SendController(view, model);
                
        graphicController.registerEvents();
        receiver.registerEvents();
        sender.registerEvents();
        connector.registerEvents();

        OhmLogger.info("setup done");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Main();
    }

}
