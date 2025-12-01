package sam.model.dao;

import sam.model.domain.Denuncia;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DenunciaDAO {

    private final Connection conexao;

    public DenunciaDAO() {
         this.conexao = Conexao.getConnection();
    }


    public void inserir(Denuncia denuncia) throws SQLException {
        String sql = "INSERT INTO denuncia (id_denunciante, id_denunciado, motivo, detalhes, status) " +
                     "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, denuncia.getDenunciante().getId());
        stmt.setLong(2, denuncia.getDenunciado().getId());
        stmt.setString(3, denuncia.getMotivo());
        stmt.setString(4, denuncia.getDetalhes());
        stmt.setString(5, denuncia.getStatus());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            denuncia.setId(rs.getInt(1));
        }
    }


    public List<Denuncia> listarTodas() throws SQLException {
        List<Denuncia> lista = new ArrayList<>();
        String sql = "SELECT d.id, d.motivo, d.detalhes, d.status, " +
                     "u1.id AS idDenunciante, u1.nome AS nomeDenunciante, u1.email AS emailDenunciante, u1.cpf AS cpfDenunciante, u1.tipo AS tipoDenunciante, " +
                     "u2.id AS idDenunciado, u2.nome AS nomeDenunciado, u2.email AS emailDenunciado, u2.cpf AS cpfDenunciado, u2.tipo AS tipoDenunciado " +
                     "FROM denuncia d " +
                     "JOIN usuario u1 ON d.id_denunciante = u1.id " +
                     "JOIN usuario u2 ON d.id_denunciado = u2.id";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario denunciante = new Usuario(
                    rs.getString("nomeDenunciante"),
                    rs.getString("emailDenunciante"),
                    rs.getString("cpfDenunciante"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipoDenunciante"))
            );
            denunciante.setId(rs.getLong("idDenunciante"));

            Usuario denunciado = new Usuario(
                    rs.getString("nomeDenunciado"),
                    rs.getString("emailDenunciado"),
                    rs.getString("cpfDenunciado"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipoDenunciado"))
            );
            denunciado.setId(rs.getLong("idDenunciado"));

            Denuncia d = new Denuncia(
                    rs.getInt("id"),
                    denunciante,
                    denunciado,
                    rs.getString("motivo"),
                    rs.getString("detalhes")
            );
            d.setStatus(rs.getString("status"));
            lista.add(d);
        }
        return lista;
    }

    public Denuncia buscarPorId(int id) throws SQLException {
        String sql = "SELECT d.id, d.motivo, d.detalhes, d.status, " +
                     "u1.id AS idDenunciante, u1.nome AS nomeDenunciante, u1.email AS emailDenunciante, u1.cpf AS cpfDenunciante, u1.tipo AS tipoDenunciante, " +
                     "u2.id AS idDenunciado, u2.nome AS nomeDenunciado, u2.email AS emailDenunciado, u2.cpf AS cpfDenunciado, u2.tipo AS tipoDenunciado " +
                     "FROM denuncia d " +
                     "JOIN usuario u1 ON d.id_denunciante = u1.id " +
                     "JOIN usuario u2 ON d.id_denunciado = u2.id " +
                     "WHERE d.id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Usuario denunciante = new Usuario(
                    rs.getString("nomeDenunciante"),
                    rs.getString("emailDenunciante"),
                    rs.getString("cpfDenunciante"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipoDenunciante"))
            );
            denunciante.setId(rs.getLong("idDenunciante"));

            Usuario denunciado = new Usuario(
                    rs.getString("nomeDenunciado"),
                    rs.getString("emailDenunciado"),
                    rs.getString("cpfDenunciado"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipoDenunciado"))
            );
            denunciado.setId(rs.getLong("idDenunciado"));

            Denuncia d = new Denuncia(
                    rs.getInt("id"),
                    denunciante,
                    denunciado,
                    rs.getString("motivo"),
                    rs.getString("detalhes")
            );
            d.setStatus(rs.getString("status"));
            return d;
        }
        return null;
    }

    public void atualizar(Denuncia denuncia) throws SQLException {
        String sql = "UPDATE denuncia SET id_denunciante = ?, id_denunciado = ?, motivo = ?, detalhes = ?, status = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setLong(1, denuncia.getDenunciante().getId());
        stmt.setLong(2, denuncia.getDenunciado().getId());
        stmt.setString(3, denuncia.getMotivo());
        stmt.setString(4, denuncia.getDetalhes());
        stmt.setString(5, denuncia.getStatus());
        stmt.setInt(6, denuncia.getId());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM denuncia WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
