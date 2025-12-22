package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.domain.Atividade;
import sam.model.domain.AtividadeReferencia;
import sam.model.service.AtividadeService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="atividadeController", urlPatterns = {"/atividades"})
public class AtividadeController extends HttpServlet {

    private AtividadeService atividadeService = new AtividadeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("detalhes".equals(action)) {
                mostrarDetalhes(request, response);
            } else {
                listarAtividades(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarAtividades(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Atividade> atividades = atividadeService.listarTodas();
        request.setAttribute("atividades", atividades);

        request.getRequestDispatcher("/core/dev/monitoramento.jsp")
                .forward(request, response);
    }

    private void mostrarDetalhes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        Long atividadeId = Long.parseLong(request.getParameter("id"));

        Atividade atividade = atividadeService.buscarPorId(atividadeId);
        List<AtividadeReferencia> referencias =
                atividadeService.listarReferencias(atividadeId);

        request.setAttribute("atividade", atividade);
        request.setAttribute("referencias", referencias);

        request.getRequestDispatcher("/core/dev/monitoramento.jsp")
                .forward(request, response);
    }
}
