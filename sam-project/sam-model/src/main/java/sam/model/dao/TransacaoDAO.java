package sam.model.dao;

import sam.model.domain.Transacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.TransacaoTipo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class TransacaoDAO implements GenericDAO<Transacao, Long> {

    private final Connection conexao;
    private final UsuarioDAO usuarioDAO;
    private static TransacaoDAO transacaoDAO;

    static {
        TransacaoDAO.transacaoDAO = null;
    }

    private TransacaoDAO() {
        this.conexao = Conexao.getConnection();
        this.usuarioDAO = UsuarioDAO.getInstance();
    }

    public static TransacaoDAO getInstance() {
        if (transacaoDAO == null)
            transacaoDAO = new TransacaoDAO();

        return transacaoDAO;
    }

    @Override
    public void inserir(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes(id_cliente, data, quantidade, tipo, valor, bonus) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, transacao.getIdCliente());
            preparedStatement.setTimestamp(2, transacao.getData());
            preparedStatement.setLong(3, transacao.getQuantidade());
            preparedStatement.setString(4, transacao.getTipo().toString());
            preparedStatement.setBigDecimal(5, transacao.getValor());
            preparedStatement.setInt(6, transacao.getBonus());

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
        String sql = "UPDATE transacoes SET id_cliente = ?, data = ?, quantidade = ?, tipo = ?, valor = ?, bonus = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, transacao.getIdCliente());
            preparedStatement.setTimestamp(2, transacao.getData());
            preparedStatement.setLong(3, transacao.getQuantidade());
            preparedStatement.setString(4, transacao.getTipo().toString());
            preparedStatement.setBigDecimal(5, transacao.getValor());
            preparedStatement.setInt(6, transacao.getBonus());
            preparedStatement.setLong(7, transacao.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário", e);
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
                Long idCliente = rs.getLong("id_cliente");
                Timestamp data = rs.getTimestamp("data");
                int quantidade = rs.getInt("quantidade");
                String tipo = rs.getString("tipo");
                BigDecimal valor = rs.getBigDecimal("valor");
                int bonus = rs.getInt("bonus");

                Usuario cliente = usuarioDAO.pesquisar(idCliente);

                transacao = new Transacao(cliente.getId(), data, quantidade, TransacaoTipo.strTo(tipo), valor, bonus);
                transacao.setId(id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return transacao;
    }

    public List<Transacao> pesquisarPorCliente(Usuario cliente) throws SQLException {
        List<Transacao> listaTransacoes = new ArrayList<>();
        Long idCliente = cliente.getId();
        String sql = "SELECT * FROM transacoes WHERE id_cliente = ? ORDER BY data DESC";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Timestamp data = rs.getTimestamp("data");
                int quantidade = rs.getInt("quantidade");
                String tipo = rs.getString("tipo");
                BigDecimal valor = rs.getBigDecimal("valor");
                int bonus = rs.getInt("bonus");

                Transacao transacao = new Transacao(cliente.getId(), data, quantidade, TransacaoTipo.strTo(tipo), valor, bonus);
                transacao.setId(id);

                listaTransacoes.add(transacao);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar transações", e);
        }
        return listaTransacoes;
    }

    public List<Transacao> pesquisarPorGestor(Usuario gestor) throws SQLException {
        List<Transacao> listaTransacoes = new ArrayList<>();
        Long idGestor = gestor.getId();
        String sql = "SELECT t.* FROM transacoes t JOIN usuarios u ON u.id = t.id_cliente WHERE u.id_gestor = ? ORDER BY data DESC";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idGestor);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long idCliente = rs.getLong("idCliente");
                Timestamp data = rs.getTimestamp("data");
                int quantidade = rs.getInt("quantidade");
                String tipo = rs.getString("tipo");
                BigDecimal valor = rs.getBigDecimal("valor");
                int bonus = rs.getInt("bonus");

                Transacao transacao = new Transacao(idCliente, data, quantidade, TransacaoTipo.strTo(tipo), valor, bonus);
                transacao.setId(id);

                listaTransacoes.add(transacao);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar transações", e);
        }
        return listaTransacoes;
    }
}
