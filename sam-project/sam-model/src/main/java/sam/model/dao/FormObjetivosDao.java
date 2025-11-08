package sam.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sam.model.domain.FormObjetivos;
import sam.model.common.ConexaoDB;

public class FormObjetivosDao {

    public static boolean inserir(FormObjetivos form) {
        // SQL para inserção. Use '?' para evitar SQL Injection (PreparedStatement)
        String sql = "INSERT INTO form_objetivos ("
                + "nome, email, data_ultima_atualizacao, objetivos_gerais, objetivos_especificos, "
                + "destino_principal, num_pessoas, pref_companhia, orcamento_total, "
                + "nivel_detalhamento, req_especificos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 1. Configurar os parâmetros do PreparedStatement
            stmt.setString(1, form.getNome());
            stmt.setString(2, form.getEmail());

            // Converte LocalDate para java.sql.Date (pode ser nulo)
            if (form.getData() != null) {
                stmt.setDate(3, Date.valueOf(form.getData()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }

            stmt.setString(4, form.getObjetivosGerais());
            stmt.setString(5, form.getObjetivosEspecificos());
            stmt.setString(6, form.getDestPrincipal());

            if (form.getNumPessoas() != null) {
                stmt.setInt(7, form.getNumPessoas());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }

            stmt.setString(8, form.getPrefCompanhia());

            if (form.getOrcTotal() != null) {
                stmt.setFloat(9, form.getOrcTotal());
            } else {
                stmt.setNull(9, java.sql.Types.FLOAT);
            }

            stmt.setString(10, form.getNivelDetalhamento());
            stmt.setString(11, form.getReqEspecificos());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}