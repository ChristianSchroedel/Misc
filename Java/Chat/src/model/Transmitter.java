/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.Events;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.OhmLogger;

/**
 * @author christian
 */
public class Transmitter extends Observable implements Runnable
{
    public static final boolean SERVER_MODE = true;
    public static final boolean NORMAL_MODE = false;
    
    private static final int PORT = 4444;
    private static final String IP_ADDRESS = "127.0.0.1";
    
    private boolean receiveMode;
    private boolean connected;
    
    private ServerSocket ss;
    private Socket s;
    
    private ObjectInputStream objectReader;
    private ObjectOutputStream objectWriter;
    
    private String receivedMessage;
    private ArrayList< ArrayList<Point> > receivedPoints;
    
    public Transmitter()
    {
        this.receiveMode = NORMAL_MODE;
        this.connected = false;
        
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run()
    {
        while( true )
        {
            if( !connected )
            {
                this.doWait();
                this.connectReceiver();
            }
            
            this.receive();
        }
    }
    
    private synchronized void doWait()
    {
        try
        {
            this.wait();
        } 
        catch (InterruptedException ex)
        {
            OhmLogger.log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void connectReceiver()
    {
        if( receiveMode == SERVER_MODE )
        {
            try
            {
                ss = new ServerSocket(PORT);

                OhmLogger.info("connected as server");
                setChanged();
                notifyObservers(Events.CONNECT_SERVER_SUCCESSFUL);
                
                this.s = ss.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                
                BufferedOutputStream bos = new BufferedOutputStream(os);
                this.objectWriter = new ObjectOutputStream(bos);
                objectWriter.flush();
                
                BufferedInputStream bis = new BufferedInputStream(is);
                this.objectReader = new ObjectInputStream(bis);

                connected = true;
                OhmLogger.info("sender connected");
            } 
            catch (BindException ex)
            {
                connected = false;
                
                setChanged();
                notifyObservers(Events.CONNECT_SERVER_FAILED);

                OhmLogger.log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex)
            {
                OhmLogger.log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try
            {
                this.s = new Socket(IP_ADDRESS, PORT);
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                
                BufferedOutputStream bos = new BufferedOutputStream(os);
                objectWriter = new ObjectOutputStream(bos);
                objectWriter.flush();
                
                BufferedInputStream bis = new BufferedInputStream(is);
                this.objectReader = new ObjectInputStream(bis);

                connected = true;
                OhmLogger.info("connected as client");
                
                setChanged();
                notifyObservers(Events.CONNECT_CLIENT_SUCCESSFUL);
            } 
            catch (IOException ex)
            {
                connected = false;
                
                setChanged();
                notifyObservers(Events.CONNECT_CLIENT_FAILED);
                OhmLogger.log(Level.SEVERE, null, ex);
            }
        }
    }
        
    private synchronized void receive()
    {
        if( objectReader != null )
        {
            try
            {
                OhmLogger.info("wait for message...");
                //receivedMessage = reader.readLine();
                //Object receivedData = objectReader.read();
                Object receivedData = objectReader.readObject();
                
                if( receivedData != null )
                {
                    if( receivedData instanceof String )
                    {
                        OhmLogger.info("received message: " + receivedMessage);
                        receivedMessage = (String) receivedData;
                        setChanged();
                        notifyObservers(Events.MESSAGE_RECEIVED);
                    }
                    else if( receivedData instanceof ArrayList )
                    {
                        OhmLogger.info("received points");
                        receivedPoints = (ArrayList<ArrayList<Point>>) receivedData;
                        setChanged();
                        notifyObservers(Events.POINTS_RECEIVED);
                    }
                }
                else
                {
                    this.connected = false;
                    s.close();
                }
            } 
            catch (IOException ex)
            {
                connected = false;
                OhmLogger.log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public synchronized void connect(boolean serverMode)
    {
        this.receiveMode = serverMode;
        this.notify();
    }
    
    public String getReceivedMessage()
    {
        return receivedMessage;
    }
    
    public ArrayList<ArrayList<Point>> getReceivedPoints()
    {
        return receivedPoints;
    }

    public void sendObject(Object object)
    {
        try
        {
            objectWriter.writeObject(object);
            objectWriter.flush();
        } 
        catch (IOException ex)
        {
            OhmLogger.log(Level.SEVERE, null, ex);
        } 
    }
    
    public boolean isConnected()
    {
        return connected;
    }

    public void disconnect()
    {
        try
        {
            connected = false;
            
            if( ss != null )
                ss.close();
            if( objectReader != null )
                objectReader.close();
            if( objectWriter != null )
                objectWriter.close();
            if( s != null )
                s.close();
            
            setChanged();
            notifyObservers(Events.DISCONNECTED);
        } 
        catch (IOException ex)
        {
            OhmLogger.log(Level.SEVERE, null, ex);
        }
    }
}
