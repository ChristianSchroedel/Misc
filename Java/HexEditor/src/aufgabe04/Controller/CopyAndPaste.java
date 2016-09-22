/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe04.Controller;

import aufgabe04.Model.EditorModel;
import aufgabe04.View.EditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author christian
 */
public class CopyAndPaste implements Adapter, ActionListener
{
    private EditorAdapter editorAdapter;

    CopyAndPaste(EditorAdapter editorAdapter) 
    {
        this.editorAdapter = editorAdapter;
    }

    @Override
    public void registerComponents() 
    {
        EditorView ev = editorAdapter.getEditorView();
        
        ev.getCopyMenuItem().addActionListener(this);
        ev.getPasteMenuItem().addActionListener(this);
        ev.getPumCopy().addActionListener(this);
        ev.getPumPaste().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        EditorView ev = editorAdapter.getEditorView();
        EditorModel em = editorAdapter.getEditorModel();
        
        Object src = evt.getSource();
        
        if( src == ev.getCopyMenuItem() || src == ev.getPumCopy() )
            em.copyText(ev.getTaEditor().getSelectedText());
        else if( src == ev.getPasteMenuItem() || src == ev.getPumPaste() )
            ev.getTaEditor().insert(em.pasteText(), ev.getTaEditor().getCaretPosition());
    }
    
}
