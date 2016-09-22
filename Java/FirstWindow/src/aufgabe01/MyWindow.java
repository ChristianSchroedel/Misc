/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe01;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author nobody
 */
public class MyWindow extends JFrame implements WindowListener
{
  public MyWindow(String string)
  {
    this.setSize(500, 800);
    this.setTitle(string);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.addWindowListener(this);
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
}
