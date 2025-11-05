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

@WebServlet(name = "EmpresaController", urlPatterns = {"/empresa"})
public class EmpresaController extends HttpServlet {
    private final EmpresaDAO empresaDAO = new EmpresaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Empresa> listaEmpresas = empresaDAO.listarTodas();
            request.setAttribute("empresas", listaEmpresas);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao carregar empresas: " + e.getMessage());
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/empresas.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nome = request.getParameter("nomeEmpresa");
            String cnpj = request.getParameter("cnpj");
            double milheiroSeguranca = Double.parseDouble(request.getParameter("milheiroSeguranca"));

            Empresa empresa = new Empresa(nome, cnpj, milheiroSeguranca);
            empresaDAO.salvar(empresa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        doGet(request, response);
    }
}
