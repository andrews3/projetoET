/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.janelas;

import java.awt.Container;
import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import projetoet.escudeiro.eventos.ProdutoListener;
import projetoet.escudeiro.modelo.Produto;
import projetoet.escudeiro.utilitarios.Repositorio;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.EscudeiroException;

/**
 *
 * @author Andrews-PC
 */
public class CadastroProdutoJanela extends JInternalFrame {

    private ProdutoListener listener = new ProdutoListener(this);
    Container c;
    JTextField nomeTf;
    JTextField codTf;
    JComboBox setorCb;
    JButton adicionarBotao;

    public Produto getProduto() {
        Produto p = new Produto();
        p.setNome(nomeTf.getText());
        p.setId(Integer.parseInt(codTf.getText()));
        p.setSetor(setorCb.getSelectedItem().toString());
        nomeTf.setText("");
        codTf.setText("");
        return p;//TODO: retornar a instaancia com os dados da janela
    }

    public boolean isValido() throws EscudeiroException {
        if (codTf.getText().length() >= 1) {
            if (verificaCodigo()) {
                if (Repositorio.verificaCodigo(Integer.parseInt(codTf.getText()))) {
                    return true;
                } else {
                    mostraMensagem("Esse código de produto já foi cadastrado");
                }
            } else {
                mostraMensagem("O Código não pode possuir letras, somente números");
            }
        } else {
            mostraMensagem("Não é possível cadastrar um produto sem um código");
        }
        return false;
    }

    private void insereComponentes() {
        JLabel codLabel = new JLabel("Código do produto:");
        codLabel.setBounds(10, 15, 140, 14);
        c.add(codLabel);

        Font font = new Font("SansSerif", Font.PLAIN, 15);

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

        setorCb = new JComboBox();
        setorCb.setBounds(120, 80, 260, 30);
        setorCb.setFont(font);
        for (Setor i : Repositorio.getSetores()) {
            setorCb.addItem(i.getNomeSetor());
        }
        c.add(setorCb);

        adicionarBotao = new JButton("Adicionar Produto");
        adicionarBotao.setBounds(50, 140, 300, 50);
        adicionarBotao.setFont(new Font("SansSerif", Font.BOLD, 15));
        adicionarBotao.addActionListener(listener);
        adicionarBotao.setActionCommand("adicionar");
        c.add(adicionarBotao);

    }

    //Gambiarra feita para verificar se há somente números no código;
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
        insereComponentes();
        URL url = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/shield2.png");
        ImageIcon imagemTitulo = new ImageIcon(url);
        this.setFrameIcon(imagemTitulo);
        this.setSize(400, 240);
        this.setLocation((1280 / 2) - (400 / 2), (700 / 2) - (240 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    private void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
