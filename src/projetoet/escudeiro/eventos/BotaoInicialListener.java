/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import projetoet.escudeiro.DAOs.SetorDAO;
import projetoet.escudeiro.janelas.JanelaPrincipal;;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class BotaoInicialListener implements ActionListener {

    private JanelaPrincipal frame;
    private SetorDAO dao;

    public BotaoInicialListener(JanelaPrincipal frame) {
        this.frame = frame;
        this.dao = new SetorDAO();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "cadastroProduto": {
                try {
                    frame.iniciaCadastroProdutos(dao.getSetores());
                } catch (EscudeiroException ex) {
                    Logger.getLogger(BotaoInicialListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "cadastroUsuario": {
                try {
                    frame.iniciaCadastroUsuario();
                } catch (EscudeiroException ex) {
                    Logger.getLogger(BotaoInicialListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "listarProdutos": {
                try {
                    frame.iniciaExibirProdutos(dao.getSetores());
                } catch (EscudeiroException ex) {
                    Logger.getLogger(BotaoInicialListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "sair": {
                try {
                    frame.sair();
                } catch (EscudeiroException ex) {
                    Logger.getLogger(BotaoInicialListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "cadastroSetor": {
                try {
                    frame.iniciaCadastroSetores(dao.getSetores());
                } catch (EscudeiroException ex) {
                    Logger.getLogger(BotaoInicialListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "sobrePrograma": {
                mostraMensagem("O Escudeiro das Compras", "O Escudeiro das Compras é uma aplicação Desktop como intuito de auxiliar\nos "
                        + "resposáveis de cada setor a repor produtos em falta.");
            }
            break;
            case "sobreDesenvolvedor": {
                mostraMensagem("O Desenvolvedor", "A aplicação Escudeiro das Compras foi projetada pelo desenvolvedor "
                        + "Andrews Duarte\ne desenvolvida durante as aulas de Tópicos Especiais I, regidas pelo professor Gilberto Vieira.");
            }
            break;
        }
    }

    private void mostraMensagem(String titulo, String mensagem) {
        Object[] options = {"Entendi"};
        int n = JOptionPane.showOptionDialog(null,
                mensagem,
                titulo,
                JOptionPane.OK_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title
    }

}
