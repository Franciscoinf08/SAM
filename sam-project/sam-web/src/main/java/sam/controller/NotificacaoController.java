package sam.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.service.NotificacaoService;

@WebServlet("/notificacoes")
public class NotificacaoController extends HttpServlet {

    private final NotificacaoService service = new NotificacaoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        try {
            req.setAttribute("lista", service.listarPorUsuario(usuario.getId()));
            req.getRequestDispatcher("core/geral/notificacoes.jsp").forward(req, resp);
        } catch (PersistenciaException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        String acao = req.getParameter("acao");
        long idNotif = Long.parseLong(req.getParameter("id"));
        long idUsuario = (Long) req.getSession().getAttribute("usuarioId");

        try {
            if ("lida".equals(acao)) {
                service.marcarComoLida(idNotif, idUsuario);
            }
        } catch (PersistenciaException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/notificacoes");
    }
}
