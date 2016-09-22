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
public class OpenFile implements Adapter, ActionListener
{
    private EditorAdapter editorAdapter;

    public OpenFile(EditorAdapter editorAdapter) 
    {
        this.editorAdapter = editorAdapter;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        EditorView ev = editorAdapter.getEditorView();
        EditorModel em = editorAdapter.getEditorModel();

        int res = ev.getFcEditor().showOpenDialog(ev);

        if( res == JFileChooser.APPROVE_OPTION )
        {
            File file = ev.getFcEditor().getSelectedFile();
            String path = file.getAbsolutePath();

            try 
            {
                em.openFile(file);
                ev.getLbEditor().setText("File '" + path + "' opened");
                ev.getTaEditor().setText(em.getHexData().toUpperCase());
                ev.getTaEditor().setCaretPosition(0);
            }
            catch(Exception exp)
            {
                ev.showError(exp.toString());
            }
        }
    }

    @Override
    public void registerComponents() 
    {
        EditorView ev = editorAdapter.getEditorView();

        ev.getBtnOpen().addActionListener(this);
        ev.getOpenMenuItem().addActionListener(this);
    }
    
}
