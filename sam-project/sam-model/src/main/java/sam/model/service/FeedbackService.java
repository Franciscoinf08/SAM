package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.FeedbackDAO;
import sam.model.domain.Usuario;
import sam.model.domain.Feedback;

import java.sql.Connection;
import java.sql.SQLException;

public class FeedbackService {
    private final Connection conexao;
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();
    public FeedbackService() {
        this.conexao = Conexao.getConnection();
    }

    public void registrarFeedback(Long idAutor, Long idAvaliado, int nota, String comentario) throws Exception {

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

        Usuario autor = usuarioDAO.pesquisar(idAutor);
        Usuario avaliado = usuarioDAO.pesquisar(idAvaliado);

        if (autor == null)
            throw new Exception("Autor não encontrado.");

        if (avaliado == null)
            throw new Exception("Avaliado não encontrado.");

        Feedback feedback = new Feedback(autor, avaliado, comentario, nota);

        try {
            feedbackDAO.inserir(feedback);
        }catch (SQLException e){
            throw new RuntimeException("Erro ao inserir feedback:" + e.getMessage());
        }
    }
}
