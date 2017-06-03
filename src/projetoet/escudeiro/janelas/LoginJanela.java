/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.janelas;

import java.awt.Container;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import projetoet.escudeiro.eventos.UsuarioListener;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Usuarios;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class LoginJanela extends JInternalFrame {

    private UsuarioListener listener = new UsuarioListener(this);
    Container c;
    List<Usuarios> usuarios;
    JTextField usuarioTf;
    JPasswordField senhaTf;
    JButton entrar;

    private void insereComponentes() {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(font);
        usuarioLabel.setBounds(170, 20, 60, 13);
        c.add(usuarioLabel);

        usuarioTf = new JTextField();
        usuarioTf.setBounds(50, 40, 300, 30);
        c.add(usuarioTf);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(font);
        senhaLabel.setBounds(170, 80, 60, 13);
        c.add(senhaLabel);

        senhaTf = new JPasswordField();
        senhaTf.setBounds(50, 103, 300, 30);
        c.add(senhaTf);

        entrar = new JButton("Conectar");
        entrar.setFont(font);
        entrar.setBounds(125, 170, 150, 40);
        entrar.addActionListener(listener);
        entrar.setActionCommand("entrar");
        c.add(entrar);

    }

    public Usuarios getUsuario() {
        Usuarios u = new Usuarios();
        u.setNome(usuarioTf.getText());
        u.setSenha(senhaTf.getText());
        return u;
    }

    public boolean verificaStrings() throws EscudeiroException {
        if (usuarioTf.getText().length() > 0 && senhaTf.getText().length() > 0) {
            Usuarios user = new Usuarios(usuarioTf.getText(), senhaTf.getText());
            if (verificaUser(user)) {
                setVisible(false);
                JanelaPrincipal.main.visibilidadeComponentes(true);
                return true;
            } else {
                mostraMensagem("Usuário ou senha inválidos");
            }
        } else {
            /// SIMULAÇÃO DE EXCEPTION PARA TESTE DO LOG /////
            try {                                           //
                String f = null;                            //
                int i = Integer.parseInt(f);                //
            } catch (NumberFormatException e) {             //
                throw new EscudeiroException(e);            //
            }                                               //
           ///////////////////////////////////////////////////

            mostraMensagem("Não é possível conectar-se sem um usuário e uma senha");
        }
        return false;
    }

    public boolean verificaUser(Usuarios u) throws EscudeiroException {
        usuarios = Repositorio.getUsuarios();
        for (Usuarios x : usuarios) {
            if (u.getNome().equals(x.getNome()) && u.getSenha().equals(x.getSenha())) {
                saveLastUser(u.getNome());
                return true;
            }
        }
        return false;
    }

    private void loadLastUser() throws EscudeiroException {
        try {
            FileReader fileReader = new FileReader("LUC");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String luc;
            try {
                luc = bufferedReader.readLine();
            } catch (IOException ex) {
                throw new EscudeiroException(ex);
            }
            usuarioTf.setText(luc);
        } catch (FileNotFoundException ex) {
            throw new EscudeiroException(ex);
        }
    }

    private void saveLastUser(String login) throws EscudeiroException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("LUC");
        } catch (IOException ex) {
            throw new EscudeiroException(ex);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(login);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ex) {
            throw new EscudeiroException(ex);
        }
    }

    public LoginJanela() throws EscudeiroException {
        super("Escudeiro das Compras", false, true, false, false);
        c = this.getContentPane();

        insereComponentes();
        usuarios = Repositorio.getUsuarios();
        loadLastUser();

        URL url = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setLayout(null);
        this.setSize(400, 300);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (330 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
    }

    private void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
