package projetoet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Andrews-PC
 */
public class JanelaPrincipal extends JFrame {

    //// PROFESSOR, PARA TESTAR O SOFTWARE, ALTERE O CAMINHO DO BANCO////
    public static String caminhoBanco = "jdbc:hsqldb:file:/Users/Andrews-PC/Documents/NetBeansProjects/projetoET/db/banco/";
    ///////////

    Container c;
    JMenuItem menuCadastroProduto, menuCadastroSetor, menuCadastroSair;
    JMenuItem menuExibirProdutos;
    JMenuItem menuSobrePrograma, menuSobreDesenvolvedor;
    JButton botaoCadastrar, botaoExibir, botaoSair;
    static JanelaPrincipal main;
    JDesktopPane mJdp;
    boolean bJanelaCadastroProduto, bJanelaCadastroSetor,bJanelaSelecaoBanco;

    private void insereComponentes() {
        String copyright = "© Escudeiro das Compras 2017 - Todos os direitos reservados";
        JMenuBar barraMenu = new JMenuBar();

        JMenu menuCadastro = new JMenu(" Cadastro");
        menuCadastroProduto = new JMenuItem("Cadastrar Produto");
        menuCadastroSetor = new JMenuItem("Cadastrar Setor");
        menuCadastroSair = new JMenuItem("Sair");
        menuCadastro.add(menuCadastroProduto);
        menuCadastro.add(menuCadastroSetor);
        menuCadastro.addSeparator();
        menuCadastro.add(menuCadastroSair);
        barraMenu.add(menuCadastro);

        JMenu menuExibir = new JMenu(" Exibir");
        menuExibirProdutos = new JMenuItem("Listar Produtos");
        menuExibir.add(menuExibirProdutos);
        barraMenu.add(menuExibir);

        JMenu menuSobre = new JMenu(" Sobre");
        menuSobrePrograma = new JMenuItem("O Programa");
        menuSobreDesenvolvedor = new JMenuItem("O Desenvolvedor");
        menuSobre.add(menuSobrePrograma);
        menuSobre.add(menuSobreDesenvolvedor);
        barraMenu.add(menuSobre);

        this.setJMenuBar(barraMenu);

        botaoCadastrar = new JButton("Cadastrar Produto em Falta");
        botaoCadastrar.setFont(new Font("SansSerif", Font.BOLD, 15));
        botaoCadastrar.setBounds(20, 20, 250, 80);
        this.add(botaoCadastrar);

        botaoExibir = new JButton("Exibir Produtos em Falta");
        botaoExibir.setBounds(20, 110, 250, 80);
        botaoExibir.setFont(new Font("SansSerif", Font.BOLD, 15));
        this.add(botaoExibir);

        botaoSair = new JButton("Sair");
        botaoSair.setBounds(20, 590, 250, 50);
        this.add(botaoSair);

        JLabel encontradoLabel;
        Border encontradoBorder;
        encontradoLabel = new JLabel();
        encontradoLabel.setBounds(5, 5, 285, 645);
        encontradoBorder = BorderFactory.createRaisedBevelBorder();

        encontradoLabel.setBorder(encontradoBorder);
        this.add(encontradoLabel);

        JLabel copyrightLabel = new JLabel(copyright);
        copyrightLabel.setBounds(480, 640, 400, 25);
        this.add(copyrightLabel);

        ImageIcon icon = new ImageIcon("src/imagens/escudo.png");

        JLabel label = new JLabel(icon);
        label.setBounds(500, 60, 380, 500);
        this.add(label);

    }

    private void iniciaCadastroProdutos() {
        if (!bJanelaCadastroProduto) {
            CadastroProdutoJanela novaJanelaCadastroProduto;
            novaJanelaCadastroProduto = new CadastroProdutoJanela();
            novaJanelaCadastroProduto.setVisible(true);
            mJdp.add(novaJanelaCadastroProduto);
            bJanelaCadastroProduto = true;
            try {
                novaJanelaCadastroProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
            novaJanelaCadastroProduto.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaCadastroProduto = false;
                }

                @Override
                public void internalFrameOpened(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameClosed(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameIconified(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameDeiconified(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameActivated(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameDeactivated(InternalFrameEvent ife) {

                }
            });

        }

    }

    private void iniciaCadastroSetores() throws SQLException {
        if (!bJanelaCadastroSetor) {
            CadastroSetorJanela novaJanelaCadastroSetor;
            novaJanelaCadastroSetor = new CadastroSetorJanela();
            novaJanelaCadastroSetor.setVisible(true);
            mJdp.add(novaJanelaCadastroSetor);
            bJanelaCadastroSetor = true;
            try {
                novaJanelaCadastroSetor.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
            novaJanelaCadastroSetor.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaCadastroSetor = false;
                }

                @Override
                public void internalFrameOpened(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameClosed(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameIconified(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameDeiconified(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameActivated(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameDeactivated(InternalFrameEvent ife) {

                }
            });

        }
    }
    
    private void iniciaSelecaoBanco(){
    if (!bJanelaSelecaoBanco) {
            SelecaoBancoJanela novaJanelaSelecaoBanco;
            novaJanelaSelecaoBanco = new SelecaoBancoJanela();
            novaJanelaSelecaoBanco.setVisible(true);
            mJdp.add(novaJanelaSelecaoBanco);
            bJanelaSelecaoBanco = true;
            try {
                novaJanelaSelecaoBanco.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
            novaJanelaSelecaoBanco.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaSelecaoBanco = false;
                }

                @Override
                public void internalFrameOpened(InternalFrameEvent ife) {
                }

                @Override
                public void internalFrameClosed(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameIconified(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameDeiconified(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameActivated(InternalFrameEvent ife) {

                }

                @Override
                public void internalFrameDeactivated(InternalFrameEvent ife) {

                }
            });

        }
    }

    private void sair() {
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
        }
    }

    public JanelaPrincipal() {
        super("Escudeiro das Compras");
        mJdp = new JDesktopPane();
        setContentPane(mJdp);
        mJdp.putClientProperty("JDesktopPane.dragMode", "outline");
        insereComponentes();
        Color cor = new Color(193, 205, 205);
        mJdp.setBackground(cor);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciaCadastroProdutos();
            }
        });
        menuCadastroProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciaCadastroProdutos();
            }
        });
        menuCadastroSetor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    iniciaCadastroSetores();
                } catch (SQLException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sair();

            }
        });

        menuCadastroSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sair();
            }
        });

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        main = new JanelaPrincipal();
        main.setVisible(true);
    }

}
