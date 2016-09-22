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
public class Application1 extends Observable implements Runnable, Application
{
    private final Thread th;
    
    private volatile boolean notifyObserver;
    private int augenZahl;
    
    private static final Logger log = Logger.getLogger(Application1.class.getName());

    public Application1() 
    {
        th = new Thread(this);
  
        
        log.log(Level.INFO, "application1 thread started");
        
        notifyObserver = false;
        th.start();
    }
    
    public void addObserverToModel(Observer o)
    {
        this.addObserver(o);
    }
    
    public void start()
    {      
        notifyObserver = true;
    }
        
    public void stop()
    {   
        notifyObserver = false;
    }

    @Override
    public void run() 
    {
        while( true )
        {   
            log.log(Level.INFO, "application1 thread running...");
            
            augenZahl = (int) ((Math.random() * 6) + 1);
            
            try 
            {
                Thread.sleep(200);
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
            
            if( notifyObserver == true )
            {
                this.setChanged();
                this.notifyObservers();
            }
        }
    }
    
    public int getAugenZahl()
    {
        return augenZahl;
    }
}
