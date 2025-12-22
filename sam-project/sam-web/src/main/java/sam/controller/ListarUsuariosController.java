package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListarUsuariosController")
public class ListarUsuariosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

            List<Usuario> usuarios =
                    usuarioDAO.listarPorTipo("CLIENTE");

            request.setAttribute("usuarios", usuarios);

            request.getRequestDispatcher("/core/gestor/visualizar-historico.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", e.getMessage());
            response.sendRedirect("/core/mensagens-erro.jsp");
        }
    }
}