
package sam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sam.model.domain.Empresa;

public class EmpresaDAO {
    private final Connection conexao;

    public EmpresaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    public Connection salvar(Empresa empresa) throws SQLException{
            String sql = "insert into **tabela do BD** values(nome, CNPJ, programasFidelidade )";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCNPJ());
            stmt.setObject(3, empresa.getListaProgramasFidelidade());
            stmt.setObject(3, empresa.getListaProgramasFidelidade());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    empresa.setIdEmpresa(rs.getInt(1));  // Aqui pegamos o id gerado e colocamos no objeto
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
}
