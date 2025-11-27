package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/sam/UserVerifyController")
public class UserVerifyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");

            EnviarEmailController sm =  new EnviarEmailController();
            String codigo = sm.getRandom();

            HttpSession session = request.getSession();
            session.setAttribute("codigoVerificacao", codigo);

            boolean test = sm.enviarEmail(email, codigo);

            if (test) {
                request.setAttribute("mensagem", "Código enviado! Confira seu e-mail.");
                request.getRequestDispatcher("verificar.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
