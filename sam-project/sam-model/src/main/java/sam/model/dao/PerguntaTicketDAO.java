package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.PerguntaTicket;

import java.sql.*;

public class PerguntaTicketDAO implements GenericDAO<PerguntaTicket, Long> {

    private final Connection conexao;
    private static PerguntaTicketDAO perguntaTicketDAO;

    static {
        PerguntaTicketDAO.perguntaTicketDAO = null;
    }

    private PerguntaTicketDAO() {
        this.conexao = Conexao.getConnection();
    }

    public static PerguntaTicketDAO getInstance() {
        if (perguntaTicketDAO == null)
            perguntaTicketDAO = new PerguntaTicketDAO();

        return perguntaTicketDAO;
    }

    @Override
    public void inserir(PerguntaTicket pergunta) throws SQLException {
        String sql = "INSERT INTO perguntas_ticket(id_usuario, titulo, descricao) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, pergunta.getIdUsuario());
            preparedStatement.setString(2, pergunta.getTitulo());
            preparedStatement.setString(3, pergunta.getDescricao());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                pergunta.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar pergunta", e);
        }
    }

    @Override
    public void atualizar(PerguntaTicket pergunta) throws SQLException {
        String sql = "UPDATE perguntas_ticket SET id_usuario = ?, titulo = ?, descricao = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, pergunta.getIdUsuario());
            preparedStatement.setString(2, pergunta.getTitulo());
            preparedStatement.setString(3, pergunta.getDescricao());
            preparedStatement.setLong(4, pergunta.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar pergunta", e);
        }
    }

    @Override
    public PerguntaTicket pesquisar(Long id) throws SQLException {

        PerguntaTicket pergunta = null;
        String sql = "SELECT * FROM perguntas_ticket WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long idUsuario = rs.getLong("id_usuario");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                pergunta = new PerguntaTicket(id, idUsuario, titulo, descricao);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar pergunta", e);
        }
        return pergunta;
    }
}
