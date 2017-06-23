/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.DAOs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projetoet.escudeiro.modelo.Produto;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.EscudeiroException;
import projetoet.escudeiro.utilitarios.Repositorio;

/**
 *
 * @author Andrews-PC
 */
public class ProdutoDAO {

    public ProdutoDAO() {
    }

    public boolean insert(Produto produto) throws EscudeiroException {
        String sql = "insert into produto (id, nome, id_setor)"
                + "values (?,?,?)";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, produto.getId());
            ps.setString(2, produto.getNome());
            ps.setInt(3, produto.getSetor().getId());
            ps.execute();
        } catch (SQLException e) {
            if (e.getErrorCode() == -104) {
                mostraMensagem("Este código já está cadastrado");
            }
            throw new EscudeiroException(e);
        } catch (NullPointerException e) {
            throw new EscudeiroException(e);
        }
        return true;
    }

    public List getProdutos(Setor setor) throws EscudeiroException {
        Produto produto;
        Setor set;
        List<Produto> produtos = new ArrayList<>();
        String sql = "select produto.nome, produto.id, produto.id_setor, setores.nomesetor from produto "
                + "join setores on produto.id_setor = setores.id and setores.id = ?";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, setor.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
//                    set = new Setor();
//                    set.setId(rs.getInt("id_setor"));
//                    set.setNomeSetor(rs.getString("nomesetor"));
                    produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setSetor(setor);
                    produtos.add(produto);
                }
            }
        } catch (Exception e) {
           throw new EscudeiroException(e);
        }
        return produtos;
    }

    public void removeProduto(int id) throws IOException, EscudeiroException {
        String sql = "delete from produto where id=?";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
    }
    
    public void removeProdutos(int idSetor) throws EscudeiroException {
        String sql = "delete from produto where id_setor=?";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSetor);
            ps.execute();
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
    }

    private void mostraMensagem(String m) {
        JOptionPane.showMessageDialog(null, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
