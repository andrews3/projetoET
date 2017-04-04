package projetoet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
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

    //// Professor, para testar o software altere o parteCaminhoBanco////
    public static String parteCaminhoBanco = "/Users/Andrews-PC/Documents/NetBeansProjects/projetoET/db/banco/";
    public static String caminhoBanco = "jdbc:hsqldb:file:";
    ///////////

    Container c;
    JMenuBar barraMenu;
    JMenuItem menuCadastroProduto, menuCadastroSetor, menuCadastroSair;
    JMenuItem menuExibirProdutos;
    JMenuItem menuSobrePrograma, menuSobreDesenvolvedor, menuSobreBancoDados;
    JMenuItem menuOpcoesBanco;
    static JMenu menuExibir, menuSobre, menuCadastro;
    static JButton botaoCadastrar, botaoExibir, botaoSair;
    static JanelaPrincipal main;
    JDesktopPane mJdp;
    static LoginJanela loginJanela;
    boolean bJanelaCadastroProduto, bJanelaCadastroSetor, bJanelaSelecaoBanco, bJanelaExibirProdutos;

    static private void visibilidadeComponentes(boolean b) {
        menuCadastro.setVisible(b);
        menuExibir.setVisible(b);
        botaoCadastrar.setVisible(b);
        botaoExibir.setVisible(b);
    }

    public static String getCaminhoBanco() {
        return caminhoBanco + parteCaminhoBanco;
    }

    private void insereComponentes() {
        String copyright = "© Escudeiro das Compras 2017 - Todos os direitos reservados";
        barraMenu = new JMenuBar();

        menuCadastro = new JMenu(" Cadastro");
        menuCadastroProduto = new JMenuItem("Cadastrar Produto");
        menuCadastroSetor = new JMenuItem("Cadastrar Setor");
        menuCadastroSair = new JMenuItem("Sair");
        menuCadastro.add(menuCadastroProduto);
        menuCadastro.add(menuCadastroSetor);
        menuCadastro.addSeparator();
        menuCadastro.add(menuCadastroSair);
        barraMenu.add(menuCadastro);

        menuExibir = new JMenu(" Exibir");
        menuExibirProdutos = new JMenuItem("Listar Produtos");
        menuExibir.add(menuExibirProdutos);
        barraMenu.add(menuExibir);

        JMenu menuOpcoes = new JMenu("Opções");
        menuOpcoesBanco = new JMenuItem("Alterar Caminho do Banco de Dados");
        menuOpcoes.add(menuOpcoesBanco);
        barraMenu.add(menuOpcoes);

        menuSobre = new JMenu(" Sobre");
        menuSobrePrograma = new JMenuItem("O Programa");
        menuSobreDesenvolvedor = new JMenuItem("O Desenvolvedor");
        menuSobreBancoDados = new JMenuItem("Como Encontrar o Banco de Dados");
        menuSobre.add(menuSobrePrograma);
        menuSobre.add(menuSobreDesenvolvedor);
        menuSobre.add(menuSobreBancoDados);
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

        URL imgUrl = this.getClass().getClassLoader().getResource("imagens/escudo.png");
        ImageIcon icon = new ImageIcon(imgUrl);
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

    private void iniciaSelecaoBanco() {
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

    private void iniciaExibirProdutos() {
        if (!bJanelaExibirProdutos) {
            ExibirProdutoJanela novaJanelaExibirProduto;
            novaJanelaExibirProduto = new ExibirProdutoJanela();
            novaJanelaExibirProduto.setVisible(true);
            mJdp.add(novaJanelaExibirProduto);
            bJanelaExibirProdutos = true;
            try {
                novaJanelaExibirProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
            novaJanelaExibirProduto.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaExibirProdutos = false;
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

    private void iniciaLogin() {
        loginJanela = new LoginJanela();
        loginJanela.setVisible(true);
        loginJanela.toFront();
        mJdp.add(loginJanela);
        visibilidadeComponentes(false);

    }

    public static void auxLogin() {
        loginJanela.setVisible(false);
        visibilidadeComponentes(true);
    }

    private void sair() {
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
        }
    }

    private void mostraMensagem(String titulo, String mensagem) {
        Object[] options = {"Entendi"};
        int n = JOptionPane.showOptionDialog(null,
                mensagem,
                titulo,
                JOptionPane.OK_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title
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
        botaoExibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciaExibirProdutos();
            }
        });
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sair();

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
        menuCadastroSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sair();
            }
        });
        menuExibirProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciaExibirProdutos();
            }
        });
        menuOpcoesBanco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciaSelecaoBanco();
            }
        });

        menuSobrePrograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostraMensagem("O Escudeiro das Compras", "O Escudeiro das Compras é uma aplicação Desktop como intuito de auxiliar\nos resposáveis de cada setor a repor produtos em falta.");
            }
        });

        menuSobreDesenvolvedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostraMensagem("O Desenvolvedor", "A aplicação Escudeiro das Compras foi projetada pelo desenvolvedor Andrews Duarte\ne desenvolvida durante as aulas de Tópicos Especiais I, regidas pelo professor Gilberto Vieira.");
            }
        });
        menuSobreBancoDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostraMensagem("Como Encontrar o Banco de Dados", "Para selecionar a pasta correta na hora de escolher o Banco de Dados, você deve entrar\nna pasta do programa e procurar pela pasta \"db\",  dentro desta pasta haverá a pasta \"banco\"\na qual deverá ser selecionada.");
            }
        });
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = this.getClass().getClassLoader().getResource("imagens/shield.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        iniciaLogin();
        loginJanela.toFront();
    }

    public static void main(String[] args) {
        main = new JanelaPrincipal();
        main.setVisible(true);
    }

}
