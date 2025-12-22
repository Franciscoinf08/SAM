package sam.controller;

import sam.model.common.exception.PersistenciaException;
import sam.model.service.GestaoPerguntasTicketService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemocaoTicketController", urlPatterns = {"/RemocaoTicketController"})
public class RemocaoTicketController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;

        try {
            id = Long.parseLong(request.getParameter("remover"));
        } catch (NumberFormatException e) {
            e.printStackTrace();

            response.sendRedirect("/core/geral/suporte.jsp");
            return;
        }

        GestaoPerguntasTicketService manterTicket = new GestaoPerguntasTicketService();

        try {
            manterTicket.remover(id);
            response.sendRedirect("/sam/core/geral/suporte.jsp");
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/suporte.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
