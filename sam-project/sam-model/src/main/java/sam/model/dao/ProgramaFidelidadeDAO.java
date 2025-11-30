package sam.model.dao;

import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.Usuario;
import sam.model.domain.UsuarioPrograma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaFidelidadeDAO {
    private final Connection conexao;

    public ProgramaFidelidadeDAO() {
        this.conexao = Conexao.getConnection();
    }


    public ProgramaFidelidade salvar(ProgramaFidelidade programa) {
        String sql = "INSERT INTO programa_fidelidade (nome, bonusMilhas, qtdeMilhasMes, duracao, empresa_id, precoMes, avaliacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, programa.getNome());
            stmt.setDouble(2, programa.getBonusMilhas());
            stmt.setInt(3, programa.getQtdeMilhasMes());
            stmt.setInt(4, programa.getDuracao());
            stmt.setInt(5, programa.getIdEmpresa());
            stmt.setDouble(6, programa.getPrecoMensal());
            stmt.setString(7, programa.getAvaliacao());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    programa.setIdProgramaFidelidade(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }
    public List<ProgramaFidelidade> listarNaoAssociados(Usuario usuario){
        ProgramaFidelidadeDAO dao = new ProgramaFidelidadeDAO();
        List<ProgramaFidelidade> lista = new ArrayList<>();
        String sql = "SELECT p.*\n" +
                "FROM programa_fidelidade p\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1\n" +
                "    FROM usuario_programa up\n" +
                "    WHERE up.programa_id = p.idProgramaFidelidade\n" +
                "      AND up.usuario_id = ?\n" +
                ");";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, Math.toIntExact(usuario.getId()));
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProgramaFidelidade p = new ProgramaFidelidade();
                    p.setIdProgramaFidelidade(rs.getInt("idProgramaFidelidade"));
                    p.setPrecoMensal(rs.getDouble("precoMes"));
                    p.setAvaliacao(rs.getString("avaliacao"));
                    p.setBonusMilhas(rs.getDouble("bonusMilhas"));
                    p.setDuracao(rs.getInt("duracao"));
                    p.setIdEmpresa(rs.getInt("empresa_id"));
                    p.setNome(rs.getString("nome"));
                    p.setQtdeMilhasMes(rs.getInt("qtdeMilhasMes"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public ProgramaFidelidade buscarPorId(int id) {
        ProgramaFidelidade programaFidelidade = null;
        String sql = "SELECT * FROM programa_fidelidade WHERE idProgramaFidelidade = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                programaFidelidade = new ProgramaFidelidade();
                programaFidelidade.setIdProgramaFidelidade(rs.getInt("idProgramaFidelidade"));
                programaFidelidade.setNome(rs.getString("nome"));
                programaFidelidade.setBonusMilhas(rs.getDouble("bonusMilhas"));
                programaFidelidade.setQtdeMilhasMes(rs.getInt("qtdeMilhasMes"));
                programaFidelidade.setDuracao(rs.getInt("duracao"));
                programaFidelidade.setPrecoMensal(rs.getDouble("precoMes"));
                programaFidelidade.setIdEmpresa(rs.getInt("empresa_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programaFidelidade;
    }

    public void atualizarProgramaFidelidade(ProgramaFidelidade programaFidelidade) throws SQLException {
        String sql = "UPDATE programa_fidelidade SET nome = ?, bonusMilhas = ?, qtdeMilhasMes = ?, duracao = ?, precoMes = ?, avaliacao = ? WHERE idProgramaFidelidade = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, programaFidelidade.getNome());
            stmt.setDouble(2, programaFidelidade.getBonusMilhas());
            stmt.setInt(3, programaFidelidade.getQtdeMilhasMes());
            stmt.setInt(4, programaFidelidade.getDuracao());
            stmt.setDouble(5, programaFidelidade.getPrecoMensal());
            stmt.setString(6, programaFidelidade.getAvaliacao());
            stmt.setInt(7, programaFidelidade.getIdProgramaFidelidade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar programa de fidelidade", e);
        }
    }

    public void excluirProgramaFidelidade(int id) throws SQLException {
        String sql = "DELETE FROM programa_fidelidade WHERE idProgramaFidelidade = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir o programa de fidelidade", e);
        }
    }

    public List<ProgramaFidelidade> listarPorEmpresa(int idEmpresa) {
        List<ProgramaFidelidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM programa_fidelidade WHERE empresa_id = ? ORDER BY idProgramaFidelidade";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEmpresa);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProgramaFidelidade programaFidelidade = new ProgramaFidelidade();
                    programaFidelidade.setIdProgramaFidelidade(rs.getInt("idProgramaFidelidade"));
                    programaFidelidade.setNome(rs.getString("nome"));
                    programaFidelidade.setBonusMilhas(rs.getDouble("bonusMilhas"));
                    programaFidelidade.setQtdeMilhasMes(rs.getInt("qtdeMilhasMes"));
                    programaFidelidade.setDuracao(rs.getInt("duracao"));
                    programaFidelidade.setPrecoMensal(rs.getDouble("precoMes"));
                    programaFidelidade.setIdEmpresa(rs.getInt("empresa_id"));
                    programaFidelidade.setAvaliacao(rs.getString("avaliacao"));
                    lista.add(programaFidelidade);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ProgramaFidelidade> listarTodos() {
        List<ProgramaFidelidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM programa_fidelidade";
        try(PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                ProgramaFidelidade programaFidelidade = new ProgramaFidelidade();
                programaFidelidade.setIdProgramaFidelidade(rs.getInt("idProgramaFidelidade"));
                programaFidelidade.setNome(rs.getString("nome"));
                programaFidelidade.setBonusMilhas(rs.getDouble("bonusMilhas"));
                programaFidelidade.setQtdeMilhasMes(rs.getInt("qtdeMilhasMes"));
                programaFidelidade.setDuracao(rs.getInt("duracao"));
                programaFidelidade.setPrecoMensal(rs.getDouble("precoMes"));
                programaFidelidade.setIdEmpresa(rs.getInt("empresa_id"));
                programaFidelidade.setAvaliacao(rs.getString("avaliacao"));
                lista.add(programaFidelidade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<ProgramaFidelidade> listarAssociados(Usuario usuario) {
        List<ProgramaFidelidade> lista = new ArrayList<>();

        String sql = """
        SELECT p.*
        FROM usuario_programa up
        INNER JOIN programa_fidelidade p 
                ON p.idProgramaFidelidade = up.programa_id
        WHERE up.usuario_id = ?
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, usuario.getId()); // Não precisa converter

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProgramaFidelidade p = new ProgramaFidelidade();

                    p.setIdProgramaFidelidade(rs.getInt("idProgramaFidelidade"));
                    p.setPrecoMensal(rs.getDouble("precoMes"));
                    p.setAvaliacao(rs.getString("avaliacao"));
                    p.setBonusMilhas(rs.getDouble("bonusMilhas"));
                    p.setDuracao(rs.getInt("duracao"));
                    p.setIdEmpresa(rs.getInt("empresa_id"));
                    p.setNome(rs.getString("nome"));
                    p.setQtdeMilhasMes(rs.getInt("qtdeMilhasMes"));

                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar programas associados", e);
        }

        return lista;
    }

}