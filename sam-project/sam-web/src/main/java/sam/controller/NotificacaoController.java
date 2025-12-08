package sam.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sam.model.domain.Notificacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;
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

    private void listarNotificacoes(HttpServletRequest request, HttpServletResponse response){
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        GestaoUsuariosService gestaoUsuariosService = new GestaoUsuariosService();
        int idUsuario = Math.toIntExact(usuario.getId());

        List<Notificacao> lista;
        try {
            if (usuario.getTipo() == UsuarioTipo.GESTOR){
                List<Usuario> clientes;
                clientes =  gestaoUsuariosService.getListaClientes(usuario);
                request.setAttribute("clientes", clientes);
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
            enviarNotificacao(request, response);

    }

    private void enviarNotificacao(HttpServletRequest request, HttpServletResponse response) {
        String titulo = request.getParameter("titulo");
        String mensagem = request.getParameter("mensagem");
        int destinatario = Integer.parseInt(request.getParameter("cliente"));
        Notificacao notificacao = new Notificacao(titulo, mensagem, destinatario);

        try {
            notificacaoService.enviar(notificacao);
            response.sendRedirect(request.getContextPath() + "/notificacoes?action=listar");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
