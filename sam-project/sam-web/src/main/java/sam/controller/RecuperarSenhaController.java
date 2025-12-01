package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/RecuperarSenhaController")
public class RecuperarSenhaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");

            EnviarEmailController sm =  new EnviarEmailController();

            HttpSession session = request.getSession();

            session.setAttribute("emailRecuperacao", email);

            String codigo = sm.getRandom();
            session.setAttribute("codigoVerificacao", codigo);

            String assunto = "Recuperação de Senha";
            String mensagem = "Para recuperar sua senha por favor utilize o seguinte codigo: ";

            boolean test = sm.enviarEmail(email, codigo, mensagem, assunto);

            if (test) {
                request.setAttribute("mensagem", "Código enviado! Confira seu e-mail.");
                request.setAttribute("emailEnviado", true);
                request.getRequestDispatcher("core/geral/esqueceu-senha.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
