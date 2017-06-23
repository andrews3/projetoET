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
import projetoet.escudeiro.DAOs.UsuarioDAO;
import projetoet.escudeiro.janelas.CadastroUsuarioJanela;
import projetoet.escudeiro.janelas.JanelaPrincipal;
import projetoet.escudeiro.janelas.LoginJanela;
import projetoet.escudeiro.utilitarios.ECLogger;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Usuarios;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class UsuarioListener implements ActionListener {

    private LoginJanela frame;
    private CadastroUsuarioJanela frame2;
    private UsuarioDAO dao = new UsuarioDAO();

    public UsuarioListener(LoginJanela frame) {
        this.frame = frame;
    }

    public UsuarioListener(CadastroUsuarioJanela frame2) {
        this.frame2 = frame2;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("entrar".equals(ae.getActionCommand())) {
            try {
                if (frame.verificaStrings()) {
                    Usuarios u = frame.getUsuario();
                    u = dao.isUser(u);
                    if (u != null) {
                        Repositorio.setConec(true);
                        Repositorio.setUsuarioConec(u);
                        System.out.println("Login efeituado com sucesso");
                        frame.setVisible(false);
                        frame.saveLastUser(u.getNome());
                        JanelaPrincipal.visibilidadeComponentes(true);
                        try {
                            ECLogger.insereLog("Usuário " + u.getNome() + " conectou-se;");
                        } catch (IOException ex) {
                            throw new EscudeiroException(ex);
                        }
                    } else {
                        mostraMensagem("Erro de autenticação", "Usuário ou senha inválidos");
                    }
                }
            } catch (EscudeiroException ex) {
                Logger.getLogger(UsuarioListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("cadastraUsuario".equals(ae.getActionCommand())) {
            try {
                if (frame2.verificaEntrada()) {
                    Usuarios u = frame2.getUsuario();
                    System.out.println(u.toString());
                    dao.insert(u);
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " cadastrou o usuário " + u.getNome() + ";");
                    } catch (IOException ex) {
                        throw new EscudeiroException(ex);
                    }
                    mostraMensagem("Cadastrado com Sucesso", "O usuário foi cadastrado com sucesso!");
                }
            } catch (EscudeiroException ex) {
                Logger.getLogger(UsuarioListener.class.getName()).log(Level.SEVERE, null, ex);
            }
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
