package sam.controller;

import sam.model.common.exception.PersistenciaException;
import sam.model.domain.RespostaTicket;
import sam.model.service.GestaoRespostasTicketService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CadastroRespostaTicketController", urlPatterns = {"/CadastroRespostaTicketController"})
public class CadastroRespostaTicketController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long idUsuario;
        Long idPergunta = null;
        String descricao = request.getParameter("descricao");

        try {
            idUsuario = Long.parseLong(request.getParameter("usuario"));
            idPergunta = Long.parseLong(request.getParameter("pergunta"));
        } catch (RuntimeException e) {
            e.printStackTrace();

            String erro = "Dados inválidos";
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/tickets.jsp?pergunta=" + idPergunta);
            rd.forward(request, response);
            return;
        }

        GestaoRespostasTicketService manterResposta = new GestaoRespostasTicketService();

        try {
            RespostaTicket resposta = new RespostaTicket(idUsuario, idPergunta, descricao);

            manterResposta.cadastrar(resposta);
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
