/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe03.View;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author christian
 */
public class MyWindow extends JFrame
{
    private JTextField tfCentimeter;
    private JTextField tfInch;
    private JButton btnRight;
    private JButton btnLeft;

    public MyWindow(String title)
    {
        
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContainer();
    }

    private void setContainer()
    {
        // Set main container
        Container mainContainer = getContentPane();
        mainContainer.setLayout(new FlowLayout());
        
        // Add centimeter container
        Container cmContainer = new Container();
        cmContainer.setLayout(new BoxLayout(cmContainer, BoxLayout.Y_AXIS));
        JLabel lbCentimeter = new JLabel("cm");
        tfCentimeter = new JTextField(6);
        cmContainer.add(lbCentimeter);
        cmContainer.add(tfCentimeter);
        mainContainer.add(cmContainer);
        
        // Add button container
        Container buttonContainer = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        btnRight = new JButton(">");
        btnLeft = new JButton("<");
        buttonContainer.add(btnRight);
        buttonContainer.add(btnLeft);
        mainContainer.add(buttonContainer);
        
        // Add inch container
        Container inchContainer = new Container();
        inchContainer.setLayout(new BoxLayout(inchContainer, BoxLayout.Y_AXIS));
        JLabel lbInch = new JLabel("inch");
        tfInch = new JTextField(10);
        inchContainer.add(lbInch);
        inchContainer.add(tfInch);
        mainContainer.add(inchContainer);
    }
  
    public JButton getLeftButton()
    {
      return btnLeft;
    }
    
    public JButton getRightButton()
    {
      return btnRight;
    }
    
    public JTextField getInchTextField()
    {
      return tfInch;
    }
    
    public JTextField getCentimeterTextField()
    {
      return tfCentimeter;
    }
    
    public void showWrongInputDialog()
    {
      JOptionPane.showMessageDialog(this,
              "Nur Zahlen im Format '0.00' eingeben!!",
              "Falsche Eingabe",
              JOptionPane.ERROR_MESSAGE);
      tfCentimeter.setText("");
      tfInch.setText("");
    }
}
