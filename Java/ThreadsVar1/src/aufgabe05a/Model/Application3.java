/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe05a.Model;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christian
 */
public class Application3 extends Observable implements Runnable, Application
{
    private final Thread th;
    
    private volatile boolean running;
    private int augenZahl;
    
    private static final Logger log = Logger.getLogger(Application3.class.getName());

    public Application3() 
    {
        running = false;
      
        th = new Thread(this);
        th.start();
        
        log.log(Level.INFO, "application3 thread started");
    }
    
    public void addObserverToModel(Observer o)
    {
        this.addObserver(o);
    }
    
    public synchronized void start()
    {   
        running = true;
        notify();
    }
        
    public synchronized void stop()
    {   
        running = false;
    }

    @Override
    public void run() 
    {
        while( true )
        {
            if( running == false )
                doWait(); 
            
            log.log(Level.INFO, "application3 thread running...");
            
            getRandomValue();
            
            try 
            {
                Thread.sleep(200);
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
            
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    public int getAugenZahl()
    {
        return augenZahl;
    }

    private synchronized void doWait()
    {
        try 
        {
            wait(); 
        }
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
    }

    private synchronized void getRandomValue()
    {
        augenZahl = (int) ((Math.random() * 6) + 1);
    }
}
