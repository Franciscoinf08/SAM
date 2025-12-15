package sam.model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import sam.model.domain.FormObjetivos;
import sam.model.common.Conexao;
import sam.model.domain.Usuario;

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

    public static List<FormObjetivos> buscarTodosPorUsuarioId(Long idUsuario) {

        String sql = """
        SELECT id, titulo_formulario, data_ultima_atualizacao
        FROM form_objetivos
        WHERE id_usuario = ?
        ORDER BY id DESC
    """;

        List<FormObjetivos> listaFormularios = new LinkedList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FormObjetivos form = new FormObjetivos();

                    form.setId(rs.getInt("id"));
                    form.setTitulo(rs.getString("titulo_formulario"));

                    if (rs.getDate("data_ultima_atualizacao") != null) {
                        form.setData(
                                rs.getDate("data_ultima_atualizacao").toLocalDate()
                        );
                    }

                    listaFormularios.add(form);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar formulários por usuário ID: " + e.getMessage());
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

        String sql = """
        UPDATE form_objetivos SET 
            titulo_formulario = ?, 
            objetivos_gerais = ?, 
            objetivos_especificos = ?, 
            destino_principal = ?, 
            num_pessoas = ?, 
            pref_companhia = ?, 
            orcamento_total = ?, 
            nivel_detalhamento = ?, 
            req_especificos = ?, 
            data_ultima_atualizacao = ?
        WHERE id = ? AND id_usuario = ?
    """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, form.getTitulo());
            stmt.setString(2, form.getObjetivosGerais());
            stmt.setString(3, form.getObjetivosEspecificos());
            stmt.setString(4, form.getDestPrincipal());

            // NULL OU VALOR → AUTOMÁTICO
            stmt.setObject(5, form.getNumPessoas());

            stmt.setString(6, form.getPrefCompanhia());

            // NULL OU VALOR → AUTOMÁTICO
            stmt.setObject(7, form.getOrcTotal());

            stmt.setString(8, form.getNivelDetalhamento());
            stmt.setString(9, form.getReqEspecificos());
            stmt.setObject(10, form.getData());

            // WHERE
            stmt.setInt(11, form.getId());
            stmt.setLong(12, form.getId_usuario());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean excluir(int id, long idUsuario) {
        String sql = "DELETE FROM form_objetivos WHERE id = ? AND id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setLong(2, idUsuario);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


