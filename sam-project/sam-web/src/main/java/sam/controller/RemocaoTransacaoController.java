package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.common.exception.PersistenciaException;
import sam.model.service.GestaoTransacoesService;

import java.io.IOException;

@WebServlet(name = "RemocaoTransacaoController", urlPatterns = {"/RemocaoTransacaoController"})
public class RemocaoTransacaoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;

        try {
            id = Long.parseLong(request.getParameter("remover"));
        } catch (RuntimeException e) {
            e.printStackTrace();

            String erro = "Dados inválidos";
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/transacoes.jsp");
            rd.forward(request, response);
            return;
        }

        GestaoTransacoesService manterTransacao = new GestaoTransacoesService();

        try {
            manterTransacao.remover(id);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/core/cliente/dashboard.jsp");
        rd.forward(request, response);
    }
}
