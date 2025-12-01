package sam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import sam.model.domain.FormObjetivos;
import sam.model.domain.Usuario;
import sam.model.common.Conexao;

public class FormObjetivosDao {

    public static boolean inserir(FormObjetivos form) {
        String sql = "INSERT INTO `form_objetivos` ("
                + "id_usuario ,titulo_formulario,data_ultima_atualizacao, objetivos_gerais, objetivos_especificos, "
                + "destino_principal, num_pessoas, pref_companhia, orcamento_total, "
                + "nivel_detalhamento, req_especificos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
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

    public static List<FormObjetivos> buscarTodos(Usuario usuario) {

        String sql = "SELECT id, titulo_formulario, data_ultima_atualizacao FROM form_objetivos WHERE id_usuario = ? ORDER BY id DESC ";
        List<FormObjetivos> listaFormularios = new LinkedList<>();

        try (Connection conn = Conexao.getConnection()) {
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

    public static FormObjetivos buscarPorId(int idFormulario) {
        String sql = "SELECT * FROM form_objetivos WHERE id = ?";
        FormObjetivos form = null;

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFormulario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    form = new FormObjetivos();

                    form.setId(rs.getInt("id"));
                    form.setId_usuario(rs.getLong("id_usuario"));

                    form.setTitulo(rs.getString("titulo_formulario"));
                    if (rs.getDate("data_ultima_atualizacao") != null) {
                        form.setData(rs.getDate("data_ultima_atualizacao").toLocalDate());
                    }

                    form.setObjetivosGerais(rs.getString("objetivos_gerais"));
                    form.setObjetivosEspecificos(rs.getString("objetivos_especificos"));
                    form.setDestPrincipal(rs.getString("destino_principal"));
                    form.setNumPessoas(rs.getInt("num_pessoas"));
                    form.setPrefCompanhia(rs.getString("pref_companhia"));
                    form.setOrcTotal(rs.getFloat("orcamento_total"));
                    form.setNivelDetalhamento(rs.getString("nivel_detalhamento"));
                    form.setReqEspecificos(rs.getString("req_especificos"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar formulário por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return form;
    }

    public static boolean atualizar(FormObjetivos form) {
        String sql = "UPDATE `form_objetivos` SET "
                + "titulo_formulario = ?, objetivos_gerais = ?, objetivos_especificos = ?, "
                + "destino_principal = ?, num_pessoas = ?, pref_companhia = ?, "
                + "orcamento_total = ?, nivel_detalhamento = ?, req_especificos = ?, "
                + "data_ultima_atualizacao = ? "
                + "WHERE id = ? AND id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int i = 1; // Contador para os parâmetros

            stmt.setString(i++, form.getTitulo());
            stmt.setString(i++, form.getObjetivosGerais());
            stmt.setString(i++, form.getObjetivosEspecificos());
            stmt.setString(i++, form.getDestPrincipal());

            // Tratamento de NULL para INT
            if (form.getNumPessoas() != null) {
                stmt.setInt(i++, form.getNumPessoas());
            } else {
                stmt.setNull(i++, java.sql.Types.INTEGER);
            }

            stmt.setString(i++, form.getPrefCompanhia());

            // Tratamento de NULL para FLOAT
            if (form.getOrcTotal() != null) {
                stmt.setFloat(i++, form.getOrcTotal());
            } else {
                stmt.setNull(i++, java.sql.Types.FLOAT);
            }

            stmt.setString(i++, form.getNivelDetalhamento());
            stmt.setString(i++, form.getReqEspecificos());

            // 2. Data de Atualização (Deve ser o último campo antes do WHERE)
            stmt.setObject(i++, form.getData());

            // 3. Cláusula WHERE (Identificação do Registro)
            stmt.setInt(i++, form.getId()); // O ID do formulário
            stmt.setLong(i++, form.getId_usuario()); // O ID do usuário logado

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }



        }
        public static void excluir(int id) {
            String sql = "DELETE FROM `form_objetivos` WHERE id = ?";
            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}