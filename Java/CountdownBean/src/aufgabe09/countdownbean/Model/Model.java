/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe09.countdownbean.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author christian
 */
public class Model implements Runnable
{
    private int value;
    
    private final Thread th;
    private boolean running = false;
    
    private PropertyChangeSupport pcs;
    
    public Model()
    {
        value = 0;
        pcs = new PropertyChangeSupport(this);
        
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run()
    {
        while( true )
        {
            if( !running )
                this.doWait();
            
            System.out.println("running");
            
            if( value > 0 )
            {
                this.setCounter(this.getCounter()-1);
                this.doSleep();
            }
            else if( value <= 0 )
                running = false;
        }
    }

    private void doSleep()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void doWait()
    {
        try
        {
            this.wait();
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void startCountdown()
    {
        System.out.println("start");
        
        running = true;
        this.notify();
    }
    
    public void stopCountdown()
    {
        System.out.println("stop");
        
        running = false;
    }
    
    public int getCounter()
    {
        return value;
    }

    public void setCounter(int value)
    {
        int old = this.value;
        this.value = value;
        
        pcs.firePropertyChange("counter", old, value);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(listener);
    }
}
