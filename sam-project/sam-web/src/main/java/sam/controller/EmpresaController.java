package sam.controller;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.dao.EmpresaDAO;
import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;

@WebServlet(name = "EmpresaController", urlPatterns = {"/EmpresaController"})
public class EmpresaController extends HttpServlet {
    private final EmpresaDAO empresaDAO = new EmpresaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h1>Servlet EmpresaController executando!</h1>");



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String nome  = request.getParameter("nomeEmpresa");
            String cnpj  = request.getParameter("cnpj");
            double milheiroSeguranca = Double.parseDouble(request.getParameter("milheiroSeguranca"));

            Empresa empresa = new Empresa(nome, cnpj, milheiroSeguranca);
        try {
            empresaDAO.salvar(empresa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        doGet(request, response);
    }
}
