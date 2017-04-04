/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Andrews-PC
 */
public class SelecaoBancoJanela extends JInternalFrame {

    Container c;
    JTextField caminhoBancoTf;
    JButton botaoProcuraCaminho, botaoSalvarCaminho;
    String caminhoArquivo = "";
    char[] caminhoArquivoFinal;

    private void insereComponentes() {
        JLabel labelCaminhoBanco = new JLabel("Adicione o caminho do banco de dados:");
        labelCaminhoBanco.setBounds(75, 10, 300, 30);
        labelCaminhoBanco.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        c.add(labelCaminhoBanco);

        caminhoBancoTf = new JTextField();
        caminhoBancoTf.setBounds(20, 50, 330, 35);
        caminhoBancoTf.setEditable(false);
        c.add(caminhoBancoTf);

        botaoProcuraCaminho = new JButton("...");
        botaoProcuraCaminho.setBounds(355, 50, 70, 35);
        c.add(botaoProcuraCaminho);

        botaoSalvarCaminho = new JButton("Salvar");
        botaoSalvarCaminho.setBounds(175, 95, 100, 40);
        c.add(botaoSalvarCaminho);

    }

    public SelecaoBancoJanela() {
        super("Seleção Banco", false, true, false, false);
        c = this.getContentPane();
        insereComponentes();
        caminhoBancoTf.setText(JanelaPrincipal.parteCaminhoBanco);

        this.setLayout(null);
        this.setSize(450, 178);
        this.setLocation((1280 / 2) - (450 / 2), (700 / 2) - (300 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

}
