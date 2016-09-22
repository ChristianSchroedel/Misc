/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe04;

import aufgabe04.Controller.EditorAdapter;
import aufgabe04.Model.EditorModel;
import aufgabe04.View.EditorView;

/**
 * Builder Class
 * @author nobody
 */
public class Main
{
  public Main()
  {
    EditorView editorView = new EditorView();
    EditorModel editorModel = new EditorModel();
    EditorAdapter editorAdapter = new EditorAdapter(editorView, editorModel);
    
    editorView.setVisible(true);
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    new Main();
  }
}
