/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet;

import java.awt.Container;
import javax.swing.JInternalFrame;

/**
 *
 * @author Andrews-PC
 */
public class SelecaoBancoJanela extends JInternalFrame {

    Container c;
    
    public SelecaoBancoJanela() {
        super("Seleção Banco", false, true, false, false);
        c = this.getContentPane();
        
        this.setLayout(null);
        this.setSize(350, 450);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (300 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
    
    
    
}
