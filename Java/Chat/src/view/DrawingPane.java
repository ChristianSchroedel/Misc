/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logger.OhmLogger;
import model.ChatApplication;

/**
 * @author christian
 */
public class DrawingPane extends JPanel implements Printable
{
    private ChatApplication application;
    private final Dimension dimension;
    
    private final Rectangle2D.Float pixel;
    private final float thickness = 3;
    
    private Point prevPoint = null;

    public DrawingPane()
    {
        this.dimension = new Dimension(1, 1);
        this.pixel = new Rectangle2D.Float();
        
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
    }
    
    public void updateGraphic()
    {
        OhmLogger.info("do repaint");
        this.repaint();
    }
    
    public void setGraphicModel(ChatApplication graphicModel)
    {
        this.application = graphicModel;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if( application == null )
            return;
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        
        this.drawAllPoints(g2);
    }
    
    public void drawPoint(Point p)
    {
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        g2.setStroke(new BasicStroke(thickness));
        
        if( prevPoint == null )
        {
            pixel.setFrame(p, dimension);
            g2.draw(pixel);
        }
        else
        {
            Line2D connector = new Line2D.Double();
            connector.setLine(prevPoint, p);
            g2.draw(connector);
        }
        
        prevPoint = p;
        
        g2.dispose();
    }

    private void drawAllPoints(Graphics2D g2)
    {
        super.paintComponent(g2);
        g2.setStroke(new BasicStroke(thickness));
        
        ArrayList< ArrayList<Point> > points = application.getAllPoints();
        
        for( int i = 0; i < points.size(); i++ )
        {
            ArrayList<Point> line = points.get(i);
            
            for( int j = 0; j < line.size(); j++ )
            {
                if( j < line.size()-1 )
                {
                    Line2D connector = new Line2D.Double();
                    connector.setLine(line.get(j), line.get(j+1));
                    g2.draw(connector);
                }
                if( j == line.size()-1 || j == 0 )
                {
                    pixel.setFrame(line.get(j), dimension);
                    g2.draw(pixel);
                }
            }
        }
    }

    public void endLine()
    {
        prevPoint = null;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        
        if( pageIndex == 0 )
        {
            this.drawAllPoints(g2);

            return Printable.PAGE_EXISTS;
        }
        else
        {
            return Printable.NO_SUCH_PAGE;
        }
    }

    public void printDrawing()
    {
        HashPrintRequestAttributeSet printSet = new HashPrintRequestAttributeSet();
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);

        if( pj.printDialog(printSet) )
        {
            try
            {
                pj.print(printSet);
            }
            catch( PrinterException ex )
            {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
        }
    }
    
}
