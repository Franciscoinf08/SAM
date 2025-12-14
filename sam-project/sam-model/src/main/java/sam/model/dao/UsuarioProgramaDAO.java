package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.UsuarioPrograma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioProgramaDAO{
    private Connection conexao = null;

    public UsuarioProgramaDAO() {
        this.conexao = Conexao.getConnection();
    }

    public void inserir(UsuarioPrograma entidade){
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

    public void excluir(Integer idUsuario,  Integer idPrograma) {
        String sql = "delete from usuario_programa where usuario_id = ? and programa_id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idPrograma);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void excluirTodos(Integer idUsuario) {
        String sql = "delete from usuario_programa where usuario_id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}