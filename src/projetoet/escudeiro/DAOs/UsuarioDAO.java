/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import projetoet.escudeiro.modelo.Usuarios;
import projetoet.escudeiro.utilitarios.EscudeiroException;
import projetoet.escudeiro.utilitarios.Repositorio;

/**
 *
 * @author Andrews-PC
 */
public class UsuarioDAO {

    public UsuarioDAO() {
    }

    public Usuarios isUser(Usuarios u) throws EscudeiroException {
        Usuarios usuario = null;

        String sql = "select * from usuario where senha=? and nome=?";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getSenha());
            ps.setString(2, u.getNome());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuarios();
                    usuario.setCodigo(rs.getInt("codigo"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                }
            }
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }

        return usuario;
    }
    
    public void insert(Usuarios u) throws EscudeiroException {
        String sql = "insert into usuario (nome,senha) "
                + "values (?,?)";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.execute();
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
    }
}
