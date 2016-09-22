/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

/**
 * @author christian
 */
public class ChatApplication 
{
    private final Transmitter transmitter;
    private final Graphic graphic;
    
    public ChatApplication()
    {
        this.transmitter = new Transmitter();
        this.graphic = new Graphic();
    }

    public ArrayList getAllPoints()
    {
        return graphic.getAllPoints();
    }
    
    public void setPoints(ArrayList< ArrayList<Point> > points)
    {
        graphic.setPoints(points);
    }
    
    public void savePoints(File f)
    {
        graphic.savePoints(f);
    }
    
    public void addPoint(Point p)
    {
        graphic.addPoint(p);
    }
    
    public void endLine(Point p)
    {
        graphic.endLine(p);
    }
    
    public void beginLine(Point p)
    {
        graphic.beginLine(p);
    }
    
    public void clearPoints()
    {
        graphic.clearPoints();
    }
    
    public void addObserver(Observer o)
    {
        transmitter.addObserver(o);
    }
    
    public void connect(boolean serverMode)
    {
        transmitter.connect(serverMode);
    }
    
    public void disconnect()
    {
        transmitter.disconnect();
    }
    
    public String getReceivedMessage()
    {
        return transmitter.getReceivedMessage();
    }
        
    public ArrayList<ArrayList<Point>> getReceivedPoints()
    {
        return transmitter.getReceivedPoints();
    }
    
    public boolean isConnected()
    {
        return transmitter.isConnected();
    }
    
    public void sendMessage(String msg)
    {
        transmitter.sendObject(msg);
    }
    
    public void sendObjects()
    {
        transmitter.sendObject(graphic.getAllPoints());
    }
}
