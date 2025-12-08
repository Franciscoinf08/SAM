package sam.model.dao;

import sam.model.domain.Feedback;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    private final Connection conexao;

    public FeedbackDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Feedback feedback) throws SQLException {

        String sql = "INSERT INTO feedback (id_autor, id_avaliado, comentario, nota) " +
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
                "JOIN usuario u1 ON f.id_autor = u1.id " +
                "JOIN usuario u2 ON f.id_avaliado = u2.id " +
                "WHERE f.id_avaliado = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setLong(1, idAvaliado);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Usuario autor = new Usuario(
                    rs.getString("nomeAutor"),
                    rs.getString("emailAutor"),
                    rs.getString("cpfAutor"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipoAutor"))
            );
            autor.setId(rs.getLong("idAutor"));

            Usuario avaliado = new Usuario(
                    rs.getString("nomeAvaliado"),
                    rs.getString("emailAvaliado"),
                    rs.getString("cpfAvaliado"),
                    null,
                    UsuarioTipo.valueOf(rs.getString("tipoAvaliado"))
            );
            avaliado.setId(rs.getLong("idAvaliado"));

            Feedback f = new Feedback(
                    rs.getInt("id"),
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
