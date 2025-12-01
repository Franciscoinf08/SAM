package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/UserVerifyController")
public class UserVerifyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");

            EnviarEmailController sm =  new EnviarEmailController();

            HttpSession session = request.getSession();

            session.setAttribute("nome", request.getParameter("nome"));
            session.setAttribute("email", request.getParameter("email"));
            session.setAttribute("cpf", request.getParameter("cpf"));
            session.setAttribute("senha", request.getParameter("senha"));

            String codigo = sm.getRandom();
            session.setAttribute("codigoVerificacao", codigo);

            String assunto = "Verificacao de email de usuário";
            String mensagem = "Registro realizado com sucesso. Por favor verifique sua conta usando o codigo: ";

            boolean test = sm.enviarEmail(email, codigo, assunto, mensagem);

            if (test) {
                request.setAttribute("mensagem", "Código enviado! Confira seu e-mail.");
                request.getRequestDispatcher("core/geral/verificar.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
