/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe03;

import aufgabe03.Controller.ListenerCentimeter;
import aufgabe03.Controller.ListenerInch;
import aufgabe03.Model.Application;
import aufgabe03.View.MyWindow;

/**
 * Builder Class
 * @author nobody
 */
public class Main
{
  public Main()
  {
    MyWindow window = new MyWindow("Umrechner mit MVC"); 
    Application app = new Application();
    
    ListenerCentimeter listenerCm = new ListenerCentimeter();
    ListenerInch listenerInch = new ListenerInch();
    
    listenerCm.setApplication(app);
    listenerCm.setWindow(window);
    
    listenerInch.setApplication(app);
    listenerInch.setWindow(window);
    
    window.setVisible(true);
    window.pack();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    new Main();
  }
}
