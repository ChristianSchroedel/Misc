/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.Observable;
import java.util.Observer;
import model.ChatApplication;
import view.ChatView;

/**
 * @author christian
 */
public class ReceiveController implements Observer
{
    private final ChatView chatWindow;
    private final ChatApplication chatApplication;
    
    public ReceiveController(ChatView view, ChatApplication model)
    {
        this.chatWindow = view;
        this.chatApplication = model;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Events event = (Events) arg;
        
        if( event == Events.MESSAGE_RECEIVED )
        {
            chatWindow.getTaChatOutputField().append("Der andere: " +
                            chatApplication.getReceivedMessage() + "\n");
        }
        else if( event == Events.POINTS_RECEIVED )
        {
            chatApplication.setPoints(chatApplication.getReceivedPoints());
            chatWindow.getDrawingPane().updateGraphic();
        }
    }

    public void registerEvents()
    {
        chatApplication.addObserver(this);
    }
}
