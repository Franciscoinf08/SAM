package sam.controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sam.model.service.*;

import java.io.IOException;
@WebServlet(name="feedbackdenuncia", urlPatterns = {"/feedbackdenuncia"})
public class FeedbackDenunciaControler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/core/geral/avaliacoes.jsp");
        rd.forward(request, response);
    }
}
