package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import sam.model.common.Conexao;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.dao.UsuarioProgramaDAO;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.Usuario;
import sam.model.domain.UsuarioPrograma;
import sam.model.service.GestaoUsuariosService;
import sam.model.service.ProgramaFidelidadeService;
import sam.model.service.UsuarioProgramaService;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="UsuarioProgramaController", urlPatterns = {"/usuarioPrograma"})
public class UsuarioProgramaController extends HttpServlet {
    GestaoUsuariosService usuariosService = new GestaoUsuariosService();
    ProgramaFidelidadeService programaFidelidadeService = new ProgramaFidelidadeService();
    UsuarioProgramaService usuarioProgramaService = new UsuarioProgramaService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("programas")) {
            try {
                listarProgramas(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            listarClientes(request, response);
        }

    }

    private void listarProgramas(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        String idUsuario = request.getParameter("idUsuario");
        List<ProgramaFidelidade> lista = usuarioProgramaService.listarNaoAssociados(Integer.parseInt(idUsuario));
        request.setAttribute("listaDisponiveis", lista);
        request.setAttribute("idUsuario", idUsuario);
        lista = usuarioProgramaService.listarAssociados(Integer.parseInt(idUsuario));
        request.setAttribute("listaAssociados", lista);

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
        String action = request.getParameter("action");
        if (action.equals("associar")) {
            associar(request, response);
        }else {
            excluir(request, response);
        }
    }

    private void associar(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        String idUsuario = request.getParameter("idUsuario");
        String idPrograma = request.getParameter("idPrograma");
        UsuarioPrograma usuarioPrograma = new UsuarioPrograma(
                Integer.parseInt(idUsuario),
                Integer.parseInt(idPrograma)
        );
        try {
            usuarioProgramaService.associar(usuarioPrograma, Math.toIntExact(usuario.getId()));
            response.sendRedirect(request.getContextPath() + "/usuarioPrograma?action=programas&idUsuario=" + idUsuario);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        List<Usuario> lista;
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
        String idUsuario = request.getParameter("idUsuario");
        String idPrograma = request.getParameter("idPrograma");

        UsuarioPrograma usuarioPrograma = new UsuarioPrograma(Integer.parseInt(idUsuario), Integer.parseInt(idPrograma));

        try {
            usuarioProgramaService.desassociar(usuarioPrograma, Integer.parseInt(idUsuario));
            response.sendRedirect(request.getContextPath() + "/usuarioPrograma?action=programas&idUsuario=" + idUsuario);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
