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
public class CadastroProdutoJanela extends JInternalFrame {

    Container c;

    public CadastroProdutoJanela() {
        super("Cadastro de Produtos", false, true, false, false);
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }

    
}
