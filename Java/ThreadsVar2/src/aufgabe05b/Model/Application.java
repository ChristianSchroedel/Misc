/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe05b.Model;

import java.util.Observer;
import logger.OhmLogger;

/**
 * Model of slot machine
 * @author christian
 */
public class Application
{
    private final SlotThread slot1;
    private final SlotThread slot2;
    private final SlotThread slot3;
    
    private boolean slotMachineRunning = false;
    
    private int credits = 50;
    
    /**
     * Creates a new Application object
     */
    public Application() 
    {
        slot1 = new SlotThread();
        slot2 = new SlotThread();
        slot3 = new SlotThread();
        
        OhmLogger.info("Application is all set up");
    }
    
    /**
     * Set observer to Application object
     * @param o Observer to register
     */
    public void setObservers(Observer o)
    {
        slot1.addObserver(o);
        slot2.addObserver(o);
        slot3.addObserver(o);
        
        OhmLogger.info("set observers to threads");
    }

    /**
     * Get numbers of slot threads
     * @return All numbers of threads
     */
    public int[] getSlotNumbers() 
    {
        int[] slotNumbers  = new int[3];
        
        slotNumbers[0] = slot1.getSlotZahl();
        slotNumbers[1] = slot2.getSlotZahl();
        slotNumbers[2] = slot3.getSlotZahl();
        
        return slotNumbers;
    }

    /**
     * Start slot threads
     */
    public void startSlotMachine() 
    {
        if( !slotMachineRunning )
        {
            slotMachineRunning = true;
            
            slot1.start();
            slot2.start();
            slot3.start();
            
            OhmLogger.info("start all slot machine threads");
        }
    }

    /**
     * Stop slot threads
     */
    public void stopSlotMachine() 
    {
        if( slotMachineRunning )
        {
            slotMachineRunning = false;
        
            slot1.stop();
            slot2.stop();
            slot3.stop();
            
            OhmLogger.info("stop all slot machine threads");
        }
    }

    /**
     * Check if slot machine is running
     * @return State of slot machine
     */
    public boolean isSlotMachineRunning() 
    {
        return slotMachineRunning;
    }
    
    /**
     * Set credits of slot machine
     * @param credits Amount of credits
     */
    public void setCredits(int credits)
    {
        this.credits = credits;
        
        OhmLogger.info("updated credits to " + credits);
    }
    
    /**
     * Get credits of slot machine
     * @return Amount of credits
     */
    public synchronized int getCredits()
    {
        return credits;
    }

}
