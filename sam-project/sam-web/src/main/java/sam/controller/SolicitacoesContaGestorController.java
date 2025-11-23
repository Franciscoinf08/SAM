package sam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import sam.model.service.GestaoSolicitacoesService;
import sam.model.domain.Solicitacao;

@WebServlet(name = "SolicitacoesContaGestorController", urlPatterns = {"/solicitarGestor"})
public class SolicitacoesContaGestorController extends HttpServlet {
    private String jsp = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");

        switch (acao) {
            case "Pedir":
                jsp = this.pedir(request);
                break;
            case "Cancelar":
                jsp = this.cancelar(request);
                break;
            case "Aprovar":
                jsp = this.aprovar(request);
                break;
            case "Recusar":
                jsp = this.recusar(request);
                break;
            case "Pagamento":
                jsp = this.pagamento(request);
                break;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }

    public String pedir(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String nome = (String) request.getParameter("nome");
            String email = (String) request.getParameter("email");
            String pagamento = (String) request.getParameter("forma-pagamento");
            Solicitacao sol = new Solicitacao(nome, email, pagamento);
            gestao.adicionarPedido(sol); // RECEBE SOLICITACAO
            jsp = "/core/cliente/lista-solicitacoes.jsp";        
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

    public String cancelar(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String id = request.getParameter("id");
            gestao.cancelarPedido(id); // RECEBE STRING E CONVERTE PARA INT
            jsp = "/core/cliente/lista-solicitacoes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String aprovar(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String id = request.getParameter("id");
            gestao.aprovarPedido(id); // RECEBE STRING E CONVERTE PARA INT
            jsp = "/core/dev/gerenciar-solicitacoes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String recusar(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String id = request.getParameter("id");
            gestao.recusarPedido(id); // RECEBE STRING E CONVERTE PARA INT
            jsp = "/core/dev/gerenciar-solicitacoes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String pagamento(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String id = request.getParameter("id");
            gestao.solicitarPagamento(id); // RECEBE STRING E CONVERTE PARA INT
            jsp = "/core/dev/gerenciar-solicitacoes.jsp";
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
