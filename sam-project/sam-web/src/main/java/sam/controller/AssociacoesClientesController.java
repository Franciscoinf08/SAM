package sam.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.service.GestaoAssociacoesClientesService;
import sam.model.domain.AssociacaoCliente;
import sam.model.domain.util.AssociacaoClienteTipo;

@WebServlet(name = "AssociacoesController", urlPatterns = {"/associacoes"})
public class AssociacoesClientesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        String jsp = "";

        switch (acao) {
            case "Adicionar": // GESTOR QUER ADICIONAR UM CLIENTE COMO SEU 
                jsp = this.adicionar(request);
                break;
            case "Remover": // GESTOR QUER PARAR DE GERENCIAR UM CLIENTE
                jsp = this.remover(request);
                break;
            case "Aprovar": // CLIENTE QUER APROVAR UM PEDIDO PARA SER GERENCIADO
                jsp = this.aprovar(request);
                break;
            case "Recusar": // CLIENTE QUER RECUSAR UM PEDIDO PARA SER GERENCIADO
                jsp = this.recusar(request);
                break;
            case "ListarDisponiveis": // GESTOR QUER VER CLIENTES QUE AINDA ESTÃO DISPONÍVEIS PARA ASSOCIAÇÃO
                jsp = this.listar(request);
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
    
    public String adicionar(HttpServletRequest request) {
        String jsp;
        try {
            Long idCliente = Long.valueOf(request.getParameter("idCliente"));
            Long idGestor = Long.valueOf(request.getParameter("idGestor"));
            
            AssociacaoCliente associacao = new AssociacaoCliente(idCliente, idGestor, AssociacaoClienteTipo.ASSOCIAR);
            
            GestaoAssociacoesClientesService gestao = new GestaoAssociacoesClientesService();
            gestao.adicionarPedido(associacao);
            
            jsp = "/core/gestor/meus-clientes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String remover(HttpServletRequest request) {
        String jsp;
        try {
            Long idCliente = Long.valueOf(request.getParameter("idCliente"));
            Long idGestor = Long.valueOf(request.getParameter("idGestor"));
            
            AssociacaoCliente associacao = new AssociacaoCliente(idCliente, idGestor, AssociacaoClienteTipo.DESASSOCIAR);
            
            GestaoAssociacoesClientesService gestao = new GestaoAssociacoesClientesService();
            gestao.adicionarPedido(associacao);
            
            jsp = "/core/gestor/meus-clientes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String aprovar(HttpServletRequest request) {
        String jsp;
        try {
            Long idCliente = Long.valueOf(request.getParameter("idCliente"));
            Long idGestor = Long.valueOf(request.getParameter("idGestor"));
            String tipo = request.getParameter("tipo");
            
            GestaoAssociacoesClientesService gestao = new GestaoAssociacoesClientesService();
            gestao.aprovar(idCliente, idGestor, tipo);
            
            jsp = "/core/geral/inicio.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String recusar(HttpServletRequest request) {
        String jsp;
        try {
            Long idPedido = Long.valueOf(request.getParameter("idPedido"));
            GestaoAssociacoesClientesService gestao = new GestaoAssociacoesClientesService();
            gestao.recusar(idPedido);
            jsp = "/core/geral/inicio.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
    public String listar(HttpServletRequest request) {
        String jsp;
        try {
            jsp = "/core/gestor/listaClientesDisponiveis.jsp";
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
