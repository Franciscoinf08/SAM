package sam.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.dao.EmpresaDAO;
import sam.model.domain.Empresa;
import sam.model.service.EmpresaService;

@WebServlet(name = "EmpresaController", urlPatterns = {"/empresa"})
public class EmpresaController extends HttpServlet {
    private final EmpresaService empresaService = new EmpresaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "novo":
                    mostrarFormulario(request, response);
                    break;
                case "editar":
                    mostrarFormularioEdicao(request, response);
                    break;
                case "excluir":
                    excluirEmpresa(request, response);
                    break;
                default:
                    listarEmpresas(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/core/gestor/empresas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        try {
            if ("atualizar".equals(action)) {
                atualizarEmpresa(request, response);
            } else {
                inserirEmpresa(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empresa> lista = empresaService.listarEmpresas();
        request.setAttribute("empresas", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/empresas.jsp");
        dispatcher.forward(request, response);
    }


    private void inserirEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        Empresa empresa = new Empresa(
                request.getParameter("nomeEmpresa"),
                request.getParameter("cnpj"),
                Double.parseDouble(request.getParameter("milheiroSeguranca"))
        );
        empresaService.cadastrarEmpresa(empresa);
        response.sendRedirect(request.getContextPath() + "/empresa");
    }

    private void excluirEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        empresaService.excluir(id);
        response.sendRedirect(request.getContextPath() + "/empresa");
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/formularioEmpresas.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empresa empresa = empresaService.buscarEmpresa(id);
        request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/formularioEmpresas.jsp");

        dispatcher.forward(request, response);
    }

    private void atualizarEmpresa(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empresa empresa = new Empresa(request.getParameter("nomeEmpresa"), request.getParameter("cnpj"), Double.parseDouble(request.getParameter("milheiroSeguranca")));
        empresa.setIdEmpresa(id);
        empresaService.atualizarEmpresa(empresa);
        response.sendRedirect(request.getContextPath() + "/empresa");
    }
}
