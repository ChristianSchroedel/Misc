/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe04.Controller;

import aufgabe04.Model.EditorModel;
import aufgabe04.View.EditorView;

/**
 *
 * @author nobody
 */
public class EditorAdapter
{
  private EditorView editorView;
  private EditorModel editorModel;
  
  private Adapter saveFile;
  private Adapter openFile;
  private Adapter copyAndPaste;
  
  public EditorAdapter(EditorView editorView, EditorModel editorModel)
  {
    this.editorView = editorView;
    this.editorModel = editorModel;
    
    this.saveFile = new SaveFile(this);
    this.openFile = new OpenFile(this);
    this.copyAndPaste = new CopyAndPaste(this);
    
    this.registerComponents();
  }
  
  public EditorView getEditorView()
  {
      return editorView;
  }
  
  public EditorModel getEditorModel()
  {
      return editorModel;
  }

  private void registerComponents()
  {
      saveFile.registerComponents();
      openFile.registerComponents();
      copyAndPaste.registerComponents();
  }
}
