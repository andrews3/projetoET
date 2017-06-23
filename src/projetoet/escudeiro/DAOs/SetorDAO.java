package projetoet.escudeiro.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projetoet.escudeiro.modelo.Setor;
import projetoet.escudeiro.utilitarios.EscudeiroException;
import projetoet.escudeiro.utilitarios.Repositorio;

public class SetorDAO {

    public SetorDAO() {
    }

    public void insert(Setor setor) throws EscudeiroException {
        String sql = "insert into setores (nomesetor) "
                + "values (?)";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, setor.getNomeSetor());
            ps.execute();
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
    }

    public List getSetores() throws EscudeiroException {
        Setor setor;
        List<Setor> setores = new ArrayList<>();
        String sql = "select * from setores";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    setor = new Setor();
                    setor.setId(rs.getInt("id"));
                    setor.setNomeSetor(rs.getString("nomesetor"));
                    setores.add(setor);
                }
            }
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
        return setores;
    }

    public void remover(Integer id) throws EscudeiroException {
        String sql = "delete from setores where id=?";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
    }

    public void update(Setor setor) throws EscudeiroException {
        String sql = "update setores set nomesetor=?"
                + " where id=?";
        try (Connection c = Repositorio.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, setor.getNomeSetor());
            ps.setInt(2, setor.getId());
            ps.execute();
        } catch (Exception e) {
            throw new EscudeiroException(e);
        }
    }
}
