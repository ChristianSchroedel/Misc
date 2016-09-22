/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe05a;

import aufgabe05a.Controller.Adapter;
import aufgabe05a.Model.Application;
import aufgabe05a.Model.Application3;
import aufgabe05a.View.WuerfelView;

/**
 * Builder Class
 * @author nobody
 */
public class Main
{
    public Main()
    {
        WuerfelView view = new WuerfelView();
        Application model = new Application3();

        Adapter adapter = new Adapter(model, view);
        adapter.registerListener();

        view.setVisible(true);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) 
    {
        new Main();
    }
}
