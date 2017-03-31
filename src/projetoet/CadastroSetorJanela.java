/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import projetoet.util.Setor;
import projetoet.util.SetorTableModel;

/**
 *
 * @author Andrews-PC
 */
public class CadastroSetorJanela extends JInternalFrame {

    Container c;
    JTable tabelaSetores;
    SetorTableModel model;
    JDesktopPane mJdp;
    JTextField nomeTf;
    ArrayList<Setor> setores;
    JButton botaoSalvar, botaoDeletar;

    private void mostraMensagemErro(String s, String i) {
        JOptionPane.showMessageDialog(null, s, i, JOptionPane.ERROR_MESSAGE);

    }

    private ArrayList<Setor> loadSetores() throws SQLException {
        setores = new ArrayList();
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.caminhoBanco, "sa", "");
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

    private void insereComponentes() {
        model = new SetorTableModel();
        tabelaSetores = new JTable(model);
        populaTabela();
        JScrollPane p = new JScrollPane(tabelaSetores);
        p.setBounds(5, 150, 331, 215);
        p.createVerticalScrollBar();
        c.add(p);

        JLabel cadastroLabel = new JLabel();
        cadastroLabel.setBounds(10, 10, 322, 135);
        TitledBorder cadastroBorder = new TitledBorder("Cadastro de Setores:");
        cadastroLabel.setBorder(cadastroBorder);
        c.add(cadastroLabel);

        JLabel labelNome = new JLabel("Nome do Setor:");
        labelNome.setBounds(125, 25, 100, 22);
        c.add(labelNome);

        nomeTf = new JTextField();
        nomeTf.setBounds(45, 50, 250, 30);
        c.add(nomeTf);

        botaoSalvar = new JButton("Cadastrar");
        botaoSalvar.setBounds(95, 90, 150, 30);
        c.add(botaoSalvar);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.setBounds(95, 375, 150, 30);
        c.add(botaoDeletar);

    }

    private boolean cadastraSetor() {
        String nomeSetor = nomeTf.getText();
        if (nomeSetor.length() > 0) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                Connection con;
                con = DriverManager.getConnection(JanelaPrincipal.caminhoBanco, "sa", "");
                java.sql.Statement stm = con.createStatement();
                stm.executeQuery("INSERT INTO SETORES (NOMESETOR) VALUES('" + nomeSetor + "')");
                stm.execute("SHUTDOWN");
                nomeTf.setText("");
                return true;
            } catch (ClassNotFoundException e) {
                System.out.println("Erro ao carregar o driver JDBC. ");
            } catch (SQLException e) {
                System.out.println("Erro de SQL: " + e.getMessage());
            }
        } else {
            mostraMensagemErro("Não é possível cadastrar um setor sem nome", "Cadastro Inválido");
        }
        return false;
    }

    private void populaTabela() {
        for (Setor setor : setores) {
            model.addRow(setor);
        }
    }

    private void removeSetorBanco(int id) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.caminhoBanco, "sa", "");
            java.sql.Statement stm = con.createStatement();
            stm.executeQuery("DELETE FROM SETORES Where idsetor='" + id + "'");
            stm.execute("SHUTDOWN");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

    }

    //Verifica se há alguma linha selecionada e verifica se há somente uma selecioanda
    private boolean isRowSelected() {
        boolean b = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (tabelaSetores.isRowSelected(i)) {
                if (!b) {
                    b = true;
                } else {
                    return false;
                }
            }
        }
        return b;
    }

    public CadastroSetorJanela() throws SQLException {
        super("Cadastro de Setores", false, true, false, false);
        c = this.getContentPane();
        setores = loadSetores();
        insereComponentes();

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean bCadastro = cadastraSetor();
                if (bCadastro) {
                    try {
                        loadSetores();
                    } catch (SQLException ex) {
                        Logger.getLogger(CadastroSetorJanela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    model = new SetorTableModel();
                    tabelaSetores.setModel(model);
                    populaTabela();
                }
            }
        });

        botaoDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UIManager.put("OptionPane.cancelButtonText", "");
                UIManager.put("OptionPane.noButtonText", "Não");
                UIManager.put("OptionPane.yesButtonText", "Sim");
                if (isRowSelected()) {
                    int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o setor?", "Deletar Produto", JOptionPane.YES_NO_OPTION);
                    if (op == JOptionPane.YES_OPTION) {
                        int linha = tabelaSetores.getSelectedRow();
                        Setor s = model.get(linha);
                        removeSetorBanco(s.getId());
                        model.removeRow(linha);
                    }
                } else {
                    mostraMensagemErro("Verifique se há um setor selecionado e se há somente um setor selecionado", "Não foi possível deletar");
                }

            }
        });

        this.setLayout(null);
        this.setSize(350, 450);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (300 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

}
