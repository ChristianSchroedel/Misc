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
public class Application2 extends Observable implements Runnable, Application
{
    private Thread th;
    
    private volatile boolean running;
    private int augenZahl;

    private static final Logger log = Logger.getLogger(Application2.class.getName());
    
    public Application2() 
    {

    }
    
    public void addObserverToModel(Observer o)
    {
        this.addObserver(o);
    }
    
    public void start()
    {   
        running = true;
        
        th = new Thread(this);
        th.start();
        
        log.log(Level.INFO, "application2 thread started");
    }
        
    public void stop()
    {   
        running = false;
    }

    @Override
    public void run() 
    {
        while( running )
        {   
            log.log(Level.INFO, "application2 thread running...");
            
            augenZahl = (int) ((Math.random() * 6) + 1);
            
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
        
        log.log(Level.INFO, "application2 thread finished");
    }
    
    public int getAugenZahl()
    {
        return augenZahl;
    }
}
