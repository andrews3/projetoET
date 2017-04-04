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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

        botaoProcuraCaminho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();

                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(c);
                caminhoArquivo = fc.getSelectedFile().getAbsolutePath();
                caminhoBancoTf.setText(caminhoArquivo);
            }
        });

        botaoSalvarCaminho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar esse caminho para o banco de dados? Caso "
                        + "o caminho esteja errado,\ntoda a aplicação deixará de funcionar.", "Salvar Banco de Dados", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    if (caminhoArquivo.length() > 2) {
                        if (caminhoArquivo.charAt(0) == 'C') {
                            int maximoArray = caminhoArquivo.length() - 2;
                            caminhoArquivoFinal = new char[maximoArray];
                            int y = 0;
                            for (int i = 2; i < caminhoArquivo.length(); i++) {
                                if (caminhoArquivo.charAt(i) == '\\') {
                                    caminhoArquivoFinal[y] = '/';
                                } else {
                                    caminhoArquivoFinal[y] = caminhoArquivo.charAt(i);
                                }
                                y++;
                            }

                            String l = "";
                            for (int i = 0; i < caminhoArquivoFinal.length; i++) {
                                l = l + caminhoArquivoFinal[i];
                            }
                            JanelaPrincipal.parteCaminhoBanco = l + "/";
                        } else {
                            JOptionPane.showMessageDialog(null, "Não é possível selecionar o banco de dados de fora da raiz C:", "Caminho Inválido", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Caminho do Banco não alterado.", "Caminho Inalterado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.setLayout(null);
        this.setSize(450, 178);
        this.setLocation((1280 / 2) - (450 / 2), (700 / 2) - (300 / 2));
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

}
