/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.janelas;

import java.awt.Container;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import projetoet.escudeiro.eventos.UsuarioListener;
import projetoet.escudeiro.modelo.Usuarios;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class CadastroUsuarioJanela extends JInternalFrame {

    private UsuarioListener listener = new UsuarioListener(this);

    private Container c;
    private JLabel nomeLabel, senhaLabel;
    private JTextField nomeTf;
    private JPasswordField senhaTf;
    private JButton cadastraButton;

    private void insereComponentes() {
        nomeLabel = new JLabel("Nome do usuário:");
        nomeLabel.setBounds(170, 10, 150, 30);
        c.add(nomeLabel);

        nomeTf = new JTextField();
        nomeTf.setBounds(100, 40, 250, 30);
        c.add(nomeTf);

        senhaLabel = new JLabel("Senha do usuário:");
        senhaLabel.setBounds(170, 80, 150, 30);
        c.add(senhaLabel);

        senhaTf = new JPasswordField();
        senhaTf.setBounds(100, 110, 250, 30);
        c.add(senhaTf);

        cadastraButton = new JButton("Cadastrar");
        cadastraButton.setBounds(150, 160, 150, 35);
        cadastraButton.addActionListener(listener);
        cadastraButton.setActionCommand("cadastraUsuario");
        c.add(cadastraButton);
    }

    public Usuarios getUsuario() {
        Usuarios u = new Usuarios();
        u.setNome(nomeTf.getText());
        u.setSenha(senhaTf.getText());
        senhaTf.setText("");
        nomeTf.setText("");
        return u;
    }

    public boolean verificaEntrada() throws EscudeiroException {
        if (nomeTf.getText().length() > 0 && senhaTf.getText().length() > 0) {
            return true;
        }
        mostraMensagem("É necessário que digite um nome e uma senha para o usuário!");
        return false;
    }

    public CadastroUsuarioJanela() {
        super("Cadastro de Usuários", false, true, false, false);
        c = this.getContentPane();
        insereComponentes();

        URL url = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setLayout(null);
        this.setSize(450, 250);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (300 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
    
    private void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
