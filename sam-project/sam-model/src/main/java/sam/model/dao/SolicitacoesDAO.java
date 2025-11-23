package sam.model.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sam.model.domain.Solicitacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;
import sam.model.domain.util.Status;

public class SolicitacoesDAO {
    private final Connection conexao;
    
    public SolicitacoesDAO() {
        this.conexao = Conexao.getConnection();
        
        try {
            conexao.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Solicitacao> listarEmail(String email){
        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacoes_gestor";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                if(rs.getString("email") == email){
                    Solicitacao sol = new Solicitacao();
                    sol.setId(rs.getLong("id"));
                    sol.setNome(rs.getString("nome"));
                    sol.setEmail(rs.getString("email"));
                    sol.setPagamento(rs.getString("formaPagamento"));
                    sol.setStatus(Status.valueOf(rs.getString("nome")));
                    lista.add(sol);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Solicitacao> listarTodos(){
        List<Solicitacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitacoes_gestor";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Solicitacao sol = new Solicitacao();
                sol.setId(rs.getLong("id"));
                sol.setNome(rs.getString("nome"));
                sol.setEmail(rs.getString("email"));
                sol.setPagamento(rs.getString("formaPagamento"));
                sol.setStatus(Status.valueOf(rs.getString("nome")));
                lista.add(sol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void adicionarPedido(Solicitacao sol){
        String sql = "INSERT INTO solicitacoes_gestor(nome, email, formaPagamento, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, sol.getNome());
            preparedStatement.setString(2, sol.getEmail());
            preparedStatement.setString(3, sol.getPagamento());
            preparedStatement.setString(4, sol.getStatus().toString());
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                sol.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar pedido para conta gestor", e);
        }
    }
    
    public void cancelarPedido(Long id){
        String sql = "UPDATE solicitacoes_gestor SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "CANCELADO");
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cancelar pedido para conta gestor", e);
        }
    }
    
    public void aprovarPedido(Long id){
        UsuarioDAO dao = UsuarioDAO.getInstance();
        Usuario usuario = dao.pesquisar(id);
        String sql = "UPDATE solicitacoes_gestor SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "APROVADO");
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            
            usuario.setTipo(UsuarioTipo.GESTOR);
            dao.atualizar(usuario);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao aprovar pedido para conta gestor", e);
        }
    }
    
    public void recusarPedido(Long id){
        String sql = "UPDATE solicitacoes_gestor SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "RECUSADO");
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao recusar pedido para conta gestor", e);
        }
    }
    
    public void aguardarPagamento(Long id){
        String sql = "UPDATE solicitacoes_gestor SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "AGUARDANDO");
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao solicitar o pagamento", e);
        }
    }
    
    public String pesquisaId(Long id){
        String email = "";
        String sql = "SELECT * FROM solicitacoes_gestor WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao solicitar o pagamento", e);
        }
        return email;
    }
}
