package sam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sam.model.common.Conexao;

/* RECEBE IDs E COLOCA NUMA TABELA (usuarios_block)*/

public class UsuariosBlockDAO {
    private final Connection conexao;
    private static UsuariosBlockDAO usuariosBlockDAO;

    static {
        UsuariosBlockDAO.usuariosBlockDAO = null;
    }

    private UsuariosBlockDAO() {
        this.conexao = Conexao.getConnection();
    }
    
    public static UsuariosBlockDAO getInstance() {
        if (usuariosBlockDAO == null)
            usuariosBlockDAO = new UsuariosBlockDAO();

        return usuariosBlockDAO;
    }
    
    public void bloquear(Long idUsuario, Long idBloqueado) throws SQLException {
        String sql = "INSERT INTO ususrios_block(idUsuario, idBloqueado) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idUsuario); // USUÁRIO QUE BLOQUEOU
            preparedStatement.setLong(2, idBloqueado); // USUÁRIO QUE FOI BLOQUEADO
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao bloquear user", e);
        }
    }

    public void desbloquear(Long idUsuario, Long idBloqueado) throws SQLException {
        String sql = "DELETE FROM ususrios_block WHERE usuario = ? AND recurso = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idUsuario);
            preparedStatement.setLong(2, idBloqueado);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao desbloquear user", e);
        }
    }

     public boolean check(Long idUsuario, Long idBloqueado) throws SQLException {
        String sql = "SELECT 1 FROM ususrios_block WHERE usuario = ? AND recurso = ? LIMIT 1";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idUsuario);
            preparedStatement.setLong(2, idBloqueado);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao checar bloqueio", e);
        }
    }
}
