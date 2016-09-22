/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe03.Model;

/**
 *
 * @author nobody
 */
public class Application
{
  final double CENTIMETER_TO_INCH = 2.54;
  
  public Application()
  {

  }
      
  public double convertInchToCentimeter(double inch)
  {
    double cm = inch*CENTIMETER_TO_INCH;
    
    return cm;
  }

  public double convertCentimeterToInch(double cm)
  {
    double inch = cm/CENTIMETER_TO_INCH;
    
    return inch;
  }
}
