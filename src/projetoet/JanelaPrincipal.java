package projetoet;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Andrews-PC
 */
public class JanelaPrincipal extends JFrame {

    Container c;
    JMenuItem menuCadastroProduto, menuCadastroSetor, menuCadastroSair;
    JMenuItem menuExibirProdutos;
    JMenuItem menuSobrePrograma, menuSobreDesenvolvedor;
    JButton botaoCadastrar, botaoExibir, botaoSair;
    static JanelaPrincipal main;


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
        botaoCadastrar.setBounds(20, 20, 250, 80);
        c.add(botaoCadastrar);

        botaoExibir = new JButton("Exibir Produtos em Falta");
        botaoExibir.setBounds(20, 110, 250, 80);
        c.add(botaoExibir);

        botaoSair = new JButton("Sair");
        botaoSair.setBounds(20, 600, 250, 50);
        c.add(botaoSair);

        JLabel encontradoLabel;
        TitledBorder encontradoBorder;
        encontradoLabel = new JLabel();
        encontradoLabel.setBounds(0, 0, 290, 662);
        encontradoBorder = new TitledBorder("");
        encontradoLabel.setBorder(encontradoBorder);
        c.add(encontradoLabel);

        JLabel copyrightLabel = new JLabel(copyright);
        copyrightLabel.setBounds(480, 640, 400, 25);
        c.add(copyrightLabel);

        ImageIcon icon = new ImageIcon("src/imagens/escudo.png");

        JLabel label = new JLabel(icon);
        label.setBounds(500, 60, 380, 500);
        c.add(label);

    }

    private void iniciaCadastroProdutos() {
        CadastroProdutoJanela janelaCadastro = new CadastroProdutoJanela();
        janelaCadastro.setVisible(true);
        janelaCadastro.setBackground(Color.LIGHT_GRAY);
        
        main.add(janelaCadastro);
    }

    public JanelaPrincipal() {
        super("Escudeiro das Compras");
        c = this.getContentPane();
        insereComponentes();

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciaCadastroProdutos();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.setLayout(null);
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
