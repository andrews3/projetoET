package projetoet.escudeiro.janelas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import projetoet.escudeiro.eventos.BotaoInicialListener;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.ECLogger;
import projetoet.escudeiro.utilitarios.EscudeiroException;
import projetoet.escudeiro.utilitarios.Repositorio;

/**
 *
 * @author Andrews-PC
 */
public class JanelaPrincipal extends JFrame {

    private BotaoInicialListener listener = new BotaoInicialListener(this);

    Container c;
    JMenuBar barraMenu;
    JMenuItem menuCadastroProduto, menuCadastroSetor, menuCadastroUsuario, menuCadastroSair;
    JMenuItem menuExibirProdutos;
    JMenuItem menuSobrePrograma, menuSobreDesenvolvedor;
    JMenuItem menuOpcoesBanco;
    JMenu menuSobre;
    JButton botaoSair;
    JDesktopPane mJdp;
    LoginJanela loginJanela;
    CadastroUsuarioJanela novaJanelaCadastroUsuario;
    CadastroProdutoJanela novaJanelaCadastroProduto;
    CadastroSetorJanela novaJanelaCadastroSetor;
    ExibirProdutoJanela novaJanelaExibirProduto;
    boolean bJanelaCadastroProduto, bJanelaCadastroSetor, bJanelaCadastroUsuario, bJanelaExibirProdutos;
    static JButton botaoCadastrar, botaoExibir;
    static JMenu menuExibir, menuCadastro;
    static JanelaPrincipal main;

    public static void visibilidadeComponentes(boolean b) {
        menuCadastro.setVisible(b);
        menuExibir.setVisible(b);
        botaoCadastrar.setVisible(b);
        botaoExibir.setVisible(b);
    }

    private void insereComponentes() {
        String copyright = "© Escudeiro das Compras 2017 - Todos os direitos reservados";
        barraMenu = new JMenuBar();

        menuCadastro = new JMenu(" Cadastro");
        menuExibir = new JMenu(" Exibir");
        menuSobre = new JMenu(" Sobre");

        menuCadastroProduto = new JMenuItem("Cadastrar Produto");
        menuCadastroProduto.addActionListener(listener);
        menuCadastroProduto.setActionCommand("cadastroProduto");

        menuCadastroSetor = new JMenuItem("Cadastrar Setor");
        menuCadastroSetor.addActionListener(listener);
        menuCadastroSetor.setActionCommand("cadastroSetor");

        menuCadastroUsuario = new JMenuItem("Cadastrar Usuário");
        menuCadastroUsuario.addActionListener(listener);
        menuCadastroUsuario.setActionCommand("cadastroUsuario");

        menuCadastroSair = new JMenuItem("Sair");
        menuCadastroSair.addActionListener(listener);
        menuCadastroSair.setActionCommand("sair");

        menuCadastro.add(menuCadastroProduto);
        menuCadastro.add(menuCadastroSetor);
        menuCadastro.add(menuCadastroUsuario);
        menuCadastro.addSeparator();
        menuCadastro.add(menuCadastroSair);
        barraMenu.add(menuCadastro);

        menuExibirProdutos = new JMenuItem("Listar Produtos");
        menuExibirProdutos.addActionListener(listener);
        menuExibirProdutos.setActionCommand("listarProdutos");

        menuExibir.add(menuExibirProdutos);
        barraMenu.add(menuExibir);

        menuSobrePrograma = new JMenuItem("O Programa");
        menuSobrePrograma.addActionListener(listener);
        menuSobrePrograma.setActionCommand("sobrePrograma");

        menuSobreDesenvolvedor = new JMenuItem("O Desenvolvedor");
        menuSobreDesenvolvedor.addActionListener(listener);
        menuSobreDesenvolvedor.setActionCommand("sobreDesenvolvedor");

        menuSobre.add(menuSobrePrograma);
        menuSobre.add(menuSobreDesenvolvedor);
        barraMenu.add(menuSobre);

        this.setJMenuBar(barraMenu);

        botaoCadastrar = new JButton("Cadastrar Produto em Falta");
        botaoCadastrar.setFont(new Font("SansSerif", Font.BOLD, 15));
        botaoCadastrar.setBounds(20, 20, 250, 80);
        botaoCadastrar.addActionListener(listener);
        botaoCadastrar.setActionCommand("cadastroProduto");
        this.add(botaoCadastrar);

        botaoExibir = new JButton("Exibir Produtos em Falta");
        botaoExibir.setBounds(20, 110, 250, 80);
        botaoExibir.setFont(new Font("SansSerif", Font.BOLD, 15));
        botaoExibir.addActionListener(listener);
        botaoExibir.setActionCommand("listarProdutos");
        this.add(botaoExibir);

        botaoSair = new JButton("Sair");
        botaoSair.setBounds(20, 590, 250, 50);
        botaoSair.addActionListener(listener);
        botaoSair.setActionCommand("sair");
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

        URL imgUrl = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/escudo.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        JLabel label = new JLabel(icon);
        label.setBounds(500, 60, 380, 500);
        this.add(label);

    }

    public void iniciaCadastroProdutos(List<Setor> setores) throws EscudeiroException {
        if (!bJanelaCadastroProduto) {
            novaJanelaCadastroProduto = new CadastroProdutoJanela(setores);
            novaJanelaCadastroProduto.setVisible(true);
            mJdp.add(novaJanelaCadastroProduto);
            try {
                ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " abriu a janela de cadastro de produtos;");
            } catch (IOException ex) {
                throw new EscudeiroException(ex);
            }
            bJanelaCadastroProduto = true;
            try {
                novaJanelaCadastroProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
            novaJanelaCadastroProduto.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaCadastroProduto = false;
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " fechou a janela de cadastro de produtos;");
                    } catch (IOException ex) {
                        try {
                            throw new EscudeiroException(ex);
                        } catch (EscudeiroException ex1) {
                            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
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

    public void iniciaCadastroSetores(List<Setor> setores) throws EscudeiroException {
        if (!bJanelaCadastroSetor) {
            novaJanelaCadastroSetor = new CadastroSetorJanela();
            novaJanelaCadastroSetor.setVisible(true);
            novaJanelaCadastroSetor.setores = setores;
            novaJanelaCadastroSetor.populaTabela();
            try {
                ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " abriu a janela de cadastro de setores;");
            } catch (IOException ex) {
                throw new EscudeiroException(ex);
            }
            mJdp.add(novaJanelaCadastroSetor);
            bJanelaCadastroSetor = true;
            try {
                novaJanelaCadastroSetor.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                throw new EscudeiroException(e);
            }
            novaJanelaCadastroSetor.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaCadastroSetor = false;
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " fechou a janela de cadastro de setores;");
                    } catch (IOException ex) {
                        try {
                            throw new EscudeiroException(ex);
                        } catch (EscudeiroException ex1) {
                            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
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

    public void iniciaCadastroUsuario() throws EscudeiroException {
        if (!bJanelaCadastroUsuario) {
            novaJanelaCadastroUsuario = new CadastroUsuarioJanela();
            novaJanelaCadastroUsuario.setVisible(true);
            try {
                ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " abriu a janela de cadastro de usuarios;");
            } catch (IOException ex) {
                throw new EscudeiroException(ex);
            }
            mJdp.add(novaJanelaCadastroUsuario);
            bJanelaCadastroUsuario = true;
            try {
                novaJanelaCadastroUsuario.setSelected(true);
            } catch (PropertyVetoException ex) {
                throw new EscudeiroException(ex);
            }

            novaJanelaCadastroUsuario.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaCadastroUsuario = false;
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " fechou a janela de cadastro de usuario;");
                    } catch (IOException ex) {
                        try {
                            throw new EscudeiroException(ex);
                        } catch (EscudeiroException ex1) {
                            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
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

    public void iniciaExibirProdutos(List<Setor> setores) throws EscudeiroException {
        if (!bJanelaExibirProdutos) {
            novaJanelaExibirProduto = new ExibirProdutoJanela(setores);
            novaJanelaExibirProduto.setVisible(true);
            mJdp.add(novaJanelaExibirProduto);
            bJanelaExibirProdutos = true;
            try {
                ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " abriu a janela de exibir produtos;");
            } catch (IOException ex) {
                throw new EscudeiroException(ex);
            }
            try {
                novaJanelaExibirProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                throw new EscudeiroException(e);
            }
            novaJanelaExibirProduto.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaExibirProdutos = false;
                    try {
                        ECLogger.insereLog("Usuário " + Repositorio.getUsuarioConec().getNome() + " fechou a janela de exibir produtos;");
                    } catch (IOException ex) {
                        try {
                            throw new EscudeiroException(ex);
                        } catch (EscudeiroException ex1) {
                            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
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

    private void iniciaLogin() throws EscudeiroException {
        loginJanela = new LoginJanela();
        loginJanela.setVisible(true);
        loginJanela.toFront();
        mJdp.add(loginJanela);
        visibilidadeComponentes(false);

    }

    public void finalizaAplicacao() throws EscudeiroException {
        if (Repositorio.isConec()) {
            try {
                ECLogger.insereLog(("Usuário " + Repositorio.getUsuarioConec().getNome() + " desconectou-se;"), true);
            } catch (IOException ex) {
                throw new EscudeiroException(ex);
            }
        }
        Repositorio.setConec(false);
        this.dispose();
    }

    public void sair() throws EscudeiroException {
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            finalizaAplicacao();
        }
    }

    public JanelaPrincipal() throws EscudeiroException {
        super("Escudeiro das Compras");
        mJdp = new JDesktopPane();
        setContentPane(mJdp);
        mJdp.putClientProperty("JDesktopPane.dragMode", "outline");
        insereComponentes();
        Color cor = new Color(193, 205, 205);
        mJdp.setBackground(cor);

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = this.getClass().getClassLoader().getResource("projetoet/escudeiro/imagens/shield.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        iniciaLogin();
        loginJanela.toFront();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    finalizaAplicacao();
                } catch (EscudeiroException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public static void main(String[] args) throws EscudeiroException {
        main = new JanelaPrincipal();
        main.setVisible(true);
    }
}
