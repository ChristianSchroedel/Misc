/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe07.Controller;

import aufgabe07.Model.GraphicModel;
import aufgabe07.View.DrawingPane;
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

/**
 * @author christian
 */
public class GraphicController implements MouseListener, MouseMotionListener, WindowListener
{
    private final DrawingPane graphicView;
    private final GraphicModel graphicModel;
    
    private int mouseButton;
    
    public GraphicController(DrawingPane graphicView, GraphicModel graphicModel)
    {
        this.graphicView = graphicView;
        this.graphicModel = graphicModel;
    }
    
    public void setListener()
    {
        graphicView.addMouseListener(this);
        graphicView.addMouseMotionListener(this);
        System.out.println(graphicView.getFrame().getName());
        graphicView.getFrame().addWindowListener(this);
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
        
            graphicModel.beginLine(p);
            graphicView.drawPoint(p);
        }
        else if( mouseButton == MouseEvent.BUTTON3 )
        {
            graphicModel.clearPoints();
            graphicView.repaint();
        }
        else if( mouseButton == MouseEvent.BUTTON2 )
            graphicView.printDrawing();
    }

    @Override
    public void mouseReleased(MouseEvent evt)
    {
        if( mouseButton == MouseEvent.BUTTON1)
        {
            Point p = evt.getPoint();

            graphicModel.endLine(p);
            graphicView.endLine();
            graphicView.drawPoint(p);
            graphicView.endLine();
        }
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
        
            graphicModel.addPoint(p);
            graphicView.drawPoint(p);
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
            
            graphicModel.savePoints(f);
            
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
