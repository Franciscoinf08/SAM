package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Notificacao;
import sam.model.common.Conexao;
import sam.model.domain.util.AlcanceNotificacao;
import sam.model.domain.util.TipoNotificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {

    private static NotificacaoDAO instance;
    private Connection conexao;

    public static NotificacaoDAO getInstance() {
        if (instance == null) {
            instance = new NotificacaoDAO();
        }
        return instance;
    }

    private NotificacaoDAO() {
        conexao = Conexao.getConnection();
    }

    public void inserir(Notificacao n) throws PersistenciaException {
        String sql = """
            INSERT INTO notificacoes
            (titulo, mensagem, data_do_envio, tipo, alcance, lida, destinatario_id)
            VALUES (?, ?, NOW(), ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt =
                     conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, n.getTitulo());
            stmt.setString(2, n.getMensagem());
            stmt.setString(3, n.getTipo().name());
            stmt.setString(4, n.getAlcance().name());
            stmt.setBoolean(5, n.isLida());
            stmt.setLong(6, n.getDestinatario().getId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) n.setId(rs.getLong(1));

        } catch (Exception e) {
            throw new PersistenciaException("Erro ao inserir notificação: " + e.getMessage());
        }
    }

    public List<Notificacao> listarPorUsuario(long idUsuario) throws PersistenciaException {
        List<Notificacao> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM notificacoes
            WHERE destinatario_id = ?
            ORDER BY data_do_envio DESC
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            throw new PersistenciaException("Erro ao listar notificações: " + e.getMessage());
        }

        return lista;
    }

    public boolean marcarComoLida(long id, long idUsuario) throws PersistenciaException {
        String sql = "UPDATE notificacoes SET lida = 1 WHERE id = ? AND destinatario_id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.setLong(2, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            throw new PersistenciaException("Erro ao marcar como lida: " + e.getMessage());
        }
    }

    private Notificacao mapResultSet(ResultSet rs) throws Exception {
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
