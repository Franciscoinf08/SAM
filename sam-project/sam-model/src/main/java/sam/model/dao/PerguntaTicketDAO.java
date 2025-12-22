package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.PerguntaTicket;
import sam.model.domain.Usuario;
import sam.model.domain.util.PerguntaTicketStatus;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
        String sql = "INSERT INTO perguntas_ticket(id_usuario, titulo, descricao, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, pergunta.getIdUsuario());
            preparedStatement.setString(2, pergunta.getTitulo());
            preparedStatement.setString(3, pergunta.getDescricao());
            preparedStatement.setString(4, pergunta.getStatus().toString());

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
        String sql = "UPDATE perguntas_ticket SET id_usuario = ?, titulo = ?, descricao = ?, status = ? WHERE id = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, pergunta.getIdUsuario());
            preparedStatement.setString(2, pergunta.getTitulo());
            preparedStatement.setString(3, pergunta.getDescricao());
            preparedStatement.setString(4, pergunta.getStatus().toString());
            preparedStatement.setLong(5, pergunta.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar pergunta", e);
        }
    }

    @Override
    public PerguntaTicket pesquisar(Long id) throws SQLException {

        PerguntaTicket pergunta = null;
        String sql = "SELECT * FROM perguntas_ticket WHERE id = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long idUsuario = rs.getLong("id_usuario");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                PerguntaTicketStatus status = PerguntaTicketStatus.strTo(rs.getString("status"));

                pergunta = new PerguntaTicket(id, idUsuario, titulo, descricao, status);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar pergunta", e);
        }
        return pergunta;
    }

    public List<PerguntaTicket> pesquisarPorUsuario(Usuario usuario) throws SQLException {

        List<PerguntaTicket> listaPerguntas = new LinkedList<>();
        String sql = "SELECT * FROM perguntas_ticket WHERE id_usuario = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, usuario.getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                PerguntaTicketStatus status = PerguntaTicketStatus.strTo(rs.getString("status"));

                PerguntaTicket pergunta = new PerguntaTicket(id, usuario.getId(), titulo, descricao, status);
                listaPerguntas.add(pergunta);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar perguntas", e);
        }
        return listaPerguntas;
    }

    public List<PerguntaTicket> pesquisarSemResposta() throws SQLException {

        List<PerguntaTicket> listaPerguntas = new LinkedList<>();
        String sql = "SELECT * FROM perguntas_ticket p WHERE NOT EXISTS (" +
                     "SELECT 1 " +
                     "FROM respostas_ticket r " +
                     "WHERE r.id_pergunta = p.id AND r.status = \"ATIVA\") " +
                     "AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long idUsuario = rs.getLong("id_usuario");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                PerguntaTicketStatus status = PerguntaTicketStatus.strTo(rs.getString("status"));

                PerguntaTicket pergunta = new PerguntaTicket(id, idUsuario, titulo, descricao, status);
                listaPerguntas.add(pergunta);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar perguntas", e);
        }
        return listaPerguntas;
    }

    public List<PerguntaTicket> pesquisarComResposta() throws SQLException {

        List<PerguntaTicket> listaPerguntas = new LinkedList<>();
        String sql = "SELECT * FROM perguntas_ticket p WHERE EXISTS (" +
                "SELECT 1 " +
                "FROM respostas_ticket r " +
                "WHERE r.id_pergunta = p.id AND r.status = \"ATIVA\") " +
                "AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long idUsuario = rs.getLong("id_usuario");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                PerguntaTicketStatus status = PerguntaTicketStatus.strTo(rs.getString("status"));

                PerguntaTicket pergunta = new PerguntaTicket(id, idUsuario, titulo, descricao, status);
                listaPerguntas.add(pergunta);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar perguntas", e);
        }
        return listaPerguntas;
    }

    public void remover(Long id) throws SQLException {

        String sql = "UPDATE perguntas_ticket SET status = \"REMOVIDA\" WHERE id = ? AND status = \"ATIVA\"";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover ticket", e);
        }
    }
}
