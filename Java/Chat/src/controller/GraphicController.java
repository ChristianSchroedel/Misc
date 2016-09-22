/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChatApplication;
import view.ChatView;

/**
 * @author christian
 */
public class GraphicController implements MouseListener, MouseMotionListener, WindowListener
{
    private final ChatView view;
    private final ChatApplication model;
    
    private int mouseButton;
    
    public GraphicController(ChatView graphicView, ChatApplication graphicModel)
    {
        this.view = graphicView;
        this.model = graphicModel;
    }
    
    public void registerEvents()
    {
        view.getDrawingPane().addMouseListener(this);
        view.getDrawingPane().addMouseMotionListener(this);
        view.addWindowListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent evt)
    {
        mouseButton = evt.getButton();
        
        if( mouseButton == MouseEvent.BUTTON1 )
        {
            Point p = evt.getPoint();
        
            model.beginLine(p);
            view.drawPoint(p);
            view.updateDrawingPane();
        }
        else if( mouseButton == MouseEvent.BUTTON3 )
        {
            model.clearPoints();
            view.updateDrawingPane();
        }
        else if( mouseButton == MouseEvent.BUTTON2 )
        {
            view.printDrawing();
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt)
    {
        if( mouseButton == MouseEvent.BUTTON1)
        {
            Point p = evt.getPoint();

            model.endLine(p);
            view.endLine();
            view.drawPoint(p);
            view.endLine();
        }
        
        model.sendObjects();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void mouseDragged(MouseEvent evt)
    {
        if( mouseButton == MouseEvent.BUTTON1 )
        {
            Point p = evt.getPoint();
        
            model.addPoint(p);
            view.drawPoint(p);
        }
    }

    @Override
    public void mouseMoved(MouseEvent evt)
    {
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        try 
        {
            File f = new File("test.txt");

            if( f.exists() )
                f.delete();
            if( !f.exists() )
                f.createNewFile();
            
            model.savePoints(f);
            
            System.exit(0);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void windowClosed(WindowEvent e)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
    }
}
