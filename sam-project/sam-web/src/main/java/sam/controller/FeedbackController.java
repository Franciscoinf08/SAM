package sam.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sam.model.service.*;

import java.io.IOException;

@WebServlet(name="feedback", urlPatterns = {"/feedback"})
public class FeedbackController extends HttpServlet {

    private FeedbackService service;

    @Override
    public void init() throws ServletException {
        service = new FeedbackService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long autorId = Long.parseLong(request.getParameter("autorId"));
            long avaliadoId = Long.parseLong(request.getParameter("avaliadoId"));
            Integer nota = Integer.parseInt(request.getParameter("nota"));
            String comentario = request.getParameter("comentario");

            service.registrarFeedback(autorId, avaliadoId, nota, comentario);

            response.sendRedirect("views/avaliacoes/avaliacoes.jsp?sucesso=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("views/avaliacoes/avaliacoes.jsp?erro=1");
        }
    }
}
