/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe09.countdownbean;

import aufgabe09.countdownbean.Controller.Controller;
import aufgabe09.countdownbean.Model.Model;
import aufgabe09.countdownbean.View.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 * @author christian
 */
public class Countdown extends JPanel implements PropertyChangeListener
{
    private View view;
    private Model model;
    private Controller controller;
    
    private int countdownStartValue;
    
    public Countdown()
    {
        view = new View();
        model = new Model();
        controller = new Controller(view, model);
        controller.registerListener();
        
        this.add(view);
        model.addPropertyChangeListener(this);
    }
    
    public int getCounter()
    {
        return model.getCounter();
    }
    
    public void setCounter(int value)
    {
        model.setCounter(value);
        this.setCountdownStartValue(value);
    }

    public void setCountdownStartValue(int value)
    {
        int old = this.countdownStartValue;
        this.countdownStartValue = value;
        
        this.firePropertyChange("countdownStartValue", old, value);
    }
    
    public int getCountdownStartValue()
    {
        return countdownStartValue;
    }
    
    public void start()
    {
        if( model.getCounter() == 0 )
            model.setCounter(countdownStartValue);
        
        model.startCountdown();
    }
    
    public void stop()
    {
        model.stopCountdown();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        this.firePropertyChange(
            evt.getPropertyName(), 
            evt.getOldValue(), 
            evt.getNewValue());
    }
}
