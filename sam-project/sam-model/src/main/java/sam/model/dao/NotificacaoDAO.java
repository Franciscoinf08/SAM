package sam.model.dao;

import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Notificacao;
import sam.model.domain.util.AlcanceNotificacao;
import sam.model.domain.util.TipoNotificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {

    private static NotificacaoDAO instance;

    public static NotificacaoDAO getInstance() {
        if (instance == null) {
            instance = new NotificacaoDAO();
        }
        return instance;
    }
    private Connection conexao = null;
    public NotificacaoDAO() {
        this.conexao = Conexao.getConnection();
    }

    public void inserir(Notificacao notificacao) throws PersistenciaException {
        String sql = """
            INSERT INTO notificacao 
            (titulo, mensagem, data_do_envio, tipo, alcance, lida, destinatario_id)
            VALUES (?, ?, NOW(), ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, notificacao.getTitulo());
            stmt.setString(2, notificacao.getMensagem());
            stmt.setString(3, notificacao.getTipo().name());
            stmt.setString(4, notificacao.getAlcance().name());
            stmt.setBoolean(5, notificacao.getLida());
            stmt.setLong(6, notificacao.getDestinatario().getId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    notificacao.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao inserir notificação: " + e.getMessage());
        }
    }

    public boolean marcarComoLida(long idNotificacao, long idUsuario) throws PersistenciaException {
        String sql = "UPDATE notificacao SET lida = TRUE WHERE id = ? AND destinatario_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, idNotificacao);
            stmt.setLong(2, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao marcar como lida: " + e.getMessage());
        }
    }

    public boolean deletar(long idNotificacao, long idUsuario) throws PersistenciaException {
        String sql = "DELETE FROM notificacao WHERE id = ? AND destinatario_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, idNotificacao);
            stmt.setLong(2, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao deletar notificação: " + e.getMessage());
        }
    }



    public List<Notificacao> listarTodas() throws PersistenciaException {
        List<Notificacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM notificacao ORDER BY data_do_envio DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao listar notificações: " + e.getMessage());
        }

        return lista;
    }

    private Notificacao mapResultSet(ResultSet rs) throws SQLException {
        Notificacao n = new Notificacao();
        n.setId(rs.getLong("id"));
        n.setTitulo(rs.getString("titulo"));
        n.setMensagem(rs.getString("mensagem"));
        n.setDataDoEnvio(rs.getTimestamp("data_do_envio").toLocalDateTime());
        n.setTipo(TipoNotificacao.valueOf(rs.getString("tipo")));
        n.setAlcance(AlcanceNotificacao.valueOf(rs.getString("alcance")));
        n.setLida(rs.getBoolean("lida"));
        return n;
    }
}
