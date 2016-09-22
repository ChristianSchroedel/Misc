/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe05a.Controller;

import aufgabe05a.Model.Application;
import aufgabe05a.View.WuerfelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

/**
 *
 * @author nobody
 */
public class Adapter implements ActionListener, Observer
{
    private Application model;
    private WuerfelView view;

    public Adapter(Application model, WuerfelView view)
    {
        this.model = model;
        this.view = view;
    }

    public void registerListener()
    {
        view.getBtnStartStop().addActionListener(this);
        model.addObserverToModel(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        if( view.getBtnStartStop().isSelected() )
        {
            model.start();
            view.getBtnStartStop().setText("Stop");
        }
        else
        {
            model.stop();
            view.getBtnStartStop().setText("Start");
        }
    }
    
    @Override
    public void update(Observable arg0, Object arg1) 
    {
        ImageIcon icon = null;
        
        switch(model.getAugenZahl())
        {
            case 1:
              //this.getClass().getResource("../assets/Alea_1.png");
                icon = new ImageIcon("src/assets/Alea_1.png");
                break;
            case 2:
                icon = new ImageIcon("src/assets/Alea_2.png");
                break;
            case 3:
                icon = new ImageIcon("src/assets/Alea_3.png");
                break;
            case 4:
                icon = new ImageIcon("src/assets/Alea_4.png");
                break;
            case 5:
                icon = new ImageIcon("src/assets/Alea_5.png");
                break;
            case 6:
                icon = new ImageIcon("src/assets/Alea_6.png");
                break;                        
        }

        view.getLbWuerfelAugen().setIcon(icon);
    }
}
