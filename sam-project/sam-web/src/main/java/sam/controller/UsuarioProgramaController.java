package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sam.model.domain.Empresa;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="UsuarioProgramaController", urlPatterns = {"/usuarioPrograma"})
public class UsuarioProgramaController extends HttpServlet {
    GestaoUsuariosService usuariosService = new GestaoUsuariosService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "novo":
                mostrarFormulario(request,response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                listarClientesAssociados(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int idPrograma = Integer.parseInt(request.getParameter("idPrograma"));
        double saldoMilhas = Double.parseDouble(request.getParameter("saldoMilhas"));

    }

    private void associar(HttpServletRequest request, HttpServletResponse response) {


    }

    private void listarClientesAssociados(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        List<Usuario> lista = null;
        try {
            lista = usuariosService.getListaClientes(usuario);
            request.setAttribute("clientes", lista);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/meus-clientes.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) {
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) {
    }
}
