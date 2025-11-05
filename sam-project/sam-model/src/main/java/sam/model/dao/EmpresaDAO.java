
package sam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sam.model.domain.Empresa;

public class EmpresaDAO {
    private  Connection conexao;

    public EmpresaDAO() {
        this.conexao = Conexao.getConnection();
    }
    public Empresa salvar(Empresa empresa) throws SQLException{
            String sql = "insert into empresa(nome, cnpj, milheiroSeguranca) values(?, ?, ?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCNPJ());
            stmt.setDouble(3, empresa.getMilheiroSeguranca());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    empresa.setIdEmpresa(rs.getInt(1));  // Aqui pegamos o id gerado e colocamos no objeto
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }
    public Empresa buscarPorId(int id) {
        Empresa empresa = null;
        String sql = "SELECT * FROM empresas WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("id"));
                empresa.setNome(rs.getString("nome"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }
    public void atualizarEmpresa(int id, String nome, String CNPJ, double milheiroSeguranca) throws SQLException {
        String sql = "UPDATE empresas SET nome = ?, CNPJ = ?, milheiroSeguranca = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, CNPJ);
            stmt.setDouble(3, milheiroSeguranca);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário", e);
        }
    }
    public void excluirEmpresa(int id) throws SQLException {
        String sql = "DELETE FROM empresas WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir a empresa", e);
        }
    }

}
