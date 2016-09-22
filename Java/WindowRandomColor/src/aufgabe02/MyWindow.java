/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe02;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author nobody
 */
public class MyWindow extends JFrame implements WindowListener, MouseListener
{
  public MyWindow(String string)
  {
    this.setSize(500, 800);
    this.setTitle(string);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.addWindowListener(this);
    this.addMouseListener(this);
  }

  @Override
  public void windowOpened(WindowEvent e)
  {
  }

  @Override
  public void windowClosing(WindowEvent e)
  {
    int ret = JOptionPane.showConfirmDialog(this,
                                            "Fenster wirklich schliessen?",
                                            "Fenster wirklich schliessen?",
                                            JOptionPane.YES_NO_OPTION);

    if( ret == JOptionPane.YES_OPTION )
    {
      System.exit(0);
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

  @Override
  public void mouseClicked(MouseEvent e)
  {
    int red = (int)(Math.random() * 255);
    int green = (int)(Math.random() * 255);
    int blue = (int)(Math.random() * 255);
    
    Color col = new Color(red, green, blue);
    
    Container container = this.getContentPane();
    container.setBackground(col);
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
  }
}
