/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import projetoet.escudeiro.janelas.CadastroSetorJanela;
import projetoet.escudeiro.janelas.JanelaPrincipal;
import projetoet.escudeiro.utilitarios.ECLogger;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class SetorListener implements ActionListener {

    private CadastroSetorJanela frame;

    public SetorListener(CadastroSetorJanela frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("adicionar".equals(ae.getActionCommand())) {
            if (frame.isValido()) {
                Setor s = frame.getSetor();
                Repositorio.insereSetor(s);
                System.out.println(s.toString());
                Repositorio.incrementaCodAtual();
                try {
                    ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " cadastrou o setor " + s.getNomeSetor() + ";");
                } catch (IOException ex) {
                    Logger.getLogger(CadastroSetorJanela.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.getModel().removeAll();
                frame.populaTabela();
            }
        }else if("remover".equals(ae.getActionCommand())){
            try {
                if(frame.validaRemocao()){
                    Setor s = frame.getSetorRemocao();
                    Repositorio.removeSetor(s);
                    System.out.println("Setor " + s.toString() + " foi removido");
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " deletou o setor " + s.getNomeSetor() + ";");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (EscudeiroException ex) {
                Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostraMensagem(String m, String t) {
        JOptionPane.showMessageDialog(null, m, t, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
