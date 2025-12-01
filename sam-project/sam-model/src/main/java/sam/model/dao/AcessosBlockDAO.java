package sam.model.dao;

import sam.model.common.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessosBlockDAO {

    private final Connection conexao;

    public AcessosBlockDAO() {
        this.conexao = Conexao.getConnection();
        
        try {
            conexao.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bloquear(String recurso, String usuario) throws SQLException {
        String sql = "INSERT INTO acessos_block(usuario, recurso) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, recurso);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao bloquear acesso", e);
        }
    }

    public void ativar(String recurso, String usuario) throws SQLException {
        String sql = "DELETE FROM acessos_block WHERE usuario = ? AND recurso = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, recurso);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao ativar acesso", e);
        }
    }

     public boolean check(String recurso, String usuario) throws SQLException {
        String sql = "SELECT 1 FROM acessos_block WHERE usuario = ? AND recurso = ? LIMIT 1";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, recurso);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao checar acesso", e);
        }
    }
}
