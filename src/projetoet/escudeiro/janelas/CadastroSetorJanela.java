/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.janelas;

import java.awt.Container;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
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
import projetoet.escudeiro.eventos.SetorListener;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.modelo.ModeloTabelaSetores;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class CadastroSetorJanela extends JInternalFrame {

    private SetorListener listener = new SetorListener(this);

    Container c;
    JTable tabelaSetores;
    ModeloTabelaSetores model;
    JDesktopPane mJdp;
    JTextField nomeTf;
    List<Setor> setores;
    JButton botaoSalvar, botaoDeletar;

    public ModeloTabelaSetores getModel() {
        return model;
    }

    private void insereComponentes() {
        model = new ModeloTabelaSetores();
        tabelaSetores = new JTable(model);

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
        botaoSalvar.addActionListener(listener);
        botaoSalvar.setActionCommand("adicionar");
        c.add(botaoSalvar);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(listener);
        botaoDeletar.setActionCommand("remover");
        botaoDeletar.setBounds(95, 375, 150, 30);
        c.add(botaoDeletar);

    }

    public Setor getSetor() {
        Setor s = new Setor();
        s.setNomeSetor(nomeTf.getText());
        s.setId(Repositorio.getCodAtual());
        nomeTf.setText("");
        return s;
    }

    public boolean isValido() {
        if (nomeTf.getText().length() > 0) {
            return true;
        }
        mostraMensagem("Não é possível cadastrar um setor sem nome");
        return false;
    }

    public void populaTabela() {
        for (Setor setor : setores) {
            model.addRow(setor);
        }
    }

    //Verifica se há alguma linha selecionada e verifica se há somente uma selecioanda
    public boolean isRowSelected() {
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

    public boolean validaRemocao() throws EscudeiroException {
        if (isRowSelected()) {
            UIManager.put("OptionPane.noButtonText", "Não");
            UIManager.put("OptionPane.yesButtonText", "Sim");
            int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o setor?", "Deletar Setor", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                return true;
            }
        } else {
            mostraMensagem("Verifique se há um setor selecionado e se há somente um setor selecionado");
        }
        return false;
    }

    public Setor getSetorRemocao() {
        int linha = tabelaSetores.getSelectedRow();
        Setor s = model.get(linha);
        model.removeRow(linha);
        return s;
    }

    public CadastroSetorJanela() {
        super("Cadastro de Setores", false, true, false, false);
        c = this.getContentPane();
        setores = Repositorio.getSetores();
        insereComponentes();
        populaTabela();

        URL url = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setLayout(null);
        this.setSize(350, 450);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (300 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    private void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
