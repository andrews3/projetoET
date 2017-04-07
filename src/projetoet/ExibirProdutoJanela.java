/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet;

import java.awt.Container;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import projetoet.util.Produto;
import projetoet.util.ProdutoTableModel;
import projetoet.util.Setor;

public class ExibirProdutoJanela extends JInternalFrame {

    Container c;
    JTable tabelaProdutos;
    JList<String> setoresLista;
    JButton botaoExcluir, botaoLimparTabela;
    TitledBorder setorBorder;
    ArrayList<Setor> setores;
    DefaultListModel<String> model;
    ProdutoTableModel modeloTabela;
    ArrayList<Produto> produtos;
    String currentSetor;

    private void insereComponentes() {
        JLabel setorLabel = new JLabel();
        setorLabel.setBounds(10, 10, 250, 400);
        setorBorder = new TitledBorder("Consultar por:");
        setorLabel.setBorder(setorBorder);
        c.add(setorLabel);

        model = new DefaultListModel();

        setoresLista = new JList<>(model);
        setoresLista.setLayoutOrientation(JList.VERTICAL);
        JScrollPane js = new JScrollPane(setoresLista);
        js.setBounds(20, 30, 232, 370);
        c.add(js);

        modeloTabela = new ProdutoTableModel();
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane p = new JScrollPane(tabelaProdutos);
        p.setBounds(270, 10, 450, 350);
        p.createVerticalScrollBar();
        c.add(p);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(340, 365, 140, 40);
        c.add(botaoExcluir);

        botaoLimparTabela = new JButton("Limpar Tabela");
        botaoLimparTabela.setBounds(510, 365, 140, 40);
        c.add(botaoLimparTabela);
    }

    private void mostraMensagemErro(String s, String i) {
        JOptionPane.showMessageDialog(null, s, i, JOptionPane.ERROR_MESSAGE);

    }

    private void loadSetores() throws SQLException {
        setores = new ArrayList();
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
        for (Setor setor : setores) {
            model.addElement(setor.getNomeSetor());
        }
    }

    public void loadProdutos(String setor) {
        produtos = new ArrayList();
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.getCaminhoBanco(), "sa", "");
            java.sql.Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM PRODUTOS WHERE SETORPRODUTO='" + setor + "'");
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setNome(rs.getString("NOMEPRODUTO"));
                produto.setId(Integer.parseInt(rs.getString("CODPRODUTO")));
                produtos.add(produto);
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
        for (Produto produto : produtos) {
            modeloTabela.addRow(produto);
        }
    }

    public boolean isRowSelected() {
        boolean b = false;
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            if (tabelaProdutos.isRowSelected(i)) {
                if (!b) {
                    b = true;
                } else {
                    return false;
                }
            }
        }
        return b;
    }

    public void removeProdutoBanco(int id) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.getCaminhoBanco(), "sa", "");
            java.sql.Statement stm = con.createStatement();
            stm.executeQuery("DELETE FROM PRODUTOS Where CODPRODUTO='" + id + "'");
            stm.execute("SHUTDOWN");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
    }

    public void limpaSetor(String setor) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.getCaminhoBanco(), "sa", "");
            java.sql.Statement stm = con.createStatement();
            stm.executeQuery("DELETE FROM PRODUTOS Where SETORPRODUTO ='" + setor + "'");
            stm.execute("SHUTDOWN");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
    }

    public ExibirProdutoJanela() {
        super("Produtos em Falta", false, true, false, false);
        c = this.getContentPane();

        insereComponentes();
        try {
            loadSetores();
        } catch (SQLException ex) {
            Logger.getLogger(ExibirProdutoJanela.class.getName()).log(Level.SEVERE, null, ex);
        }

        URL url = this.getClass().getClassLoader().getResource("imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setLayout(null);
        this.setSize(750, 450);
        this.setLocation((1280 / 2) - (750 / 2), (700 / 2) - (450 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

}
