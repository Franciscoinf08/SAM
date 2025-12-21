package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.FaqEntry;
import sam.model.service.GestaoFaqService;

import java.io.IOException;

@WebServlet(name = "CadastroFaqController", urlPatterns = {"/CadastroFaqController"})
public class CadastroFaqController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String pergunta = request.getParameter("pergunta");
        String resposta = request.getParameter("resposta");

        GestaoFaqService manterFaq = new GestaoFaqService();

        try {
            FaqEntry faq = new FaqEntry(titulo, pergunta, resposta);

            manterFaq.cadastrar(faq);
            response.sendRedirect("/sam/core/geral/faq.jsp");
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
            RequestDispatcher rd = request.getRequestDispatcher("");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
