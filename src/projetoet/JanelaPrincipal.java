package projetoet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import projetoet.util.ECLogger;
import projetoet.util.Produto;
import projetoet.util.Setor;
import projetoet.util.UsuarioRepositorio;
import projetoet.util.Usuarios;

/**
 *
 * @author Andrews-PC
 */
public class JanelaPrincipal extends JFrame {

    //// Professor, para testar o software altere o parteCaminhoBanco////
    public static String parteCaminhoBanco = "/Users/Andrews-PC/Documents/NetBeansProjects/projetoET/db/banco";
    public static String caminhoBanco = "jdbc:hsqldb:file:";
    ///////////

    Container c;
    JMenuBar barraMenu;
    JMenuItem menuCadastroProduto, menuCadastroSetor, menuCadastroSair;
    JMenuItem menuExibirProdutos;
    JMenuItem menuSobrePrograma, menuSobreDesenvolvedor, menuSobreBancoDados;
    JMenuItem menuOpcoesBanco;
    JMenu menuExibir, menuSobre, menuCadastro;
    JButton botaoCadastrar, botaoExibir, botaoSair;
    static JanelaPrincipal main;
    JDesktopPane mJdp;
    LoginJanela loginJanela;
    boolean bJanelaCadastroProduto, bJanelaCadastroSetor, bJanelaSelecaoBanco, bJanelaExibirProdutos;
    CadastroProdutoJanela novaJanelaCadastroProduto;
    CadastroSetorJanela novaJanelaCadastroSetor;
    SelecaoBancoJanela novaJanelaSelecaoBanco;
    ExibirProdutoJanela novaJanelaExibirProduto;

    private void mostraMensagemErro(String s, String i) {
        JOptionPane.showMessageDialog(null, s, i, JOptionPane.ERROR_MESSAGE);
    }

    private void visibilidadeComponentes(boolean b) {
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

    private void iniciaCadastroProdutos() throws IOException {
        if (!bJanelaCadastroProduto) {
            novaJanelaCadastroProduto = new CadastroProdutoJanela();
            novaJanelaCadastroProduto.setVisible(true);
            mJdp.add(novaJanelaCadastroProduto);
            ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " abriu a janela de cadastro de produtos;");
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
                        ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " fechou a janela de cadastro de produtos;");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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

            novaJanelaCadastroProduto.adicionarBotao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (novaJanelaCadastroProduto.codTf.getText().length() >= 1) {
                        if (novaJanelaCadastroProduto.verificaCodigo()) {
                            String nomeProduto = novaJanelaCadastroProduto.nomeTf.getText();
                            int codProduto = Integer.parseInt(novaJanelaCadastroProduto.codTf.getText());
                            String setorProduto = novaJanelaCadastroProduto.setorCb.getSelectedItem().toString();
                            Produto produto = new Produto(nomeProduto, codProduto, setorProduto);
                            try {
                                novaJanelaCadastroProduto.insereProdutos(produto);
                                ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " cadastrou o produto " + produto.getNome()
                                        + " no setor " + produto.getSetor() + ";");
                            } catch (SQLException ex) {
                                Logger.getLogger(CadastroProdutoJanela.class.getName()).log(Level.SEVERE, null, ex);

                            } catch (IOException ex) {
                                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            mostraMensagemErro("O Código não pode possuir letras, somente números", "Caracteres Inválidos");
                        }
                    } else {
                        mostraMensagemErro("Não é possível cadastrar um produto sem um código", "Código Inválido");
                    }
                }
            });
        }

    }

    private void iniciaCadastroSetores() throws SQLException, IOException {
        if (!bJanelaCadastroSetor) {
            novaJanelaCadastroSetor = new CadastroSetorJanela();
            novaJanelaCadastroSetor.setVisible(true);
            ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " abriu a janela de cadastro de setores;");
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
                    try {
                        ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " fechou a janela de cadastro de setores;");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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

            novaJanelaCadastroSetor.botaoSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    boolean bCadastro = novaJanelaCadastroSetor.cadastraSetor();
                    if (bCadastro) {
                        try {
                            novaJanelaCadastroSetor.loadSetores();
                        } catch (SQLException ex) {
                            Logger.getLogger(CadastroSetorJanela.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        novaJanelaCadastroSetor.model.removeAll();
                        novaJanelaCadastroSetor.populaTabela();
                    }
                }
            });

            novaJanelaCadastroSetor.botaoDeletar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    UIManager.put("OptionPane.noButtonText", "Não");
                    UIManager.put("OptionPane.yesButtonText", "Sim");
                    if (novaJanelaCadastroSetor.isRowSelected()) {
                        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o setor?", "Deletar Setor", JOptionPane.YES_NO_OPTION);
                        if (op == JOptionPane.YES_OPTION) {
                            int linha = novaJanelaCadastroSetor.tabelaSetores.getSelectedRow();
                            Setor s = novaJanelaCadastroSetor.model.get(linha);
                            novaJanelaCadastroSetor.removeSetorBanco(s.getId());
                            try {
                                ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " deletou o setor " + s.getNomeSetor() + ";");
                            } catch (IOException ex) {
                                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            novaJanelaCadastroSetor.model.removeRow(linha);
                        }
                    } else {
                        mostraMensagemErro("Verifique se há um setor selecionado e se há somente um setor selecionado", "Não foi possível deletar");
                    }

                }
            });

        }
    }

    private void iniciaSelecaoBanco() throws IOException {
        if (!bJanelaSelecaoBanco) {

            novaJanelaSelecaoBanco = new SelecaoBancoJanela();
            novaJanelaSelecaoBanco.setVisible(true);
            mJdp.add(novaJanelaSelecaoBanco);
            bJanelaSelecaoBanco = true;
            ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " abriu a janela de alteração do caminho do banco de dados;");
            try {
                novaJanelaSelecaoBanco.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
            novaJanelaSelecaoBanco.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaSelecaoBanco = false;
                    try {
                        ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " fechou a janela de alteração do caminho do banco de dados");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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

            novaJanelaSelecaoBanco.botaoProcuraCaminho.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        JFileChooser fc = new JFileChooser();
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fc.showOpenDialog(c);
                        novaJanelaSelecaoBanco.caminhoArquivo = fc.getSelectedFile().getAbsolutePath();
                        novaJanelaSelecaoBanco.caminhoBancoTf.setText(novaJanelaSelecaoBanco.caminhoArquivo);
                    } catch (NullPointerException ie) {
                        try {
                            ECLogger.insereLogException(ie);
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            novaJanelaSelecaoBanco.botaoSalvarCaminho.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar esse caminho para o banco de dados? Caso "
                            + "o caminho esteja errado,\ntoda a aplicação deixará de funcionar.", "Salvar Banco de Dados", JOptionPane.YES_NO_OPTION);
                    if (op == JOptionPane.YES_OPTION) {
                        if (novaJanelaSelecaoBanco.caminhoArquivo.length() > 2) {
                            if (novaJanelaSelecaoBanco.caminhoArquivo.charAt(0) == 'C') {
                                int maximoArray = novaJanelaSelecaoBanco.caminhoArquivo.length() - 2;
                                novaJanelaSelecaoBanco.caminhoArquivoFinal = new char[maximoArray];
                                int y = 0;
                                for (int i = 2; i < novaJanelaSelecaoBanco.caminhoArquivo.length(); i++) {
                                    if (novaJanelaSelecaoBanco.caminhoArquivo.charAt(i) == '\\') {
                                        novaJanelaSelecaoBanco.caminhoArquivoFinal[y] = '/';
                                    } else {
                                        novaJanelaSelecaoBanco.caminhoArquivoFinal[y] = novaJanelaSelecaoBanco.caminhoArquivo.charAt(i);
                                    }
                                    y++;
                                }

                                String l = "";
                                for (int i = 0; i < novaJanelaSelecaoBanco.caminhoArquivoFinal.length; i++) {
                                    l = l + novaJanelaSelecaoBanco.caminhoArquivoFinal[i];
                                }
                                parteCaminhoBanco = l + "/";
                                try {
                                    ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " alterou o caminho do banco de dados;");
                                } catch (IOException ex) {
                                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                bJanelaSelecaoBanco = false;
                                novaJanelaSelecaoBanco.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(null, "Não é possível selecionar o banco de dados de fora da raiz C:", "Caminho Inválido", JOptionPane.ERROR_MESSAGE);
                                try {
                                    ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " tentou alterar o caminho do banco de dados fora da raiz C:;");
                                } catch (IOException ex) {
                                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Caminho do Banco não alterado.", "Caminho Inalterado", JOptionPane.ERROR_MESSAGE);
                            bJanelaSelecaoBanco = false;
                            try {
                                ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " tentou salvar o caminho do banco de dados com tamanho menor que 2;");
                            } catch (IOException ex) {
                                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            novaJanelaSelecaoBanco.setVisible(false);
                        }
                    }
                }
            });
        }
    }

    private void iniciaExibirProdutos() throws IOException {
        if (!bJanelaExibirProdutos) {
            novaJanelaExibirProduto = new ExibirProdutoJanela();
            novaJanelaExibirProduto.setVisible(true);
            mJdp.add(novaJanelaExibirProduto);
            bJanelaExibirProdutos = true;
            ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " abriu a janela de exibir produtos;");
            try {
                novaJanelaExibirProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                 ECLogger.insereLogException(e);
            }
            novaJanelaExibirProduto.addInternalFrameListener(new InternalFrameListener() {

                @Override
                public void internalFrameClosing(InternalFrameEvent ife) {
                    bJanelaExibirProdutos = false;
                    try {
                        ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " fechou a janela de exibir produtos;");
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);                       
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

            novaJanelaExibirProduto.setoresLista.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent arg0) {
                    if (!arg0.getValueIsAdjusting()) {
                        if (novaJanelaExibirProduto.modeloTabela.getRowCount() > 0) {
                            novaJanelaExibirProduto.modeloTabela.limpaTabela();
                        }
                        novaJanelaExibirProduto.currentSetor = novaJanelaExibirProduto.setoresLista.getSelectedValue();
                        try {
                            ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " selecionou o setor " + novaJanelaExibirProduto.currentSetor + ";");
                        } catch (IOException ex) {
                            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        novaJanelaExibirProduto.loadProdutos(novaJanelaExibirProduto.setoresLista.getSelectedValue());
                    }
                }
            });

            novaJanelaExibirProduto.botaoExcluir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (novaJanelaExibirProduto.isRowSelected()) {
                        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o produto?", "Deletar Produto", JOptionPane.YES_NO_OPTION);
                        if (op == JOptionPane.YES_OPTION) {
                            int linha = novaJanelaExibirProduto.tabelaProdutos.getSelectedRow();
                            Produto p = novaJanelaExibirProduto.modeloTabela.get(linha);
                            try {
                                ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " deletou o produto " + p.getNome() + ";");
                            } catch (IOException ex) {
                                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            novaJanelaExibirProduto.removeProdutoBanco(p.getId());
                            novaJanelaExibirProduto.modeloTabela.removeRow(linha);
                        }
                    } else {
                        mostraMensagemErro("Verifique se há um produto selecionado e se há somente um produto selecionado", "Não foi Possível Deletar");
                    }
                }
            });

            novaJanelaExibirProduto.botaoLimparTabela.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (novaJanelaExibirProduto.currentSetor != null) {

                        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja limpar a tabela do setor " + novaJanelaExibirProduto.currentSetor + "?",
                                "Limpar Lista de Produtos", JOptionPane.YES_NO_OPTION);
                        if (op == JOptionPane.YES_OPTION) {
                            novaJanelaExibirProduto.modeloTabela.limpaTabela();
                            try {
                                ECLogger.insereLog("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " limpou a tabela de produtos;");
                            } catch (IOException ex) {
                                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            novaJanelaExibirProduto.limpaSetor(novaJanelaExibirProduto.currentSetor);
                        }
                    } else {
                        mostraMensagemErro("Nenhum setor foi selecionado, selecione um setor.", "Nenhum Setor Selecionado");
                    }
                }
            });

        }
    }

    private void iniciaLogin() throws IOException {
        loginJanela = new LoginJanela();
        loginJanela.setVisible(true);
        loginJanela.toFront();
        mJdp.add(loginJanela);
        visibilidadeComponentes(false);

        loginJanela.entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (loginJanela.usuarioTf.getText().length() > 0 && loginJanela.senhaTf.getText().length() > 0) {
                    Usuarios user = new Usuarios(loginJanela.usuarioTf.getText(), loginJanela.senhaTf.getText());
                    try {
                        if (loginJanela.verificaUser(user)) {
                            loginJanela.setVisible(false);
                            visibilidadeComponentes(true);
                        } else {
                            mostraMensagemErro("Usuário ou senha inválidos", "Erro ao conectar-se");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    mostraMensagemErro("Não é possível conectar-se sem um usuário e uma senha", "Conexão inválida");
                }
            }
        });

    }

    private void finalizaAplicacao() throws IOException {
        if (UsuarioRepositorio.isConec()) {
            ECLogger.insereLog(("Usuário " + UsuarioRepositorio.getUsuarioConec().getNome() + " desconectou-se;"), true);
        }
        UsuarioRepositorio.setConec(false);
        this.dispose();
    }

    private void sair() throws IOException {
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            finalizaAplicacao();
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

    public JanelaPrincipal() throws IOException {
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
                try {
                    iniciaCadastroProdutos();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        botaoExibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    iniciaExibirProdutos();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    sair();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        menuCadastroProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    iniciaCadastroProdutos();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuCadastroSetor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    iniciaCadastroSetores();
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    try {
                        ECLogger.insereLogException(ex);
                    } catch (IOException ex1) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        });
        menuCadastroSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    sair();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuExibirProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    iniciaExibirProdutos();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuOpcoesBanco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    iniciaSelecaoBanco();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        menuSobrePrograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostraMensagem("O Escudeiro das Compras", "O Escudeiro das Compras é uma aplicação Desktop como intuito de auxiliar\nos "
                        + "resposáveis de cada setor a repor produtos em falta.");
            }
        });

        menuSobreDesenvolvedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostraMensagem("O Desenvolvedor", "A aplicação Escudeiro das Compras foi projetada pelo desenvolvedor "
                        + "Andrews Duarte\ne desenvolvida durante as aulas de Tópicos Especiais I, regidas pelo professor Gilberto Vieira.");
            }
        });
        menuSobreBancoDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostraMensagem("Como Encontrar o Banco de Dados", "Para selecionar a pasta correta na hora de escolher o Banco de Dados, "
                        + "você deve entrar\nna pasta do programa e procurar pela pasta \"db\",  dentro desta pasta haverá a pasta \"banco\"\na qual deverá ser selecionada.");
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
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    finalizaAplicacao();
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        main = new JanelaPrincipal();
        main.setVisible(true);
    }

}
