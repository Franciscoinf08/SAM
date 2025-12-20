package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.Proposta;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PropostaDAO {

    private final Connection conexao;

    public PropostaDAO() {
        this.conexao = Conexao.getConnection();
    }

    public void inserir(Proposta proposta) throws SQLException{
        String sql = """
            INSERT INTO proposta
            (idCliente, idGestor, status, valorEmDinheiro, valorEmMilhas, taxas,
             observacoes, dataIda, dataVolta, origem, destino, numAdultos, numCriancas)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, proposta.getCliente().getId());
            stmt.setLong(2, proposta.getGestor().getId());
            stmt.setString(3, proposta.getStatus());
            stmt.setDouble(4, proposta.getValorEmDinheiro());
            stmt.setLong(5, proposta.getValorEmMilhas());
            stmt.setDouble(6, proposta.getTaxas());
            stmt.setString(7, proposta.getObservacoes());
            stmt.setDate(8, Date.valueOf(proposta.getDataIda()));
            stmt.setDate(9, Date.valueOf(proposta.getDataVolta()));
            stmt.setString(10, proposta.getOrigem());
            stmt.setString(11, proposta.getDestino());
            stmt.setInt(12, proposta.getNumAdultos());
            stmt.setInt(13, proposta.getNumCriancas());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir proposta" + e.getMessage());
        }
    }

    public void atualizar(Proposta proposta) throws SQLException {
        String sql = """
            UPDATE proposta SET
                status = ?,
                valorEmDinheiro = ?,
                valorEmMilhas = ?,
                taxas = ?,
                observacoes = ?,
                dataIda = ?,
                dataVolta = ?,
                origem = ?,
                destino = ?,
                numAdultos = ?,
                numCriancas = ?
            WHERE id = ?
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, proposta.getStatus());
            stmt.setDouble(2, proposta.getValorEmDinheiro());
            stmt.setLong(3, proposta.getValorEmMilhas());
            stmt.setDouble(4, proposta.getTaxas());
            stmt.setString(5, proposta.getObservacoes());
            stmt.setDate(6, Date.valueOf(proposta.getDataIda()));
            stmt.setDate(7, Date.valueOf(proposta.getDataVolta()));
            stmt.setString(8, proposta.getOrigem());
            stmt.setString(9, proposta.getDestino());
            stmt.setInt(10, proposta.getNumAdultos());
            stmt.setInt(11, proposta.getNumCriancas());
            stmt.setInt(12, proposta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar proposta" + e.getMessage());
        }
    }

    public void excluir(int id) throws SQLException{
        String sql = "DELETE FROM proposta WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir proposta" + e.getMessage());
        }
    }

    public Proposta buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT p.*,
                   c.id   AS id, c.nome AS nome, c.email AS email,
                   c.cpf  AS cpf, c.tipo AS tipo,
                   g.id   AS id, g.nome AS nome, g.email AS email,
                   g.cpf  AS cpf, g.tipo AS tipo
            FROM proposta p
            JOIN usuario c ON c.id = p.idCliente
            JOIN usuario g ON g.id = p.idGestor
            WHERE p.id = ?
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarProposta(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar proposta" + e.getMessage());
        }

        return null;
    }

    public List<Proposta> listarTodos() throws SQLException {
        List<Proposta> lista = new ArrayList<>();

        String sql = """
            SELECT p.*,
                   c.id   AS id, c.nome AS nome, c.email AS email,
                   c.cpf  AS cpf, c.tipo AS tipo,
                   g.id   AS id, g.nome AS nome, g.email AS email,
                   g.cpf  AS cpf, g.tipo AS tipo
            FROM proposta p
            JOIN usuario c ON c.id = p.idCliente
            JOIN usuario g ON g.id = p.idGestor
            ORDER BY p.id
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montarProposta(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar proposta" + e.getMessage());
        }

        return lista;
    }

    private Proposta montarProposta(ResultSet rs) throws SQLException {

        Usuario cliente = new Usuario(
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("cpf"),
                null,
                UsuarioTipo.valueOf(rs.getString("tipo"))
        );
        cliente.setId(rs.getLong("id"));

        Usuario gestor = new Usuario(
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("cpf"),
                null,
                UsuarioTipo.valueOf(rs.getString("tipoGestor"))
        );
        gestor.setId(rs.getLong("id"));

        Proposta proposta = new Proposta(
                cliente,
                gestor,
                rs.getString("status"),
                rs.getDouble("valorEmDinheiro"),
                rs.getLong("valorEmMilhas"),
                rs.getDouble("taxas"),
                rs.getString("observacoes"),
                rs.getDate("dataIda").toLocalDate(),
                rs.getDate("dataVolta").toLocalDate(),
                rs.getString("origem"),
                rs.getString("destino"),
                rs.getInt("numAdultos"),
                rs.getInt("numCriancas")
        );

        return proposta;
    }
}
