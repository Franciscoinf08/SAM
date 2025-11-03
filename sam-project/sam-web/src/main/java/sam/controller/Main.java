package sam.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "name", urlPatterns = {"/main"})
public class Main extends HttpServlet {
    private String jsp = "/";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");


        switch (acao) {
            case "logar":
                jsp = LoginController.logar(request);

            case "cadastrar":
                jsp = CadastroController.cadastrar(request);

            case "alterarPerfil":
                jsp = AlteracaoPerfilController.alterar(request);
        }

        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}
