/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe07;

import aufgabe07.Controller.GraphicController;
import aufgabe07.Model.GraphicModel;
import aufgabe07.View.DrawingPane;
import java.io.File;

/**
 *
 * @author christian
 */
public class Aufgabe07 
{
    public Aufgabe07()
    {
        GraphicModel model = new GraphicModel();
        model.loadPoints(new File("test.txt"));
        
        DrawingPane view = new DrawingPane(model);
        
        GraphicController controller = new GraphicController(view, model);
        controller.setListener();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Aufgabe07();
    }

}
