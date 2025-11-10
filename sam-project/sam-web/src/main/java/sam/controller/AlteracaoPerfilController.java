package sam.controller;

import sam.model.common.exception.PersistenciaException;
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


@WebServlet(name = "AlteracaoPerfilController", urlPatterns = {"/AlteracaoPerfilController"})
public class AlteracaoPerfilController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario((Usuario) request.getSession().getAttribute("usuario"));
        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();

        try {
            if (!"".equals(nome))
                usuario.setNome(nome);
            if (!"".equals(email))
                usuario.setEmail(email);
            if (!"".equals(senha))
                usuario.setSenha(senha);

            manterUsuario.atualizar(usuario);

            request.getSession().setAttribute("usuario", usuario);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/core/perfil.jsp");
        rd.forward(request, response);
    }
}
