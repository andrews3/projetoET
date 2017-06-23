/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import projetoet.escudeiro.DAOs.SetorDAO;
import projetoet.escudeiro.janelas.CadastroSetorJanela;
import projetoet.escudeiro.janelas.JanelaPrincipal;
import projetoet.escudeiro.modelo.ModeloTabelaSetores;
import projetoet.escudeiro.utilitarios.ECLogger;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class SetorListener implements ActionListener, TableModelListener {

    private CadastroSetorJanela frame;
    private SetorDAO dao;

    public SetorListener(CadastroSetorJanela frame) {
        this.frame = frame;
        dao = new SetorDAO();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("adicionar".equals(ae.getActionCommand())) {
            if (frame.isValido()) {
                Setor s = frame.getSetor();
                try {
                    dao.insert(s);
                } catch (EscudeiroException ex) {
                    Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(s.toString());
                try {
                    ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " cadastrou o setor " + s.getNomeSetor() + ";");
                } catch (IOException ex) {
                    Logger.getLogger(CadastroSetorJanela.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    frame.setores = dao.getSetores();
                } catch (EscudeiroException ex) {
                    Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.getModel().removeAll();
                try {
                    frame.setores = dao.getSetores();
                    frame.populaTabela();
                } catch (EscudeiroException ex) {
                    Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else if ("remover".equals(ae.getActionCommand())) {
            try {
                if (frame.validaRemocao()) {
                    Setor s = frame.getSetorRemocao();
//                    Repositorio.removeSetor(s);
                    dao.remover(s.getId());
                    System.out.println("Setor " + s.toString() + " foi removido");
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " deletou o setor " + s.getNomeSetor() + ";");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (EscudeiroException ex) {
                try {
                    ECLogger.insereLogException(ex);
                } catch (IOException ex1) {
                    Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    private void mostraMensagem(String m, String t) {
        JOptionPane.showMessageDialog(null, m, t, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        int linha = tme.getFirstRow();
        try {
            Setor s = frame.getModelLinha(linha);

            try {
                ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " alterou o nome de um setor para " + s.getNomeSetor() + ";");
            } catch (IOException ex) {
                Logger.getLogger(ModeloTabelaSetores.class.getName()).log(Level.SEVERE, null, ex);
            }
//            Repositorio.atualizaSetores(s);
            dao.update(s);
        } catch (IndexOutOfBoundsException ie) {
            try {
                ECLogger.insereLogException(ie);
            } catch (IOException ex) {
                Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (EscudeiroException ex) {
            Logger.getLogger(SetorListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
