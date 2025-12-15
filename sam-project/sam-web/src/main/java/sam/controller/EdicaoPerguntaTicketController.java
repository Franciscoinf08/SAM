package sam.controller;

import sam.model.common.exception.PersistenciaException;
import sam.model.domain.PerguntaTicket;
import sam.model.service.GestaoPerguntasTicketService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EdicaoPerguntaTicketController", urlPatterns = {"/EdicaoPerguntaTicketController"})
public class EdicaoPerguntaTicketController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        Long idUsuario;
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");

        try {
            id = Long.parseLong(request.getParameter("id"));
            idUsuario = Long.parseLong(request.getParameter("usuario"));
        } catch (RuntimeException e) {
            e.printStackTrace();

            String erro = "Dados inválidos";
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/suporte.jsp");
            rd.forward(request, response);
            return;
        }

        GestaoPerguntasTicketService manterPergunta = new GestaoPerguntasTicketService();

        try {
            PerguntaTicket pergunta = new PerguntaTicket(id, idUsuario, titulo, descricao);

            manterPergunta.atualizar(pergunta);
            response.sendRedirect("/sam/core/geral/suporte.jsp");
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
            RequestDispatcher rd = request.getRequestDispatcher("/sam/core/geral/suporte.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
