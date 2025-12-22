package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/VerificarCodigoController")
public class VerificarCodigoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String codigoSessao = (String) session.getAttribute("codigoVerificacao");

        String codigoDigitado =
                request.getParameter("d1") +
                        request.getParameter("d2") +
                        request.getParameter("d3") +
                        request.getParameter("d4") +
                        request.getParameter("d5") +
                        request.getParameter("d6");

        String destinoSucesso = request.getParameter("destinoSucesso");
        String destinoFalha = request.getParameter("destinoFalha");

        if (codigoSessao != null && codigoDigitado.equals(codigoSessao)) {

            session.removeAttribute("codigoVerificacao");

            session.setAttribute("codigoValidado", true);

            request.getRequestDispatcher(destinoSucesso).forward(request, response);
        }else {
            request.setAttribute("erro", "Código incorreto. Tente novamente.");
            request.setAttribute("emailEnviado", true);

            request.getRequestDispatcher(destinoFalha).forward(request, response);
        }
    }
}
