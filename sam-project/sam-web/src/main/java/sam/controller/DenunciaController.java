package sam.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sam.model.service.*;

import java.io.IOException;

@WebServlet(name="denuncia", urlPatterns = {"/denuncia"})
public class DenunciaController extends HttpServlet {

    private final DenunciaService denunciaService =  new DenunciaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long idDenunciante = Long.parseLong(request.getParameter("idDenunciante"));
            Long idDenunciado = Long.parseLong(request.getParameter("idDenunciado"));
            String motivo = request.getParameter("motivo");
            String detalhes = request.getParameter("detalhes");

            denunciaService.registrarDenuncia(idDenunciante, idDenunciado, motivo, detalhes);

            response.sendRedirect("views/avaliacoes/avaliacoes.jsp?sucesso=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("views/avaliacoes/avaliacoes.jsp?erro=1");
        }
    }
}
