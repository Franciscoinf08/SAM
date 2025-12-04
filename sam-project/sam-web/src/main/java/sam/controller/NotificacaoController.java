package sam.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sam.model.domain.Notificacao;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;
import sam.model.service.NotificacaoService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="notificacoes", urlPatterns = {"/notificacoes"})
public class NotificacaoController extends HttpServlet {

    private final NotificacaoService notificacaoService =  new NotificacaoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        String action = request.getParameter("action");
        if (action == null) {
            throw  new NullPointerException("action e NULL");
        }

        switch (action) {

            case "listar":
                listarNotificacoes(request, response);
                break;
            case "detalhar":
                break;
            case "marcarComoLida":
                marcarComoLida(request, response);
                break;

        }


    }

    private void marcarComoLida(HttpServletRequest request, HttpServletResponse response) {
        int idNotificacao = Integer.parseInt(request.getParameter("idNotificacao"));

        notificacaoService.marcarComoLida(idNotificacao);
        try {
            response.sendRedirect(request.getContextPath() + "/notificacoes?action=listar");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void listarNotificacoes(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        int idUsuario = Math.toIntExact(usuario.getId());

        List<Notificacao> lista;
        try {
            lista = notificacaoService.lista(idUsuario);
            request.setAttribute("listaNotificacoes", lista);
            RequestDispatcher dispatcher = request.getRequestDispatcher("core/geral/notificacoes.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException("erro no sql ao listar notificacoes", e);
        } catch (ServletException | IOException  e) {
            throw new RuntimeException("erro no servlet ao listar notificacoes", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {

    }

}
