/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projetoet.escudeiro.modelo.Usuarios;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrews-PC
 */
public class Repositorio {

    private static List<Setor> setores = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Usuarios> usuarios = new ArrayList<>();
    private static Usuarios usuarioConec;
    private static boolean conec = false;

    public static Connection getConnection() throws EscudeiroException {
        Connection c = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            mostraMensagem("Não foi possível conectar ao banco de dados");
            throw new EscudeiroException(ex);
        }
        try {
            c = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://127.0.0.1/data",
                    "sa",
                    "");
        } catch (SQLException ex) {
            mostraMensagem("Não foi possível conectar ao banco de dados");
            throw new EscudeiroException(ex);
        }
        return c;
    }

    public static void insereUsuario(Usuarios u) {
        usuarios.add(u);
    }

    public static boolean isUsuario(Usuarios u) {
        for (Usuarios us : usuarios) {
            if (us.getNome().equals(u.getNome()) && us.getSenha().equals(u.getSenha())) {
                return true;
            }
        }
        return false;
    }

    public static void setUsuarios(List<Usuarios> usuarios) {
        Repositorio.usuarios = usuarios;
    }

    public static Usuarios getUsuarioConec() {
        return usuarioConec;
    }

    public static void setUsuarioConec(Usuarios usuarioConec) {
        Repositorio.usuarioConec = usuarioConec;
    }

    public static boolean isConec() {
        return conec;
    }

    public static void setConec(boolean conec) {
        Repositorio.conec = conec;
    }

    public static boolean verificaCodigo(int c) {
        for (Produto p : produtos) {
            if (p.getId() == c) {
                return false;
            }
        }
        return true;
    }

    public static void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
