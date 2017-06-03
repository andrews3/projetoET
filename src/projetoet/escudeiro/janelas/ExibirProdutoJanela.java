/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.janelas;

import java.awt.Container;
import java.net.URL;
import java.util.List;
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
import projetoet.escudeiro.eventos.ProdutoListener;
import projetoet.escudeiro.modelo.Produto;
import projetoet.escudeiro.modelo.ModeloTabelaProdutos;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.EscudeiroException;

public class ExibirProdutoJanela extends JInternalFrame {

    private ProdutoListener listener = new ProdutoListener(this);

    Container c;
    JTable tabelaProdutos;
    JList<String> setoresLista;
    JButton botaoExcluir, botaoLimparTabela;
    TitledBorder setorBorder;
    List<Setor> setores;
    DefaultListModel<String> model;
    ModeloTabelaProdutos modeloTabela;
    List<Produto> produtos;
    String currentSetor;

    public ModeloTabelaProdutos getModeloTabela() {
        return modeloTabela;
    }

    public String getCurrentSetor() {
        currentSetor = setoresLista.getSelectedValue();
        return currentSetor;
    }

    public void loadProdutos() {
        this.produtos = Repositorio.getProdutos(currentSetor);
        for (Produto p : produtos) {
            modeloTabela.addRow(p);
        }
    }

    private void insereComponentes() {
        JLabel setorLabel = new JLabel();
        setorLabel.setBounds(10, 10, 250, 400);
        setorBorder = new TitledBorder("Consultar por:");
        setorLabel.setBorder(setorBorder);
        c.add(setorLabel);

        model = new DefaultListModel();

        setoresLista = new JList<>(model);
        setoresLista.setLayoutOrientation(JList.VERTICAL);
        setoresLista.addListSelectionListener(listener);
        JScrollPane js = new JScrollPane(setoresLista);
        js.setBounds(20, 30, 232, 370);
        c.add(js);

        modeloTabela = new ModeloTabelaProdutos();
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane p = new JScrollPane(tabelaProdutos);
        p.setBounds(270, 10, 450, 350);
        p.createVerticalScrollBar();
        c.add(p);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(340, 365, 140, 40);
        botaoExcluir.addActionListener(listener);
        botaoExcluir.setActionCommand("remover");
        c.add(botaoExcluir);

        botaoLimparTabela = new JButton("Limpar Tabela");
        botaoLimparTabela.setBounds(510, 365, 140, 40);
        botaoLimparTabela.addActionListener(listener);
        botaoLimparTabela.setActionCommand("limpar");
        c.add(botaoLimparTabela);
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

    public Produto getProdutoRemocao() {
        int linha = tabelaProdutos.getSelectedRow();
        Produto p = modeloTabela.get(linha);
        modeloTabela.removeRow(linha);
        return p;
    }

    public boolean verificaRemocaoProduto() throws EscudeiroException {
        if (isRowSelected()) {
            int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o produto?", "Deletar Produto", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                return true;
            }
        } else {
            mostraMensagem("Verifique se há um produto selecionado e se há somente um produto selecionado");
        }
        return false;
    }

    public boolean verificaLimparTabela() throws EscudeiroException {
        if (currentSetor != null) {
            int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja limpar a tabela do setor " + currentSetor + "?",
                    "Limpar Lista de Produtos", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                return true;
            }
        } else {
            mostraMensagem("Nenhum setor foi selecionado, selecione um setor.");
        }
        return false;
    }

    public void limpaTabela() {
        modeloTabela.limpaTabela();
        Repositorio.limpaProdutosSetor(currentSetor);
    }

    public ExibirProdutoJanela() {
        super("Produtos em Falta", false, true, false, false);
        c = this.getContentPane();

        insereComponentes();

        setores = Repositorio.getSetores();
        for (Setor setor : setores) {
            model.addElement(setor.getNomeSetor());
        }

        URL url = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setLayout(null);
        this.setSize(750, 450);
        this.setLocation((1280 / 2) - (750 / 2), (700 / 2) - (450 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    private void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
