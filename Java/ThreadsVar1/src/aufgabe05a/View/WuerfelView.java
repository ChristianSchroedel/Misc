/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WuerfelView.java
 *
 * Created on Oct 30, 2013, 3:27:13 PM
 */
package aufgabe05a.View;

/**
 *
 * @author christian
 */
public class WuerfelView extends javax.swing.JFrame {

    /** Creates new form WuerfelView */
    public WuerfelView() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbWuerfelAugen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnStartStop = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("W�rfel");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lbWuerfelAugen.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jPanel1.add(lbWuerfelAugen, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        btnStartStop.setText("Start");
        jPanel2.add(btnStartStop);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-422)/2, (screenSize.height-330)/2, 422, 330);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WuerfelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WuerfelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WuerfelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WuerfelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new WuerfelView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnStartStop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbWuerfelAugen;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the btnStartStop
     */
    public javax.swing.JToggleButton getBtnStartStop() {
        return btnStartStop;
    }

    /**
     * @return the lbWuerfelAugen
     */
    public javax.swing.JLabel getLbWuerfelAugen() {
        return lbWuerfelAugen;
    }
}
