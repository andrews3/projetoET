/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import projetoet.util.Usuarios;

/**
 *
 * @author Andrews-PC
 */
public class LoginJanela extends JInternalFrame {

    Container c;
    ArrayList<Usuarios> usuarios;
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
        c.add(entrar);

    }

    private void mostraMensagemErro(String s, String i) {
        JOptionPane.showMessageDialog(null, s, i, JOptionPane.ERROR_MESSAGE);

    }

    private void loadUsuarios() {
        usuarios = new ArrayList();
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.getCaminhoBanco(), "sa", "");
            java.sql.Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM USUARIOS");
            while (rs.next()) {
                Usuarios user = new Usuarios();
                user.setNome(rs.getString("nomeUser"));
                user.setSenha(rs.getString("senhaUser"));
                usuarios.add(user);
            }
            stm.execute("SHUTDOWN");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getErrorCode());
            if (e.getErrorCode() == -5501) {
                mostraMensagemErro("O caminho do Banco de Dados está errado", "Erro Banco de Dados");
            }
        }
    }

    private boolean verificaUser(Usuarios u) {
        boolean b = false;
        for (Usuarios x : usuarios) {
            if (u.getNome().equals(x.getNome()) && u.getSenha().equals(x.getSenha())) {
                return true;
            }
        }

        return b;
    }

    public LoginJanela() {
        super("Escudeiro das Compras", false, true, false, false);
        c = this.getContentPane();

        insereComponentes();
        loadUsuarios();

        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (usuarioTf.getText().length() > 0 && senhaTf.getText().length() > 0) {
                    Usuarios user = new Usuarios(usuarioTf.getText(), senhaTf.getText());
                    System.out.println(user.getSenha());
                    if (verificaUser(user)) {
                        JanelaPrincipal.auxLogin();
                    } else {
                        mostraMensagemErro("Usuário ou senha inválidos", "Erro ao conectar-se");
                    }
                } else {
                    mostraMensagemErro("Não é possível conectar-se sem um usuário e uma senha", "Conexão inválida");
                }
            }
        });

        
        URL url = this.getClass().getClassLoader().getResource("imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setLayout(null);
        this.setSize(400, 300);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (330 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
    }

}