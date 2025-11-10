package sam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import sam.model.domain.FormObjetivos;
import sam.model.common.ConexaoDB;
import sam.model.domain.Usuario;

public class FormObjetivosDao {

    public static boolean inserir(FormObjetivos form) {
        String sql = "INSERT INTO `form_objetivos` ("
                + "id_usuario ,titulo_formulario,data_ultima_atualizacao, objetivos_gerais, objetivos_especificos, "
                + "destino_principal, num_pessoas, pref_companhia, orcamento_total, "
                + "nivel_detalhamento, req_especificos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, form.getId_usuario());
            stmt.setString(2, form.getTitulo());
            stmt.setObject(3, form.getData());

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
    // sam.model.dao.FormObjetivosDao.java

    public static List<FormObjetivos> buscarTodos(Usuario usuario) {

        String sql = "SELECT id, titulo_formulario, data_ultima_atualizacao FROM form_objetivos WHERE id_usuario = ? ORDER BY id DESC ";
        List<FormObjetivos> listaFormularios = new LinkedList<>();

        try (Connection conn = ConexaoDB.getConnection()) {
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setLong(1, usuario.getId());
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FormObjetivos form = new FormObjetivos();

                form.setId(rs.getInt("id"));

                form.setTitulo(rs.getString("titulo_formulario"));

                if (rs.getDate("data_ultima_atualizacao") != null) {
                    form.setData(rs.getDate("data_ultima_atualizacao").toLocalDate());
                }

                listaFormularios.add(form);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os formulários: " + e.getMessage());
            e.printStackTrace();
        }
        return listaFormularios;
    }
}