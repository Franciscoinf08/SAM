package sam.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.service.NotificacaoService;

@WebServlet(name = "notificacao", urlPatterns = {"/notificacao"})
public class NotificacaoController extends HttpServlet {
    private final NotificacaoService notificacaoService = new NotificacaoService();
}
