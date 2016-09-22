/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe04.Controller;

import aufgabe04.Model.EditorModel;
import aufgabe04.View.EditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author christian
 */
public class SaveFile implements Adapter, ActionListener
{
    private EditorAdapter editorAdapter;

    public SaveFile(EditorAdapter editorAdapter) 
    {
        this.editorAdapter = editorAdapter;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        EditorView ev = editorAdapter.getEditorView();
        EditorModel em = editorAdapter.getEditorModel();
        
        int res = ev.getFcEditor().showSaveDialog(ev);
        
        if( res == JFileChooser.APPROVE_OPTION )
        {
            File file = ev.getFcEditor().getSelectedFile();
            String path = file.getAbsolutePath();
            
            try 
            {
                em.saveFile(file, ev.getTaEditor().getText());
                ev.getLbEditor().setText("File '" + path + "' saved");
            } 
            catch (Exception exp)
            {
                ev.showError(exp.toString());
            }
        }
    }

    @Override
    public void registerComponents() 
    {
        EditorView ev = editorAdapter.getEditorView();
        
        ev.getBtnSave().addActionListener(this);
        ev.getSaveMenuItem().addActionListener(this);
    }
    
}
