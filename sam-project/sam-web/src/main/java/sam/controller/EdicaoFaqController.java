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
import java.sql.SQLException;

@WebServlet(name = "EdicaoFaqController", urlPatterns = {"/EdicaoFaqController"})
public class EdicaoFaqController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        String titulo;
        String pergunta;
        String resposta;

        try {
            id = Long.parseLong(request.getParameter("id"));
            titulo = request.getParameter("titulo");
            pergunta = request.getParameter("pergunta");
            resposta = request.getParameter("resposta");
        } catch (RuntimeException e) {
            e.printStackTrace();

            String erro = "Dados inválidos";
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/faq.jsp");
            rd.forward(request, response);
            return;
        }

        GestaoFaqService manterFaq = new  GestaoFaqService();

        try {
            FaqEntry faq = new FaqEntry(id, titulo, pergunta, resposta);

            manterFaq.atualizar(faq);
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
