/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe07.View;

import aufgabe07.Model.GraphicModel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author christian
 */
public class DrawingPane extends JPanel implements Printable
{
    private final GraphicModel graphicModel;
    private final Dimension dimension;
    
    private final Rectangle2D.Float pixel;
    private final float thickness = 3;
    
    private Point prevPoint = null;
    private JFrame frm;

    public DrawingPane(GraphicModel graphicModel)
    {
        this.graphicModel = graphicModel;
        this.createWindow("Zeichenprogramm");
        
        this.dimension = new Dimension(1, 1);
        this.pixel = new Rectangle2D.Float();
    }
    
    public JFrame getFrame()
    {
        return frm;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
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
        
        ArrayList< ArrayList<Point> > points = graphicModel.getAllPoints();
        
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

    private void createWindow(String title)
    {
        frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frm.setSize(500, 500);
        frm.setTitle(title);
        frm.setVisible(true);
        
        Container mainContainer = frm.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(this, BorderLayout.CENTER);
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
