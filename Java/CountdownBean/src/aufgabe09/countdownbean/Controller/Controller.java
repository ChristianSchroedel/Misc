/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe09.countdownbean.Controller;

import aufgabe09.countdownbean.Model.Model;
import aufgabe09.countdownbean.View.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author christian
 */
public class Controller implements PropertyChangeListener
{
    private View view;
    private Model model;
    
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
    }

    public void registerListener()
    {
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        view.getCounterValue().setText(String.valueOf(model.getCounter()));
    }
}
