/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ChatApplication;
import view.ChatView;

/**
 * @author christian
 */
public class SendController implements ActionListener
{
    private final ChatView view;
    private final ChatApplication model;
    
    public SendController(ChatView view, ChatApplication model)
    {
        this.view = view;
        this.model = model;
    }

    public void registerEvents()
    {
        view.getBtnSendMessage().addActionListener(this);
        view.getTfChatInputField().getRootPane().setDefaultButton(view.getBtnSendMessage());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {      
        String msg = view.getTfChatInputField().getText();
        
        if( model.isConnected() )
        {
            model.sendMessage(msg);
            //model.sendObjects();
            view.getTaChatOutputField().append("Ich: " + msg + "\n");
            view.getTfChatInputField().setText("");
        }
        else
        {
            view.showErrorDialog("Netzwerkfehler",
                                 "Nachricht konnte nicht gesendet werden. " +
                                 "Keine Verbindung zu Empfänger!");
            view.setViewStatusToDisconnected();
        }
    }
}
