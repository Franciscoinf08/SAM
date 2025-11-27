package sam.model.dao;

import sam.model.domain.Transacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.TransacaoStatus;
import sam.model.domain.util.TransacaoTipo;

import java.sql.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class TransacaoDAO implements GenericDAO<Transacao, Long> {

    private final Connection conexao;
    private static TransacaoDAO transacaoDAO;

    static {
        TransacaoDAO.transacaoDAO = null;
    }

    private TransacaoDAO() {
        this.conexao = Conexao.getConnection();
    }

    public static TransacaoDAO getInstance() {
        if (transacaoDAO == null)
            transacaoDAO = new TransacaoDAO();

        return transacaoDAO;
    }

    @Override
    public void inserir(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes(id_programa, id_cliente, data, data_expiracao, quantidade, tipo, valor, bonus, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, \"ATIVA\")";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, transacao.getIdProgramaFidelidade());
            preparedStatement.setLong(2, transacao.getIdCliente());
            preparedStatement.setDate(3, transacao.getData());
            preparedStatement.setDate(4, transacao.getDataExpiracao());
            preparedStatement.setLong(5, transacao.getQuantidade());
            preparedStatement.setString(6, transacao.getTipo().toString());
            preparedStatement.setBigDecimal(7, transacao.getValor());
            preparedStatement.setInt(8, transacao.getBonus());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                transacao.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar transação", e);
        }
    }

    @Override
    public void atualizar(Transacao transacao) throws SQLException {
        String sql = "UPDATE transacoes SET id_programa = ?, id_cliente = ?, data = ?, data_expiracao = ?, quantidade = ?, tipo = ?, valor = ?, bonus = ? WHERE id = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, transacao.getIdProgramaFidelidade());
            preparedStatement.setLong(2, transacao.getIdCliente());
            preparedStatement.setDate(3, transacao.getData());
            preparedStatement.setDate(4, transacao.getDataExpiracao());
            preparedStatement.setLong(5, transacao.getQuantidade());
            preparedStatement.setString(6, transacao.getTipo().toString());
            preparedStatement.setBigDecimal(7, transacao.getValor());
            preparedStatement.setInt(8, transacao.getBonus());
            preparedStatement.setLong(9, transacao.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar transação", e);
        }
    }

    public void remover(Long id) throws SQLException {
        String sql = "UPDATE transacoes SET status = \"REMOVIDA\" WHERE id = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover transação", e);
        }
    }

    public void expirar(Long id) throws SQLException {
        String sql = "UPDATE transacoes SET status = \"EXPIRADA\" WHERE id = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao expirar transação", e);
        }
    }

    @Override
    public Transacao pesquisar(Long id) throws SQLException {
        Transacao transacao = null;
        String sql = "SELECT * FROM transacoes WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long idProgramaFidelidade = rs.getLong("id_programa");
                Long idCliente = rs.getLong("id_cliente");
                Date data = rs.getDate("data");
                Date dataExpiracao = rs.getDate("data_expiracao");
                int quantidade = rs.getInt("quantidade");
                String tipo = rs.getString("tipo");
                BigDecimal valor = rs.getBigDecimal("valor");
                int bonus = rs.getInt("bonus");
                TransacaoStatus status = TransacaoStatus.strTo(rs.getString("status"));

                if (dataExpiracao.before(new Date(System.currentTimeMillis())))
                    expirar(id);

                transacao = new Transacao(idProgramaFidelidade, idCliente, data, dataExpiracao, quantidade, TransacaoTipo.strTo(tipo), valor, bonus, status);
                transacao.setId(id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar transação", e);
        }
        return transacao;
    }

    public List<Transacao> pesquisarPorCliente(Usuario cliente) throws SQLException {
        List<Transacao> listaTransacoes = new ArrayList<>();
        Long idCliente = cliente.getId();
        String sql = "SELECT * FROM transacoes WHERE id_cliente = ? AND (status = \"ATIVA\" OR status = \"EXPIRADA\") ORDER BY data DESC";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long idProgramaFidelidade = rs.getLong("id_programa");
                Date data = rs.getDate("data");
                Date dataExpiracao = rs.getDate("data_expiracao");
                int quantidade = rs.getInt("quantidade");
                String tipo = rs.getString("tipo");
                BigDecimal valor = rs.getBigDecimal("valor");
                int bonus = rs.getInt("bonus");
                TransacaoStatus status = TransacaoStatus.strTo(rs.getString("status"));

                if (dataExpiracao.before(new Date(System.currentTimeMillis()))) {
                    expirar(id);
                    continue;
                }

                Transacao transacao = new Transacao(idProgramaFidelidade, cliente.getId(), data, dataExpiracao, quantidade, TransacaoTipo.strTo(tipo), valor, bonus, status);
                transacao.setId(id);

                listaTransacoes.add(transacao);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar transações", e);
        }
        return listaTransacoes;
    }
}
