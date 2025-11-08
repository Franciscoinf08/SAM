package sam.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.service.AcessosBlockService;

@WebServlet(name = "AcessosBlockController", urlPatterns = {"/AcessosBlockController"})
public class AcessosBlockController extends HttpServlet {

    private static AcessosBlockService bloqueios;
    private String jsp = "";

    static {
        bloqueios = new AcessosBlockService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");

        switch (acao) {
            case "Ativar":
                jsp = this.ativar(request);
                break;
            case "Bloquear":
                jsp = this.bloquear(request);
                break;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }

    public String ativar(HttpServletRequest request) {
        String jsp = "";
        try {
            String usuario = (String) request.getParameter("usuario");
            String recurso = (String) request.getParameter("recurso");
            bloqueios.ativar(recurso, usuario);
            jsp = "/core/dev/visualizar-usuarios.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

    public String bloquear(HttpServletRequest request) {
        String jsp = "";
        try {
            String usuario = (String) request.getParameter("usuario");
            String recurso = (String) request.getParameter("recurso");
            bloqueios.bloquear(recurso, usuario);
            jsp = "/core/dev/visualizar-usuarios.jsp";
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
