package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.Proposta;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropostaDAO {

    private final Connection conexao;

    public PropostaDAO() {
        this.conexao = Conexao.getConnection();
    }


    public void inserir(Proposta proposta) throws SQLException {
        String sql = """
            INSERT INTO proposta
            (idCliente, idGestor, origem, destino, dataIda, dataVolta,
             numAdultos, numCriancas, valorEmDinheiro, valorEmMilhas,
             taxas, observacoes, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, proposta.getCliente().getId());
            stmt.setLong(2, proposta.getGestor().getId());
            stmt.setString(3, proposta.getOrigem());
            stmt.setString(4, proposta.getDestino());
            stmt.setDate(5, Date.valueOf(proposta.getDataIda()));
            stmt.setDate(6, Date.valueOf(proposta.getDataVolta()));
            stmt.setInt(7, proposta.getNumAdultos());
            stmt.setInt(8, proposta.getNumCriancas());
            stmt.setDouble(9, proposta.getValorEmDinheiro());
            stmt.setLong(10, proposta.getValorEmMilhas());
            stmt.setDouble(11, proposta.getTaxas());
            stmt.setString(12, proposta.getObservacoes());
            stmt.setString(13, proposta.getStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir proposta: " + e.getMessage(), e);
        }
    }



    public void atualizar(Proposta proposta) throws SQLException {
        String sql = """
            UPDATE proposta SET
                origem = ?,
                destino = ?,
                dataIda = ?,
                dataVolta = ?,
                numAdultos = ?,
                numCriancas = ?,
                valorEmDinheiro = ?,
                valorEmMilhas = ?,
                taxas = ?,
                observacoes = ?,
                status = ?
            WHERE id = ?
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, proposta.getOrigem());
            stmt.setString(2, proposta.getDestino());
            stmt.setDate(3, Date.valueOf(proposta.getDataIda()));
            stmt.setDate(4, Date.valueOf(proposta.getDataVolta()));
            stmt.setInt(5, proposta.getNumAdultos());
            stmt.setInt(6, proposta.getNumCriancas());
            stmt.setDouble(7, proposta.getValorEmDinheiro());
            stmt.setLong(8, proposta.getValorEmMilhas());
            stmt.setDouble(9, proposta.getTaxas());
            stmt.setString(10, proposta.getObservacoes());
            stmt.setString(11, proposta.getStatus());
            stmt.setLong(12, proposta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar proposta: " + e.getMessage(), e);
        }
    }



    public void excluir(Long id) throws SQLException{
        String sql = "DELETE FROM proposta WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir proposta: " + e.getMessage(), e);
        }
    }

    public void atualizarStatus(Long idProposta, String statusProposta) {
        String sql = "UPDATE proposta SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, statusProposta);
            stmt.setLong(2, idProposta);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da proposta" + e.getMessage());
        }
    }




    public Proposta buscarPorId(Long id) {
        String sql = baseSelect() + " WHERE p.id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarProposta(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar proposta: " + e.getMessage(), e);
        }

        return null;
    }



    public List<Proposta> listarTodos() throws SQLException{
        List<Proposta> lista = new ArrayList<>();
        String sql = baseSelect() + " ORDER BY p.id DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montarProposta(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar propostas: " + e.getMessage(), e);
        }

        return lista;
    }



    public List<Proposta> listarPorCliente(Long idCliente) throws SQLException{
        List<Proposta> lista = new ArrayList<>();
        String sql = baseSelect() + " WHERE p.idCliente = ? ORDER BY p.id DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProposta(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar propostas do cliente: " + e.getMessage(), e);
        }

        return lista;
    }



    public List<Proposta> listarPorGestor(Long idGestor) throws SQLException{
        List<Proposta> lista = new ArrayList<>();
        String sql = baseSelect() + " WHERE p.idGestor = ? ORDER BY p.id DESC ";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idGestor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProposta(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar propostas do gestor: " + e.getMessage(), e);
        }

        return lista;
    }



    private String baseSelect() {
        return """
            SELECT
                p.*,

                c.id    AS cliente_id,
                c.nome  AS cliente_nome,
                c.email AS cliente_email,
                c.cpf   AS cliente_cpf,
                c.tipo  AS cliente_tipo,

                g.id    AS gestor_id,
                g.nome  AS gestor_nome,
                g.email AS gestor_email,
                g.cpf   AS gestor_cpf,
                g.tipo  AS gestor_tipo

            FROM proposta p
            JOIN usuarios c ON c.id = p.idCliente
            JOIN usuarios g ON g.id = p.idGestor
        """;
    }


    private Proposta montarProposta(ResultSet rs) throws SQLException{

        Usuario cliente = new Usuario(
                rs.getString("cliente_nome"),
                rs.getString("cliente_email"),
                rs.getString("cliente_cpf"),
                null,
                UsuarioTipo.valueOf(rs.getString("cliente_tipo"))
        );
        cliente.setId(rs.getLong("cliente_id"));

        Usuario gestor = new Usuario(
                rs.getString("gestor_nome"),
                rs.getString("gestor_email"),
                rs.getString("gestor_cpf"),
                null,
                UsuarioTipo.valueOf(rs.getString("gestor_tipo"))
        );
        gestor.setId(rs.getLong("gestor_id"));

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

        proposta.setId(rs.getLong("id"));

        return proposta;
    }
}
