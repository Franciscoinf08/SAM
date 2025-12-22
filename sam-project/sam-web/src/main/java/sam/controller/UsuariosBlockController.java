package sam.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.service.UsuariosBlockService;

@WebServlet(name = "UsuariosBlockController", urlPatterns = {"/userBlock"})
public class UsuariosBlockController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        String jsp = "";

        switch (acao) {
            case "Bloquear":
                jsp = this.bloquear(request);
                break;
            case "Desbloquear":
                jsp = this.desbloquear(request);
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
    
    
    public String bloquear(HttpServletRequest request) {
        String jsp;
        try {
            Long idVisitado = Long.valueOf(request.getParameter("idVisitado")); //ID A SER BLOQUEADO
            Long idUsuario = Long.valueOf(request.getParameter("idUsuario")); // ID A BLOQUEAR
            
            UsuariosBlockService gestao = new UsuariosBlockService();
            gestao.bloquear(idUsuario, idVisitado);
            
            request.setAttribute("id", idVisitado);
            jsp = "/core/geral/perfil-visitado.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String desbloquear(HttpServletRequest request) {
        String jsp;
        try {
            Long idVisitado = Long.valueOf(request.getParameter("idVisitado")); //ID A SER DESBLOQUEADO
            Long idUsuario = Long.valueOf(request.getParameter("idUsuario")); // ID A DESBLOQUEAR
            
            UsuariosBlockService gestao = new UsuariosBlockService();
            gestao.desbloquear(idUsuario, idVisitado);
            
            request.setAttribute("id", idVisitado);
            jsp = "/core/geral/perfil-visitado.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
