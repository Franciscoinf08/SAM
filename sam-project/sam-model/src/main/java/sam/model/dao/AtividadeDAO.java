package sam.model.dao;

import sam.model.domain.Atividade;
import sam.model.common.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtividadeDAO {

    public Long inserir(Atividade atividade) throws SQLException {

        String sql = """
            INSERT INTO atividade (tipo, descricao, usuario_executor_id, data_hora)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, atividade.getTipo());
            ps.setString(2, atividade.getDescricao());
            ps.setLong(3, atividade.getUsuarioExecutorId());
            ps.setTimestamp(4, Timestamp.valueOf(atividade.getDataHora()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        }

        return null;
    }

    public List<Atividade> listarTodas() throws SQLException {

        List<Atividade> lista = new ArrayList<>();

        String sql = """
            SELECT id, tipo, descricao, usuario_executor_id, data_hora
            FROM atividade
            ORDER BY data_hora DESC
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Atividade atividade = new Atividade();
                atividade.setId(rs.getLong("id"));
                atividade.setTipo(rs.getString("tipo"));
                atividade.setDescricao(rs.getString("descricao"));
                atividade.setUsuarioExecutorId(rs.getLong("usuario_executor_id"));
                atividade.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());

                lista.add(atividade);
            }
        }

        return lista;
    }

    public Atividade buscarPorId(Long id) throws SQLException {

        String sql = """
            SELECT id, tipo, descricao, usuario_executor_id, data_hora
            FROM atividade
            WHERE id = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Atividade atividade = new Atividade();
                    atividade.setId(rs.getLong("id"));
                    atividade.setTipo(rs.getString("tipo"));
                    atividade.setDescricao(rs.getString("descricao"));
                    atividade.setUsuarioExecutorId(rs.getLong("usuario_executor_id"));
                    atividade.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());

                    return atividade;
                }
            }
        }

        return null;
    }
}
