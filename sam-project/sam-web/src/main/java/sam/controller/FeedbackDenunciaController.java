package sam.controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name="feedbackdenuncia", urlPatterns = {"/feedbackdenuncia"})
public class FeedbackDenunciaController extends HttpServlet {
    DenunciaController denunciaController = new DenunciaController();
    FeedbackController feedbackController = new FeedbackController();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "denuncia":
                        // lógica futura
                        break;
                    case "feedback":
                        // lógica futura
                        break;
                    default:
                        break;
                }
            }
            request.getRequestDispatcher("/core/geral/avaliacoes.jsp").forward(request, response);
        }catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("erro", e.getMessage());
        request.getRequestDispatcher("/core/gestor/avaliacoes.jsp").forward(request, response);
        return;
        }



    }
}
