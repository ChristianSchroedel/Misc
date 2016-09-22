/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik_ohne_mvc;

import java.awt.Color;
import java.awt.Container;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.OverlayLayout;
import logger.OhmLogger;

/**
 *
 * @author christian
 */
public class Main 
{
    public Main()
    {
        OhmLogger.setup();
        
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(400, 400);
        frm.setVisible(true);
        
        Container container = frm.getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(new OverlayLayout(container));
       
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        
        // Seconds
        DrawingPane seconds = new DrawingPane(0, 1000, 100, 2, 6);
        seconds.setOpaque(false);
        container.add(seconds);
        // Minutes
        DrawingPane minutes = new DrawingPane(-min*6, 1000*60, 80, 5, 6);
        minutes.setOpaque(false);
        container.add(minutes);
        // Hours
        DrawingPane hours = new DrawingPane(-hour*30, 1000*60*60, 55, 5, 30);
        hours.setOpaque(false);
        container.add(hours);
        // Clock
        Clock clock = new Clock(new ImageIcon(this.getClass().getResource("/assets/clock.png")));
        clock.setOpaque(false);
        container.add(clock);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Main();
    }

}
