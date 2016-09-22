/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe09;

import javax.swing.JFrame;

/**
 *
 * @author christian
 */
public class Main 
{
    public Main()
    {
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Main();
    }

}
