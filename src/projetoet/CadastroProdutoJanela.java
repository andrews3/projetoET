/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet;

import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import projetoet.util.Produto;
import projetoet.util.Setor;

/**
 *
 * @author Andrews-PC
 */
public class CadastroProdutoJanela extends JInternalFrame {

    Container c;
    JTextField nomeTf;
    JTextField codTf;
    JComboBox setorCb;
    JButton adicionarBotao;

    private ArrayList<Setor> loadSetores() throws SQLException {
        ArrayList<Setor> setores = new ArrayList();
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.getCaminhoBanco(), "sa", "");
            java.sql.Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM SETORES");
            while (rs.next()) {
                Setor setor = new Setor();
                setor.setNomeSetor(rs.getString("nomeSetor"));
                setor.setId(Integer.parseInt(rs.getString("idSetor")));
                setores.add(setor);
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

        return setores;
    }

    private void insereComponentes() throws SQLException {
        JLabel codLabel = new JLabel("Código do produto:");
        codLabel.setBounds(10, 15, 140, 14);
        c.add(codLabel);

        Font font = new Font("SansSerif", Font.PLAIN, 15);

        // If you want the value to be committed on each keystroke instead of focus lost
        codTf = new JTextField();
        codTf.setBounds(120, 9, 260, 30);
        codTf.setFont(font);
        c.add(codTf);

        JLabel nomeLabel = new JLabel("Nome do produto:");
        nomeLabel.setBounds(10, 50, 140, 14);
        c.add(nomeLabel);

        nomeTf = new JTextField();
        nomeTf.setFont(font);
        nomeTf.setBounds(120, 44, 260, 30);
        c.add(nomeTf);

        JLabel setorLabel = new JLabel("Setor do produto:");
        setorLabel.setBounds(10, 86, 140, 14);
        c.add(setorLabel);

        ArrayList<Setor> x;
        x = loadSetores();

        setorCb = new JComboBox();
        setorCb.setBounds(120, 80, 260, 30);
        setorCb.setFont(font);
        for (Setor i : x) {
            setorCb.addItem(i.getNomeSetor());
        }
        c.add(setorCb);

        adicionarBotao = new JButton("Adicionar Produto");
        adicionarBotao.setBounds(50, 140, 300, 50);
        adicionarBotao.setFont(new Font("SansSerif", Font.BOLD, 15));
        c.add(adicionarBotao);

    }

    public void insereProdutos(Produto produto) throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.getCaminhoBanco(), "sa", "");
            java.sql.Statement stm = con.createStatement();
            stm.executeQuery("INSERT INTO PRODUTOS(NOMEPRODUTO,CODPRODUTO,SETORPRODUTO) VALUES('" + produto.getNome() + "','" + produto.getId() + "','" + produto.getSetor() + "')");
            stm.execute("SHUTDOWN");
            nomeTf.setText("");
            codTf.setText("");
            mostraMensagem("Produto Cadastrado com Sucesso", "Cadastro Finalizado");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLIntegrityConstraintViolationException e) {
            mostraMensagemErro("Código do produto já está na lista", "Código Inválido");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
    }

    private void mostraMensagemErro(String s, String i) {
        JOptionPane.showMessageDialog(null, s, i, JOptionPane.ERROR_MESSAGE);
    }

    private void mostraMensagem(String m, String t) {
        JOptionPane.showMessageDialog(null, m, t, JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean verificaCodigo() {
        String cod = codTf.getText();
        boolean resposta = true;
        ArrayList<Character> arrayCod = new ArrayList();

        arrayCod.add('0');
        arrayCod.add('1');
        arrayCod.add('2');
        arrayCod.add('3');
        arrayCod.add('4');
        arrayCod.add('5');
        arrayCod.add('6');
        arrayCod.add('7');
        arrayCod.add('8');
        arrayCod.add('9');

        for (int i = 0; i < cod.length(); i++) {
            if (!arrayCod.contains(cod.charAt(i))) {
                return false;
            }
        }
        return resposta;
    }

    public CadastroProdutoJanela() {
        super("Cadastro de Produtos em Falta", false, true, false, false);
        c = getContentPane();
        c.setLayout(null);
        try {
            insereComponentes();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoJanela.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setSize(400, 240);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (240 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

    }

}
