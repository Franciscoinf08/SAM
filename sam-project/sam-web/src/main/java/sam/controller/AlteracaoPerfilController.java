package sam.controller;

import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AlteracaoPerfilController", urlPatterns = {"/AlteracaoPerfilController"})
public class AlteracaoPerfilController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();

        try {
            if (!"".equals(nome))
                usuario.setNome(nome);
            if (!"".equals(senha))
                usuario.setSenha(senha);
            if (!"".equals(email))
                usuario.setEmail(email);

            manterUsuario.atualizar(usuario);
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("/sam/core/perfil.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
