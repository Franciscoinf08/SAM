
package sam.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sam.model.domain.Empresa;


public class EmpresaDAO {
    private  Connection conexao;

    public EmpresaDAO() {
        this.conexao = Conexao.getConnection();
    }
    public Empresa salvar(Empresa empresa) throws SQLException{
        String sql = "insert into empresa(nome, cnpj, milheiroSeguranca) values(?, ?, ?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCNPJ());
            stmt.setDouble(3, empresa.getMilheiroSeguranca());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {

                    empresa.setIdEmpresa(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }
    public Empresa buscarPorId(int id) {
        Empresa empresa = null;
        String sql = "SELECT * FROM empresa WHERE idEmpresa = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCNPJ(rs.getString("cnpj"));
                empresa.setMilheiroSeguranca(rs.getDouble("milheiroSeguranca"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }
    public void atualizarEmpresa(Empresa empresa) throws SQLException {
        String sql = "UPDATE empresa SET nome = ?, CNPJ = ?, milheiroSeguranca = ? WHERE idEmpresa = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCNPJ());
            stmt.setDouble(3, empresa.getMilheiroSeguranca());
            stmt.setInt(4, empresa.getIdEmpresa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário", e);
        }
    }
    public void excluirEmpresa(int id) throws SQLException {
        String sql = "DELETE FROM empresa WHERE idEmpresa = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir a empresa", e);
        }
    }
    public List<Empresa> listarTodas() {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCNPJ(rs.getString("cnpj"));
                empresa.setMilheiroSeguranca(rs.getDouble("milheiroSeguranca"));
                lista.add(empresa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


}