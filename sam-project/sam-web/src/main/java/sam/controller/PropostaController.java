package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.domain.util.StatusProposta;
import sam.model.service.PropostaService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "proposta",  urlPatterns = {"/proposta"})
public class PropostaController extends HttpServlet {

    private final PropostaService propostaService =  new PropostaService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long idGestor = Long.parseLong(request.getParameter("idGestor"));
            Long idCliente = Long.parseLong(request.getParameter("idCliente"));
            String origem = request.getParameter("origem");
            String destino = request.getParameter("destino");
            String observacoes = request.getParameter("observacoes");

            LocalDate dataIda = LocalDate.parse(request.getParameter("dataIda"));
            LocalDate dataVolta = LocalDate.parse(request.getParameter("dataVolta"));

            int numAdultos = Integer.parseInt(request.getParameter("numAdultos"));
            int numCriancas = Integer.parseInt(request.getParameter("numCriancas"));

            double valorEmDinheiro = Double.parseDouble(request.getParameter("valorEmDinheiro"));
            Long valorEmMilhas = Long.parseLong(request.getParameter("valorEmMilhas"));
            double taxas = Double.parseDouble(request.getParameter("taxas"));

            propostaService.registrarProposta(idCliente,idGestor, origem, destino, observacoes, dataIda, dataVolta, numAdultos, numCriancas, valorEmDinheiro, valorEmMilhas, taxas);
            response.sendRedirect(request.getContextPath() + "/core/gestor/orcamentos.jsp");
        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/core/gestor/orcamentos.jsp")
                    .forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("excluir".equals(action)) {
            try {
                Long idProposta = Long.parseLong(request.getParameter("id"));

                propostaService.excluirProposta(idProposta);

                response.sendRedirect("core/gestor/orcamentos.jsp");

            } catch (Exception e) {
                throw new ServletException("Erro ao excluir proposta", e);
            }
        }else  if ("cancelar".equals(action)) {
            try {
                Long idProposta = Long.parseLong(request.getParameter("id"));
                StatusProposta statusProposta = StatusProposta.CANCELADA;
                propostaService.atualizarStatus(idProposta, statusProposta);

                response.sendRedirect("core/cliente/visualizacaoPropostas.jsp");

            }catch (Exception e) {
                throw new ServletException("Erro ao cancelar proposta", e);
            }

        } else if ("aprovar".equals(action)) {
            try {
                Long idProposta = Long.parseLong(request.getParameter("id"));
                StatusProposta statusProposta = StatusProposta.APROVADA;
                propostaService.atualizarStatus(idProposta, statusProposta);
                response.sendRedirect("core/cliente/visualizacaoPropostas.jsp");
            }catch (Exception e) {
                throw new ServletException("Erro ao aprovar proposta", e);
            }

        }

    }

}
