/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik_ohne_mvc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import logger.OhmLogger;

/**
 * @author christian
 */
public class DrawingPane extends JPanel implements Runnable
{
    private final Line2D.Double line;
    private volatile double angle = 90;
    
    private final long sleepTime;
    private final int radius;
    private final int strokeThickness;
    private final double stepAngle;
    
    public DrawingPane(int initAngle, long sleepTime, int radius,
                       int strokeThickness, double stepAngle)
    {
        this.sleepTime = sleepTime;
        this.radius = radius;
        this.strokeThickness = strokeThickness;
        this.stepAngle = stepAngle;
        
        if( initAngle < -90 )
            initAngle += 360 + 90;
        else
            initAngle += 90;
        
        this.angle = initAngle;
        
        line = new Line2D.Double();
        
        this.setBackground(Color.WHITE);
        
        Thread th = new Thread(this);
        th.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth() - 1;
        int h = getHeight() - 1;
        
        int xCenter = w/2 + strokeThickness/2;
        int yCenter = h/2 - strokeThickness/2;
        
        double rad = ((360.0-angle)/180.0) * Math.PI;
        
        double xFar = xCenter + ((Double) (Math.cos(rad) * radius));
        double yFar = yCenter + ((Double) (Math.sin(rad) * radius));
        
        OhmLogger.info("xFar: " + xFar);
        OhmLogger.info("yFar: " + yFar);
        
        line.setLine(xCenter, yCenter, xFar, yFar);
        
        g2.setStroke(new BasicStroke(strokeThickness));
        g2.draw(line);
    }

    @Override
    public void run()
    {
        double dAngle = stepAngle;
        
        while( true )
        {
            try
            {
                Thread.sleep(sleepTime);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(DrawingPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.angle -= dAngle;
            
            if( angle == 0 )
                angle = 360;
            
            this.repaint();
        }
    }
}
