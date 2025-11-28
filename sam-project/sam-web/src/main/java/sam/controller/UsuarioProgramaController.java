package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sam.model.dao.Conexao;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.dao.UsuarioProgramaDAO;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.Usuario;
import sam.model.domain.UsuarioPrograma;
import sam.model.service.GestaoUsuariosService;
import sam.model.service.ProgramaFidelidadeService;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="UsuarioProgramaController", urlPatterns = {"/usuarioPrograma"})
public class UsuarioProgramaController extends HttpServlet {
    GestaoUsuariosService usuariosService = new GestaoUsuariosService();
    ProgramaFidelidadeService programaFidelidadeService = new ProgramaFidelidadeService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "programas":
                listarProgramas(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                listarClientesAssociados(request,response);
                break;
        }

    }

    private void listarProgramas(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<ProgramaFidelidade> lista = programaFidelidadeService.listarTodosProgramaFidelidade();
        request.setAttribute("lista", lista);
        String idUsuario = request.getParameter("idUsuario");
        request.setAttribute("idUsuario", idUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/selecaoProgramas.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            associar(request, response);


    }

    private void associar(HttpServletRequest request, HttpServletResponse response) {
        String idUsuario = request.getParameter("idUsuario");
        String idPrograma = request.getParameter("idPrograma");

        System.out.println(">>> idUsuario recebido: " + idUsuario);
        System.out.println(">>> idPrograma recebido: " + idPrograma);

        UsuarioPrograma usuarioPrograma = new UsuarioPrograma(
                Integer.parseInt(idUsuario),
                Integer.parseInt(idPrograma)
        );
        Connection conexao = null;
        conexao = Conexao.getConnection();
        UsuarioProgramaDAO dao = new UsuarioProgramaDAO(conexao);
        try {
            dao.associar(usuarioPrograma);

            System.out.println(usuarioPrograma.getIdUsuario()+" " +  usuarioPrograma.getIdPrograma() + " " + usuarioPrograma.getSaldoMilhas());
            response.sendRedirect(request.getContextPath() + "/usuarioPrograma");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void listarClientesAssociados(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        List<Usuario> lista = null;
        try {
            lista = usuariosService.getListaClientes(usuario);
            request.setAttribute("clientes", lista);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/meus-clientes.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) {
    }

}
