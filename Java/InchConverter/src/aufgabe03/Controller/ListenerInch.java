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
public class ListenerInch implements ActionListener
{
  private MyWindow window;
  private Application app;
  
  public ListenerInch()
  {

  }

  @Override
  public void actionPerformed(ActionEvent arg0)
  {
    String inchString = window.getInchTextField().getText();
    inchString = inchString.replace(',', '.');
    window.getInchTextField().setText(inchString);
    
    try
    {  
      double inch = Double.valueOf(inchString);
      double cm = app.convertInchToCentimeter(inch);
      window.getCentimeterTextField().setText(String.format("%.2f", cm));
    }
    catch( NumberFormatException exc )
    {
      window.showWrongInputDialog();
    }   
  }
  
  public void setWindow(MyWindow window)
  {
    this.window = window;
    
    window.getInchTextField().addActionListener(this);
    window.getLeftButton().addActionListener(this);
  }
  
  public void setApplication(Application app)
  {
    this.app = app;
  }
}
