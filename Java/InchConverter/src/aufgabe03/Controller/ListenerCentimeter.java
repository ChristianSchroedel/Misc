/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe03.Controller;

import aufgabe03.Model.Application;
import aufgabe03.View.MyWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 *
 * @author nobody
 */
public class ListenerCentimeter implements ActionListener
{
  private MyWindow window;
  private Application app;
  
  public ListenerCentimeter()
  {
      
  }

  @Override
  public void actionPerformed(ActionEvent arg0)
  {
    String cmString = window.getCentimeterTextField().getText();
    cmString = cmString.replace(',', '.');
    window.getCentimeterTextField().setText(cmString);

    try
    {
      double cm = Double.valueOf(cmString);
      double inch = app.convertCentimeterToInch(cm);
      window.getInchTextField().setText(String.format("%.2f", inch));
    }
    catch( NumberFormatException exc )
    {
      window.showWrongInputDialog();
    }
  }
  
  public void setWindow(MyWindow window)
  {
    this.window = window;
    
    window.getCentimeterTextField().addActionListener(this);
    window.getRightButton().addActionListener(this);
  }
  
  public void setApplication(Application app)
  {
    this.app = app;
  }
}
