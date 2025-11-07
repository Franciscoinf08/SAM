package sam.model.dao;

import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProgramaFidelidadeDAO {
    private final Connection conexao;

    public ProgramaFidelidadeDAO() {
        this.conexao = Conexao.getConnection();
    }

    public ProgramaFidelidade salvar(ProgramaFidelidade progFidelidade) throws SQLException {
        String sql = "insert into programasFidelidade values(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, progFidelidade.getNome());
            stmt.setDouble(2, progFidelidade.getBonusMilhas());
            stmt.setInt(3, progFidelidade.getDuracao());
            stmt.setInt(4, progFidelidade.getQtdeMilhasMes());
            stmt.setDouble(5, progFidelidade.getPrecoMensal());
            stmt.setString(6, progFidelidade.getAvaliacao());
            
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    progFidelidade.setIdProgramaFidelidade(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progFidelidade;
    }

    public ProgramaFidelidade buscarPorId(int id) {
        ProgramaFidelidade programaFidelidade = null;
        String sql = "SELECT * FROM programasFidelidade WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                programaFidelidade = new ProgramaFidelidade();
                programaFidelidade.setIdProgramaFidelidade(rs.getInt("id"));
                programaFidelidade.setNome(rs.getString("nome"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programaFidelidade;
    }

    public void atualizarProgramaFidelidade(int id, String nome, double bonusMilhas, double duracao, int qtdeMilhasMes, double preco) throws SQLException {
        String sql = "update programasFidelidade set nome = ?, bonusMilhas = ?, qtdeMilhasMes = ?, duracao = ? preco = ? where id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, bonusMilhas);
            stmt.setInt(3, qtdeMilhasMes);
            stmt.setDouble(4, duracao);
            stmt.setDouble(5, preco);
            stmt.setInt(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário", e);
        }
    }
    public void excluirProgramaFidelidade(int id) throws SQLException {
        String sql = "DELETE FROM programasFidelidade WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir a empresa", e);
        }
    }

}
