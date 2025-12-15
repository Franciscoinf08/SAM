package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.common.Conexao;
import sam.model.dao.FeedbackDAO;
import sam.model.dao.FormObjetivosDao;
import sam.model.domain.Feedback;
import sam.model.domain.FormObjetivos;
import sam.model.domain.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/HistoricoController")
public class HistoricoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

            List<FormObjetivos> formularios =
                    FormObjetivosDao.buscarTodosPorUsuarioId(idUsuario);

            FeedbackDAO fbDao =
                    new FeedbackDAO(Conexao.getConnection());

            List<Feedback> feedbacks =
                    fbDao.listarPorUsuario(idUsuario);

            request.setAttribute("formularios", formularios);
            request.setAttribute("feedbacks", feedbacks);

            request.getRequestDispatcher("/jsp/cliente/historico.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", e.getMessage());
            response.sendRedirect("/core/mensagens-erro.jsp");

        }
    }
}