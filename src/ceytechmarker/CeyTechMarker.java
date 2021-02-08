/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceytechmarker;

import ceytechmarker.gui.MainWindow;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.UIManager;

/**
 *
 * @author isuru
 */
public class CeyTechMarker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new MainWindow().setVisible(true);
            }
        });
    }

}
