package sam.model.dao;

import sam.model.domain.AtividadeReferencia;
import sam.model.common.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtividadeReferenciaDAO {

    public void inserir(AtividadeReferencia ref) throws SQLException {

        String sql = """
            INSERT INTO atividade_referencia (atividade_id, tipo_entidade, entidade_id)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, ref.getAtividadeId());
            ps.setString(2, ref.getTipoEntidade());
            ps.setLong(3, ref.getEntidadeId());

            ps.executeUpdate();
        }
    }

    public List<AtividadeReferencia> listarPorAtividade(Long atividadeId) throws SQLException {

        List<AtividadeReferencia> lista = new ArrayList<>();

        String sql = """
            SELECT id, atividade_id, tipo_entidade, entidade_id
            FROM atividade_referencia
            WHERE atividade_id = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, atividadeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AtividadeReferencia ref = new AtividadeReferencia();
                    ref.setId(rs.getLong("id"));
                    ref.setAtividadeId(rs.getLong("atividade_id"));
                    ref.setTipoEntidade(rs.getString("tipo_entidade"));
                    ref.setEntidadeId(rs.getLong("entidade_id"));

                    lista.add(ref);
                }
            }
        }

        return lista;
    }
}
