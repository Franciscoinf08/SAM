package sam.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sam.model.service.*;

import java.io.IOException;

@WebServlet(name="feedback", urlPatterns = {"/feedback"})
public class FeedbackController extends HttpServlet {

    private final FeedbackService feedbackService =  new FeedbackService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long idAutor = Long.parseLong(request.getParameter("idAutor"));
            long idAvaliado = Long.parseLong(request.getParameter("idAvaliado"));
            int nota = Integer.parseInt(request.getParameter("nota"));
            String comentario = request.getParameter("comentario");

            feedbackService.registrarFeedback(idAutor, idAvaliado, nota, comentario);

            response.sendRedirect(request.getContextPath() + "/core/geral/avaliacoes.jsp?sucesso=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/core/geral/avaliacoes.jsp?erro=1");
        }
    }
}
