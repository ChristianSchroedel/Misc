/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik_ohne_mvc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author christian
 */
public class Clock extends JPanel
{
    private ImageIcon icon;
    
    public Clock(ImageIcon icon)
    {
        this.icon = icon;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
     
        int w = getWidth() - 1;
        int h = getHeight() - 1;
        
        int xCenter = w/2 - icon.getIconWidth()/2;
        int yCenter = h/2 - icon.getIconHeight()/2;
        
        g.drawImage(icon.getImage(), xCenter, yCenter, this);
    }
}
