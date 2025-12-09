package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.RespostaTicket;

import java.sql.*;

public class RespostaTicketDAO implements GenericDAO<RespostaTicket, Long> {

    private final Connection conexao;
    private static RespostaTicketDAO respostaTicketDAO;

    static {
        RespostaTicketDAO.respostaTicketDAO = null;
    }

    private RespostaTicketDAO() {
        this.conexao = Conexao.getConnection();
    }

    public static RespostaTicketDAO getInstance() {
        if (respostaTicketDAO == null)
            respostaTicketDAO = new RespostaTicketDAO();

        return respostaTicketDAO;
    }

    @Override
    public void inserir(RespostaTicket resposta) throws SQLException {
        String sql = "INSERT INTO respostas_ticket(id_usuario, id_pergunta, descricao) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, resposta.getIdUsuario());
            preparedStatement.setLong(2, resposta.getIdPergunta());
            preparedStatement.setString(3, resposta.getDescricao());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                resposta.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar resposta", e);
        }
    }

    @Override
    public void atualizar(RespostaTicket resposta) throws SQLException {
        String sql = "UPDATE respostas_ticket SET id_usuario = ?, id_pergunta = ?, descricao = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, resposta.getIdUsuario());
            preparedStatement.setLong(2, resposta.getIdPergunta());
            preparedStatement.setString(3, resposta.getDescricao());
            preparedStatement.setLong(4, resposta.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar resposta", e);
        }
    }

    @Override
    public RespostaTicket pesquisar(Long id) throws SQLException {

        RespostaTicket resposta = null;
        String sql = "SELECT * FROM respostas_ticket WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long idUsuario = rs.getLong("id_usuario");
                Long idPergunta = rs.getLong("id_pergunta");
                String descricao = rs.getString("descricao");

                resposta = new RespostaTicket(id, idUsuario, idPergunta, descricao);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar resposta", e);
        }
        return resposta;
    }
}
