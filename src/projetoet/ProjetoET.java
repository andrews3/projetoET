package projetoet;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Andrews-PC
 */
public class ProjetoET extends JFrame {

    Container c;

    public ProjetoET() {
        super("Escudeiro das Compras");
        c = this.getContentPane();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        ProjetoET main = new ProjetoET();
        main.setVisible(true);
    }

}
