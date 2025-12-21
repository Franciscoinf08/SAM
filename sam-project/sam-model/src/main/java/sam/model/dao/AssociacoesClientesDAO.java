package sam.model.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sam.model.common.Conexao;
import sam.model.domain.AssociacaoCliente;
import sam.model.domain.util.AssociacaoClienteTipo;

public class AssociacoesClientesDAO {
    
    private final Connection conexao;
    private static AssociacoesClientesDAO associacoesDAO;

    static {
        AssociacoesClientesDAO.associacoesDAO = null;
    }

    private AssociacoesClientesDAO() {
        this.conexao = Conexao.getConnection();
    }

    public static AssociacoesClientesDAO getInstance() {
        if (associacoesDAO == null) {
            associacoesDAO = new AssociacoesClientesDAO();
        }

        return associacoesDAO;
    }
    
    // REGISTRA O PEDIDO DE ASSOCIAÇÃO/DISSOCIACAO PARA APROVAÇÃO DO CLIENTE
    public void adicionarPedido(AssociacaoCliente associacao) throws SQLException {
        String sql = "INSERT INTO pedidos_associacoes(idCliente, idGestor, tipo) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, associacao.getIdCliente());
            preparedStatement.setLong(2, associacao.getIdGestor());
            preparedStatement.setString(3, associacao.getTipo().toString());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                associacao.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao adicionar pedido para cliente", e);
        }
    }
    
    // OPERA A APROVAÇÃO DO CLIENTE PARA ASSOCIAÇÃO
    public void aprovarAssociacao(Long idCliente, Long idGestor) throws SQLException {
        String sql = "UPDATE usuarios SET id_gestor = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setLong(1, idGestor);
            preparedStatement.setLong(2, idCliente);

            preparedStatement.executeUpdate();
            
            this.deletaTodos(idCliente);
        } catch (SQLException e) {
            throw new SQLException("Erro ao aprovar associação", e);
        }
    }
    
    // OPERA A APROVAÇÃO DO CLIENTE PARA DESASSOCIAÇÃO E A DESASSOCIAÇÃO APÓS O BLOQUEIO 
    public void aprovarDesassociacao(Long idCliente) throws SQLException {
        String sql = "UPDATE usuarios SET id_gestor = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setNull(1, java.sql.Types.BIGINT);
            preparedStatement.setLong(2, idCliente);

            preparedStatement.executeUpdate();
            
            this.deletaTodos(idCliente);
            UsuarioProgramaDAO userPDAO = new UsuarioProgramaDAO();
            userPDAO.excluirTodos(idCliente.intValue());
        } catch (SQLException e) {
            throw new SQLException("Erro ao aprovar desassociação", e);
        }
    }
    
    // DELETA TODOS OS PEDIDOS APÓS A APROVAÇÃO
    public void deletaTodos(Long idCliente) throws SQLException {
        String sql = "DELETE FROM pedidos_associacoes WHERE idCliente = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, idCliente);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar todos pedidos", e);
        }
    }
    
    // DELETA O PEDIDO APÓS A RECUSA 
    public void deleta(Long id) throws SQLException {
        String sql = "DELETE FROM pedidos_associacoes WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar pedido", e);
        }
    }
    
    // LISTA TODOS OS PEDIDOS FEITOS A UM CLIENTE
    public List<AssociacaoCliente> listarCliente(Long cliente) throws SQLException {
        List<AssociacaoCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_associacoes";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Long idCliente = rs.getLong("idCliente");
                if(idCliente == cliente){
                    Long id = rs.getLong("id");
                    Long idGestor = rs.getLong("idGestor");
                    AssociacaoClienteTipo tipo = AssociacaoClienteTipo.valueOf(rs.getString("tipo"));
                    
                    AssociacaoCliente associacao = new AssociacaoCliente(idCliente, idGestor, tipo);
                    associacao.setId(id);
                    lista.add(associacao);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // LISTA TODOS OS PEDIDOS FEITOS POR UM GESTOR
    public List<AssociacaoCliente> listarGestor(Long gestor) throws SQLException {
        List<AssociacaoCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_associacoes";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Long idGestor = rs.getLong("idGestor");
                if(idGestor == gestor){
                    Long id = rs.getLong("id");
                    Long idCliente = rs.getLong("idCliente");
                    AssociacaoClienteTipo tipo = AssociacaoClienteTipo.valueOf(rs.getString("tipo"));
                    
                    AssociacaoCliente associacao = new AssociacaoCliente(idCliente, idGestor, tipo);
                    associacao.setId(id);
                    lista.add(associacao);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public AssociacaoCliente pesquisar(Long idPedido) throws SQLException {
        AssociacaoCliente pedido = null;
        String sql = "SELECT * FROM pedidos_associacoes WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idPedido);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                Long idGestor = rs.getLong("idGestor");
                Long idCliente = rs.getLong("idCliente");
                AssociacaoClienteTipo tipo = AssociacaoClienteTipo.valueOf(rs.getString("tipo"));

                pedido = new AssociacaoCliente(idCliente, idGestor, tipo);
                pedido.setId(id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return pedido;
    }
    
}
