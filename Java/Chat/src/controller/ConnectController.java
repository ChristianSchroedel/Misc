/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import logger.OhmLogger;
import model.ChatApplication;
import model.Transmitter;
import view.ChatView;

/**
 * @author christian
 */
public class ConnectController implements ActionListener, Observer
{
    private final ChatView view;
    private final ChatApplication model;
    
    public ConnectController(ChatView view, ChatApplication model)
    {
        this.view = view;
        this.model = model;
    }

    public void registerEvents()
    {
        view.getMenuItemClient().addActionListener(this);
        view.getMenuItemServer().addActionListener(this);
        view.getMenuItemDisconnect().addActionListener(this);
        
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        
        if( source == view.getMenuItemClient() )
            model.connect(Transmitter.NORMAL_MODE);
        else if( source == view.getMenuItemServer() )
            model.connect(Transmitter.SERVER_MODE);
        else if( source == view.getMenuItemDisconnect() )
            model.disconnect();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Events event = (Events) arg;
        
        OhmLogger.info("event: " + event);
        
        if( event == Events.CONNECT_SERVER_SUCCESSFUL )
            view.setViewStatusToConnected("Status: Als Server verbunden");
        else if( event == Events.CONNECT_CLIENT_SUCCESSFUL )
            view.setViewStatusToConnected("Status: Mit Server verbunden");
        else if( event == Events.CONNECT_SERVER_FAILED )
        {
            view.showErrorDialog("Netzwerkfehler", 
                                 "Server konnte nicht erstellt werden.\n" +
                                 "Existiert bereits ein Server unter dieser Adresse?");
            view.setViewStatusToDisconnected();
        }
        else if( event == Events.CONNECT_CLIENT_FAILED)
        {
            view.showErrorDialog("Netzwerkfehler", 
                                 "Es konnte keine Verbindung zum Server hergestellt werden.");
            view.setViewStatusToDisconnected();
        }
        else if( event == Events.DISCONNECTED )
            view.setViewStatusToDisconnected();
    }
}
