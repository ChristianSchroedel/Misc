/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe01;

/**
 * Builder Class
 * @author nobody
 */
public class Main
{
  public Main()
  {
    MyWindow window = new MyWindow("Aufgabe01");
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
