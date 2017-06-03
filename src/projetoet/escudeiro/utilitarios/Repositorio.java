/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.utilitarios;

import projetoet.escudeiro.modelo.Usuarios;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.modelo.Produto;
import java.util.ArrayList;
import java.util.List;

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
    private static int codAtual = 0;

    public static List<Setor> getSetores() {
        if (setores.size() < 1) {
            insereSetor(new Setor("Geral", codAtual));
            incrementaCodAtual();
        }
        return setores;
    }

    public static int getCodAtual() {
        return codAtual;
    }

    public static void incrementaCodAtual() {
        codAtual = codAtual + 1;
    }

    public static void setSetores(List<Setor> setores) {
        Repositorio.setores = setores;
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static List<Produto> getProdutos(String setor) {
        List<Produto> prods = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getSetor().equals(setor)) {
                prods.add(p);
            }
        }
        return prods;
    }

    public static void setProdutos(List<Produto> produtos) {
        Repositorio.produtos = produtos;
    }

    public static List<Usuarios> getUsuarios() {
        if (usuarios.size() < 1) {
            insereUsuario(new Usuarios("admin", "123"));
        }
        return usuarios;
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

    static public void insereProduto(Produto p) {
        produtos.add(p);
    }

    static public void removeProduto(Produto p) {
        produtos.remove(p);
    }

    static public void insereSetor(Setor s) {
        setores.add(s);
    }

    static public void removeSetor(Setor s) {
        setores.remove(s);
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

    public static void atualizaSetores(Setor set) {
        for (Setor s : setores) {
            if (s.getId() == set.getId()) {
                s.setNomeSetor(set.getNomeSetor());
            }
        }
    }

    public static void limpaProdutosSetor(String setor) {
        List<Produto> prods = new ArrayList<>();
        for (Produto p : produtos) {
            if (!p.getSetor().equals(setor)) {
                prods.add(p);
            }
        }
        produtos = prods;
    }

    public static boolean verificaCodigo(int c) {
        for (Produto p : produtos) {
            if (p.getId() == c) {
                return false;
            }
        }
        return true;
    }
}
