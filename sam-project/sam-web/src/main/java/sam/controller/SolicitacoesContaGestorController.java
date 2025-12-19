package sam.controller;

import jakarta.servlet.http.Part;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import sam.model.helper.EnviarEmailHelper;
import sam.model.service.GestaoSolicitacoesService;
import sam.model.domain.Solicitacao;
import sam.model.domain.util.Status;

@MultipartConfig
@WebServlet(name = "SolicitacoesContaGestorController", urlPatterns = {"/solicitarGestor"})
public class SolicitacoesContaGestorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        String jsp = "";

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
            case "EmailSolicitar":
                jsp = this.email(request);
                break;
            case "TornarCliente":
                jsp = this.tornarCliente(request);
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }

    public String pedir(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String pagamento = request.getParameter("formaPagamento");
            Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));

            if (pagamento == null || pagamento.trim().isEmpty()) {
                throw new RuntimeException("Pagamento recebido nulo/vazio");
            }

            Solicitacao sol = new Solicitacao();
            sol.setNome(nome);
            sol.setEmail(email);
            sol.setPagamento(pagamento);
            sol.setStatus(Status.PENDENTE);
            sol.setIdUsuario(idUsuario);
            gestao.adicionarPedido(sol);
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
            gestao.cancelarPedido(id);
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
            gestao.aprovarPedido(id);
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
            gestao.recusarPedido(id);
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
            gestao.solicitarPagamento(id);

            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String formaPagamento = request.getParameter("formaPagamento");

            Part arquivoPart = (Part) request.getPart("arquivo");

            String contentDisposition = arquivoPart.getHeader("content-disposition");
            String nomeArquivo = null;
            if (contentDisposition != null) {
                for (String cd : contentDisposition.split(";")) {
                    cd = cd.trim();
                    if (cd.startsWith("filename")) {
                        nomeArquivo = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                        break;
                    }
                }
            }
            if (nomeArquivo == null || nomeArquivo.isEmpty()) {
                nomeArquivo = "anexo-" + System.currentTimeMillis();
            }

            String caminho = getServletContext().getRealPath("/uploads");
            File uploads = new File(caminho);
            if (!uploads.exists()) {
                uploads.mkdirs();
            }

            File arquivoSalvo = new File(uploads, nomeArquivo);

            try (InputStream is = arquivoPart.getInputStream()) {
                Files.copy(is, arquivoSalvo.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            EnviarEmailHelper sm = new EnviarEmailHelper();
            boolean sucesso = sm.enviarEmailSolicitacaoGestor(nome, email, formaPagamento, arquivoSalvo.getAbsolutePath());

            request.setAttribute("sucesso", String.valueOf(sucesso));
            jsp = "/core/dev/gerenciar-solicitacoes.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

    public String email(HttpServletRequest request) {
        String jsp;
        try {
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            jsp = "/core/dev/email-solicitacao.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

    public String tornarCliente(HttpServletRequest request) {
        String jsp;
        try {
            GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
            String id = request.getParameter("id");
            gestao.tornarCliente(id);

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
