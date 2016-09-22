/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe05b.Controller;

import aufgabe05b.Model.Application;
import aufgabe05b.View.BanditView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import logger.OhmLogger;

/**
 * Controller of the slot machine
 * @author christian
 */
public class Adapter implements ActionListener, Observer
{
    private final BanditView view;
    private final Application model;
    private boolean gameRunning;

    /**
     * Creates a new Adapter object
     * @param view Used view in MVC architecture
     * @param model Used model in MVC architecture
     */
    public Adapter(BanditView view, Application model) 
    {
        this.view = view;
        this.model = model;
        this.initSlotMachine();
        
        OhmLogger.info("Adapter is all set up");
    }

    /**
     * Register events to the adapter
     */
    public void registerEvents() 
    {
        view.getBtnStart().addActionListener(this);
        view.getBtnStop().addActionListener(this);
        model.setObservers(this);
        
        OhmLogger.info("registered events to adapter");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        Object src = arg0.getSource();
        
        if( src == view.getBtnStart() )
        {
            OhmLogger.info("start button pressed");
            view.getBtnStart().setEnabled(false);
            view.getBtnStop().setEnabled(true);
            
            if( !gameRunning )
            {
                gameRunning = true;
                model.setCredits(50);
                OhmLogger.info("game started");
            }
            
            if( gameRunning )
            {
                model.setCredits(model.getCredits()-1);
                model.startSlotMachine();
                view.getLbCredits().setText(model.getCredits() + "");
            }
        }
        else if( src == view.getBtnStop() )
        {            
            OhmLogger.info("stop button pressed");
            view.getBtnStart().setEnabled(true);
            view.getBtnStop().setEnabled(false);
            
            if( gameRunning )
            {
                model.stopSlotMachine();
                
                int[] slotNumbers = model.getSlotNumbers();
                
                if( model.isSlotMachineRunning() == false )
                {   
                    if( (Integer.compare(slotNumbers[0], slotNumbers[1]) == 0) && 
                        (Integer.compare(slotNumbers[1], slotNumbers[2]) == 0) )
                    {
                        OhmLogger.info("three equal symbols");
                        
                        view.showWinDialog();
                        model.setCredits(model.getCredits() + 50);
                        view.getLbCredits().setText(model.getCredits() + "");
                    }
                    else
                    {
                        if( model.getCredits() <= 0 )
                        {
                            view.showLoseDialog();
                            gameRunning = false;
                            
                            OhmLogger.info("game is over");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1)
    {
        int[] slotNumbers = model.getSlotNumbers();
        
        view.getLbSlot1().setIcon(view.selectIcon(slotNumbers[0]));
        view.getLbSlot2().setIcon(view.selectIcon(slotNumbers[1]));
        view.getLbSlot3().setIcon(view.selectIcon(slotNumbers[2]));
        
        OhmLogger.info("updated icons");
    }
    
    /**
     * Initialize the slot machine
     */
    private void initSlotMachine()
    {
        view.getLbSlot1().setIcon(view.selectIcon((int) ((Math.random() * 9) + 1)));
        view.getLbSlot2().setIcon(view.selectIcon((int) ((Math.random() * 9) + 1)));
        view.getLbSlot3().setIcon(view.selectIcon((int) ((Math.random() * 9) + 1)));
        
        view.getLbCredits().setText(model.getCredits()+ "");
        
        OhmLogger.info("initialized slotMachine");
    }
}
