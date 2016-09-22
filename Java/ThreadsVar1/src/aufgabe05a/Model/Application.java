/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe05a.Model;

import java.util.Observer;

/**
 *
 * @author christian
 */
public interface Application 
{
    public void addObserverToModel(Observer o);
    public void start();
    public void stop();
    public int getAugenZahl();
}
