/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe05b.Model;

import java.util.Observable;
import java.util.logging.Level;
import logger.OhmLogger;

/**
 * Thread creating random numbers (1-9)
 * @author christian
 */
public class SlotThread extends Observable implements Runnable
{
    private final Thread th;
    
    private volatile boolean running;
    private int slotNumber;

    /**
     * Creates a new SlotThread object
     */
    public SlotThread()
    {        
        running = false;
        
        th = new Thread(this);
        th.start();
        
        OhmLogger.info("created new SlotThread");
    }
    
    /**
     * Start SlotThread
     */
    public synchronized void start()
    {   
        running = true;
        notify();
    }
      
    /**
     * Stop SlotThread
     */
    public void stop()
    {   
        running = false;
    }

    @Override
    public void run() 
    {
        while( true )
        {
            if( running == false )
            {
                OhmLogger.info("stopped thread");
                this.doWait();
                OhmLogger.info("started thread");
            }
            
            getRandomValue();
            
            try 
            {
                Thread.sleep(200);
            } 
            catch (InterruptedException ex) 
            {
                OhmLogger.log(Level.SEVERE, null, ex);
            }
            
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * 
     * @return Current slot value (1-9)
     */
    public int getSlotZahl()
    {
        return slotNumber;
    }
    
    /**
     * Wait for notify
     */
    private synchronized void doWait()
    {
        try 
        {
            wait(); 
        }
        catch (InterruptedException ex) 
        {
            OhmLogger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create new random number
     */
    private synchronized void getRandomValue()
    {
        slotNumber = (int) ((Math.random() * 9) + 1);
    }
}
