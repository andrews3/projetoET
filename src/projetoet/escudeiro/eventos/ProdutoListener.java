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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import projetoet.escudeiro.janelas.CadastroProdutoJanela;
import projetoet.escudeiro.janelas.ExibirProdutoJanela;
import projetoet.escudeiro.janelas.JanelaPrincipal;
import projetoet.escudeiro.utilitarios.ECLogger;
import projetoet.escudeiro.modelo.Produto;
import projetoet.escudeiro.utilitarios.EscudeiroException;
import projetoet.escudeiro.utilitarios.Repositorio;

/**
 *
 * @author Andrews-PC
 */
public class ProdutoListener implements ActionListener, ListSelectionListener {

    private CadastroProdutoJanela frame;
    private ExibirProdutoJanela frame2;

    public ProdutoListener(CadastroProdutoJanela frame) {
        this.frame = frame;
    }

    public ProdutoListener(ExibirProdutoJanela frame2) {
        this.frame2 = frame2;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("adicionar".equals(ae.getActionCommand())) {
            try {
                if (frame.isValido()) {
                    Produto pro = frame.getProduto();
                    Repositorio.insereProduto(pro);
                    System.out.println(pro.toString());
                    mostraMensagem("Produto Cadastrado com Sucesso", "Cadastro Finalizado");
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " cadastrou o produto " + pro.getNome()
                                + " no setor " + pro.getSetor() + ";");
                    } catch (IOException ex) {
                        Logger.getLogger(ProdutoListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (EscudeiroException ex) {
                Logger.getLogger(ProdutoListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("remover".equals(ae.getActionCommand())) {
            try {
                if (frame2.verificaRemocaoProduto()) {
                    Produto p = frame2.getProdutoRemocao();
                    Repositorio.removeProduto(p);
                    System.out.println("Produto " + p.getNome() + " foi removido");
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " deletou o produto " + p.getNome() + ";");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (EscudeiroException ex) {
                Logger.getLogger(ProdutoListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("limpar".equals(ae.getActionCommand())) {
            try {
                if (frame2.verificaLimparTabela()) {
                    frame2.limpaTabela();
                    System.out.println("Limpou a tabela " + frame2.getCurrentSetor());
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " limpou a tabela de produtos do setor " + frame2.getCurrentSetor() + ";");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (EscudeiroException ex) {
                Logger.getLogger(ProdutoListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
            if (frame2.getModeloTabela().getRowCount() > 0) {
                frame2.getModeloTabela().limpaTabela();
            }
            String cSetor = frame2.getCurrentSetor();
            System.out.println("Selecionou o setor: " + cSetor);
            frame2.loadProdutos();
            try {
                ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " selecionou o setor " + cSetor + ";");
            } catch (IOException ex) {
                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    private void mostraMensagem(String m, String t) {
        JOptionPane.showMessageDialog(null, m, t, JOptionPane.INFORMATION_MESSAGE);
    }
}
