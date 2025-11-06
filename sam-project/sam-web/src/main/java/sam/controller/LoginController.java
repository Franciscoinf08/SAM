package sam.controller;

import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();

        try {
            Usuario usuario = manterUsuario.pesquisarConta(cpf, senha);
            request.getSession().setAttribute("usuario", usuario);

            String jsp = "";
            switch (usuario.getTipo()) {
                case CLIENTE:
                    jsp = "/sam/core/cliente/dashboard.jsp";
                    break;
                case GESTOR:
                    jsp = "/sam/core/gestor/apuracao.jsp";
                    break;
                case DESENVOLVEDOR:
                    jsp = "/sam/core/perfil.jsp";
                    break;
            }
            response.sendRedirect(jsp);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
            RequestDispatcher rd = request.getRequestDispatcher("");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void validarSessao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
}
