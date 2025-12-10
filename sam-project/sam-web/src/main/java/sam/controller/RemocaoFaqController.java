package sam.controller;

import sam.model.common.exception.PersistenciaException;
import sam.model.service.GestaoFaqService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemocaoFaqController", urlPatterns = {"/RemocaoFaqController"})
public class RemocaoFaqController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;

        try {
            id = Long.parseLong(request.getParameter("remover"));
        } catch (NumberFormatException e) {
            e.printStackTrace();

            response.sendRedirect("/core/geral/faq.jsp");
            return;
        }

        GestaoFaqService manterFaq = new GestaoFaqService();

        try {
            manterFaq.remover(id);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/core/geral/faq.jsp");
        rd.forward(request, response);
    }
}
