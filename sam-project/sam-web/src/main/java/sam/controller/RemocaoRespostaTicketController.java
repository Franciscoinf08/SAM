package sam.controller;

import sam.model.common.exception.PersistenciaException;
import sam.model.service.GestaoRespostasTicketService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemocaoRespostaTicketController", urlPatterns = {"/RemocaoRespostaTicketController"})
public class RemocaoRespostaTicketController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        Long idPergunta = null;

        try {
            id = Long.parseLong(request.getParameter("remover"));
            idPergunta = Long.parseLong(request.getParameter("pergunta"));
        } catch (NumberFormatException e) {
            e.printStackTrace();

            response.sendRedirect("/sam/core/geral/tickets.jsp?pergunta=" + idPergunta);
            return;
        }

        GestaoRespostasTicketService manterResposta = new GestaoRespostasTicketService();

        try {
            manterResposta.remover(id);
            response.sendRedirect("/sam/core/geral/tickets.jsp?pergunta=" + idPergunta);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/tickets.jsp?pergunta=" + idPergunta);
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
