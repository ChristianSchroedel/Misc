/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe09.countdownbean;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Customizer;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

/**
 * @author christian
 */
public class CountdownCustomizer extends JPanel implements Customizer, ActionListener
{
    private Countdown bean;
    private final JLabel lblText;
    private final JFormattedTextField tfedit;
    
    public CountdownCustomizer()
    {
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getIntegerInstance());
    
        this.setLayout(new BorderLayout());
        lblText = new JLabel("Startwert für Countdown: ");
        tfedit = new JFormattedTextField(formatter);

        this.add(lblText, BorderLayout.NORTH);
        this.add(tfedit, BorderLayout.SOUTH);
        
        tfedit.addActionListener(this);
    }

    @Override
    public void setObject(Object bean)
    {
        this.bean = (Countdown) bean;
        int counter = this.bean.getCounter();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int oldValue = bean.getCounter();
        int newValue = Integer.valueOf(tfedit.getText());

        bean.setCounter(newValue);
        this.firePropertyChange("counter", oldValue, newValue);
    }
}
