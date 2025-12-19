package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Usuario;

import sam.model.service.GestaoUsuariosService;

@WebServlet("/AlterarSenhaController")
public class AlterarSenhaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("emailRecuperacao");

        if (email == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String novaSenha = request.getParameter("senha1");

        try {
            GestaoUsuariosService service = new GestaoUsuariosService();
            Usuario usuario = service.buscarPorEmail(email);

            if (usuario == null) {
                request.setAttribute("erro", "Usuário não encontrado.");
                request.getRequestDispatcher("/core/geral/esqueceu-senha.jsp").forward(request, response);
                return;
            }

            usuario.setSenha(novaSenha);
            service.atualizar(usuario);

            request.getRequestDispatcher("/core/geral/dashboard.jsp");

        } catch (PersistenciaException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/core/geral/alterar-senha.jsp").forward(request, response);
        }
    }
}