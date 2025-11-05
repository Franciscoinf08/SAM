package sam.controller;

import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="CadastroController", urlPatterns = {"/CadastroController"})
public class CadastroController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();
        Usuario usuario = new Usuario(nome, email, cpf, senha);

        try {
            manterUsuario.cadastrar(usuario);
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("/sam/core/cliente/dashboard.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
