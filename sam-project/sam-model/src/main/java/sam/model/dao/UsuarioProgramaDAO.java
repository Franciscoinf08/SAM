package sam.model.dao;

import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.UsuarioPrograma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioProgramaDAO{
    private Connection conexao = null;

    public UsuarioProgramaDAO() {
        this.conexao = Conexao.getConnection();
    }

    public void associar(UsuarioPrograma entidade){
        String sql = "insert into usuario_programa(usuario_id, programa_id, saldo_milhas) values(?, ?, ?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, entidade.getIdUsuario());
            stmt.setInt(2, entidade.getIdPrograma());
            stmt.setDouble(3, entidade.getSaldoMilhas());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()) {
                    entidade.setIdUsuarioPrograma(rs.getInt(1));
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    public void atualizar(UsuarioPrograma entidade){
        String sql = "update usuario_programa set programa_id = ?, usuario_id = ?, saldo_milhas = ? where id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, entidade.getIdPrograma());
            stmt.setInt(2, entidade.getIdUsuario());
            stmt.setDouble(3, entidade.getSaldoMilhas());
            stmt.setDouble(4, entidade.getIdUsuarioPrograma());

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public UsuarioPrograma pesquisar(Integer chave) {
        String sql = "select * from usuario_programa where id = ?";
        UsuarioPrograma up = null;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, chave);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                up = new UsuarioPrograma();
                up.setIdUsuarioPrograma(rs.getInt("id"));
                up.setIdUsuario(rs.getInt("usuario_id"));
                up.setSaldoMilhas(rs.getDouble("saldo_milhas"));
                up.setIdPrograma(rs.getInt("programa_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return up;
    }

    public void desassociar(Integer id) {
        String sql = "delete from usuario_programa where id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public List<UsuarioPrograma> listarTodos() {
        List<UsuarioPrograma> lista = new ArrayList<>();
        UsuarioPrograma up;
        String sql = "SELECT * FROM usuario_programa";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                up = new UsuarioPrograma();
                up.setIdUsuarioPrograma(rs.getInt(1));
                up.setIdUsuario(rs.getInt(2));
                up.setSaldoMilhas(rs.getDouble(3));
                up.setIdPrograma(rs.getInt(4));
                lista.add(up);
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return lista;
    }
}
