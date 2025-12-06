package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import sam.model.dao.EmpresaDAO;
import sam.model.service.AvaliadorProgramaFidelidadeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.ProgramaFidelidade;
import sam.model.service.ProgramaFidelidadeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProgramaFidelidadeController", urlPatterns = {"/programaFidelidade"})
public class ProgramaFidelidadeController extends HttpServlet {

    private final ProgramaFidelidadeService pfs = new ProgramaFidelidadeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "novo":
                exibirFormulario(request, response);
                break;
            case "editar":
                exibirFormularioEdicao(request, response);
                break;
            case "excluir":
                excluirProgramaFidelidade(request, response);
                break;
            default:
                try {
                    listarProgramasFidelidade(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void exibirFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("idEmpresa");
        if (idParam == null || idParam.isEmpty()) {
            throw new ServletException("ID da empresa é obrigatório para cadastrar programa de fidelidade.");
        }

        int idEmpresa = Integer.parseInt(idParam);
        request.setAttribute("idEmpresa", idEmpresa);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/formularioProgramaFidelidade.jsp");
        dispatcher.forward(request, response);
    }

    private void listarProgramasFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String idParam = request.getParameter("idEmpresa");

        if (idParam == null || idParam.isEmpty()) {
            throw new ServletException("ID da empresa é obrigatório para listar programas de fidelidade.");
        }

        int idEmpresa = Integer.parseInt(idParam);
        List<ProgramaFidelidade> lista = pfs.listarProgramaFidelidadePorEmpresa(idEmpresa);

        request.setAttribute("programas", lista);
        request.setAttribute("idEmpresa", idEmpresa);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/programasFidelidade.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String idParam = request.getParameter("id");
            Integer id = null;

            if (idParam != null && !idParam.isEmpty()) {
                id = Integer.parseInt(idParam);
            }
            if (id != null && id > 0) {
                editarProgramaFidelidade(request, response);
            } else {
                adicionarProgramaFidelidade(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Erro ao processar programa de fidelidade.", e);
        }
    }

    private void adicionarProgramaFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String nome = request.getParameter("nome");
        double bonusMilhas = Double.parseDouble(request.getParameter("bonusMilhas"));
        int qtdeMilhasMes = Integer.parseInt(request.getParameter("qtdeMilhasMes"));
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        double preco = Double.parseDouble(request.getParameter("preco"));

        AvaliadorProgramaFidelidadeService avaliador = new AvaliadorProgramaFidelidadeService(new EmpresaDAO(), new ProgramaFidelidadeDAO());

        ProgramaFidelidade programa = new ProgramaFidelidade(
                nome, bonusMilhas, qtdeMilhasMes, duracao, preco, idEmpresa
        );


        pfs.cadastrarProgramaFidelidade(programa);
        avaliador.avaliarPrograma(programa);
        response.sendRedirect(request.getContextPath() + "/programaFidelidade?action=listar&idEmpresa=" + idEmpresa);
    }

    private void editarProgramaFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        double bonusMilhas = Double.parseDouble(request.getParameter("bonusMilhas"));
        int qtdeMilhasMes = Integer.parseInt(request.getParameter("qtdeMilhasMes"));
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        double preco = Double.parseDouble(request.getParameter("preco"));

        ProgramaFidelidade programa = new ProgramaFidelidade(nome, bonusMilhas, qtdeMilhasMes, duracao, preco, idEmpresa);
        programa.setIdProgramaFidelidade(id);

        pfs.atualizarProgramaFidelidade(programa);
        AvaliadorProgramaFidelidadeService avaliador = new AvaliadorProgramaFidelidadeService(new EmpresaDAO(), new ProgramaFidelidadeDAO());
        avaliador.avaliarPrograma(programa);
        response.sendRedirect(request.getContextPath() + "/programaFidelidade?action=listar&idEmpresa=" + idEmpresa);
    }

    private void excluirProgramaFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
            pfs.deletarProgramaFidelidade(id);
            response.sendRedirect(request.getContextPath() + "/programaFidelidade?action=listar&idEmpresa=" + idEmpresa);
        } catch (Exception e) {
            throw new ServletException("Erro ao excluir programa de fidelidade.", e);
        }
    }

    private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProgramaFidelidade programa = pfs.buscarProgramaFidelidade(id);
            request.setAttribute("programa", programa);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/formularioProgramaFidelidade.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new ServletException("Erro ao carregar programa de fidelidade para edição.", e);
        }
    }
}