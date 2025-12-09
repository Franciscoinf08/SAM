package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.FaqEntry;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FaqDAO implements GenericDAO<FaqEntry, Long> {

    private final Connection conexao;
    private static FaqDAO faqDAO;

    static {
        FaqDAO.faqDAO = null;
    }

    private FaqDAO() {
        this.conexao = Conexao.getConnection();
    }

    public static FaqDAO getInstance() {
        if (faqDAO == null)
            faqDAO = new FaqDAO();

        return faqDAO;
    }

    @Override
    public void inserir(FaqEntry faq) throws SQLException {
        String sql = "INSERT INTO perguntas_faq(titulo, pergunta, resposta) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, faq.getTitulo());
            preparedStatement.setString(2, faq.getPergunta());
            preparedStatement.setString(3, faq.getResposta());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                faq.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar pergunta", e);
        }
    }

    @Override
    public void atualizar(FaqEntry faq) throws SQLException {
        String sql = "UPDATE perguntas_faq SET titulo = ?, pergunta = ?, resposta = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, faq.getTitulo());
            preparedStatement.setString(2, faq.getPergunta());
            preparedStatement.setString(3, faq.getResposta());
            preparedStatement.setLong(4, faq.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar pergunta", e);
        }
    }

    @Override
    public FaqEntry pesquisar(Long id) throws SQLException {

        FaqEntry faq = null;
        String sql = "SELECT * FROM perguntas_faq WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String pergunta = rs.getString("pergunta");
                String resposta = rs.getString("resposta");

                faq = new FaqEntry(id, titulo, pergunta, resposta);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar pergunta", e);
        }
        return faq;
    }

    public List<FaqEntry> pesquisarTodos() throws SQLException {
        List<FaqEntry> listaFaq = new LinkedList<>();

        String sql = "SELECT * FROM perguntas_faq";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String pergunta = rs.getString("pergunta");
                String resposta = rs.getString("resposta");

                FaqEntry faq = new FaqEntry(id, titulo, pergunta, resposta);
                listaFaq.add(faq);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar pergunta", e);
        }
        return listaFaq;
    }
}
