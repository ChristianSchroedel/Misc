/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aufgabe09.countdownbean.View;

/**
 *
 * @author christian
 */
public class View extends javax.swing.JPanel
{

    /**
     * Creates new form View
     */
    public View()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        counterValue = new javax.swing.JLabel();

        counterValue.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        counterValue.setText("0");
        add(counterValue);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel counterValue;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the counterValue
     */
    public javax.swing.JLabel getCounterValue()
    {
        return counterValue;
    }
}