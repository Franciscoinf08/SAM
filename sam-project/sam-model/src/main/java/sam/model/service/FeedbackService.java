package sam.model.service;

import sam.model.dao.Conexao;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.FeedbackDAO;
import sam.model.domain.Usuario;
import sam.model.domain.Feedback;

import java.sql.Connection;

public class FeedbackService {

    public void registrarFeedback(Long autorId,
                                  Long avaliadoId,
                                  Integer nota,
                                  String comentario) throws Exception {

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

        Usuario autor = usuarioDAO.pesquisar(autorId);
        Usuario avaliado = usuarioDAO.pesquisar(avaliadoId);

        if (autor == null)
            throw new Exception("Autor não encontrado.");

        if (avaliado == null)
            throw new Exception("Avaliado não encontrado.");

        try (Connection conexao = Conexao.getConnection()) {

            FeedbackDAO feedbackDAO = new FeedbackDAO();

            Feedback feedback = new Feedback(
                    0,
                    autor,
                    avaliado,
                    comentario,
                    nota
            );

            feedbackDAO.inserir(feedback);
        }
    }
}
