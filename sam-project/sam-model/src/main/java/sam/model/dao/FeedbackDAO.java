package sam.model.dao;

import sam.model.domain.Feedback;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sam.model.common.Conexao;

public class FeedbackDAO {

    private final Connection conexao;

    public FeedbackDAO() {

        this.conexao = Conexao.getConnection();
    }

    public void inserir(Feedback feedback) throws SQLException {

        String sql = "INSERT INTO feedback(idAutor, idAvaliado, comentario, nota) " +
                     "VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, feedback.getAutor().getId());
        stmt.setLong(2, feedback.getAvaliado().getId());
        stmt.setString(3, feedback.getComentario());
        stmt.setInt(4, feedback.getNota());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            feedback.setId(rs.getInt(1));
        }
    }

    public List<Feedback> listarPorUsuario(Long idAvaliado) throws SQLException {
        List<Feedback> lista = new ArrayList<>();

        String sql =
                "SELECT f.id, f.comentario, f.nota, " +
                "u1.id AS idAutor, u1.nome AS nomeAutor, u1.email AS emailAutor, u1.cpf AS cpfAutor, u1.tipo AS tipoAutor, " +
                "u2.id AS idAvaliado, u2.nome AS nomeAvaliado, u2.email AS emailAvaliado, u2.cpf AS cpfAvaliado, u2.tipo AS tipoAvaliado " +
                "FROM feedback f " +
                "JOIN usuario u1 ON f.idAutor = u1.id " +
                "JOIN usuario u2 ON f.idAvaliado = u2.id " +
                "WHERE f.idAvaliado = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setLong(1, idAvaliado);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Usuario autor = new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipo"))
            );
            autor.setId(rs.getLong("idAutor"));

            Usuario avaliado = new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipo"))
            );
            avaliado.setId(rs.getLong("idAvaliado"));

            Feedback f = new Feedback(
                    autor,
                    avaliado,
                    rs.getString("comentario"),
                    rs.getInt("nota")
            );

            lista.add(f);
        }

        return lista;
    }
}
