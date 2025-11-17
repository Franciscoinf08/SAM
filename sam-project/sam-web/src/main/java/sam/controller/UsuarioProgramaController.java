package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="UsuarioProgramaController", urlPatterns = {"/usuarioPrograma"})
public class UsuarioProgramaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "novo":
                mostrarFormulario(request,response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                listarClentesAssociados(request,response);
                break;
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int idPrograma = Integer.parseInt(request.getParameter("idPrograma"));
        String numeroAdesao = request.getParameter("numeroAdesao");
        double saldoMilhas = Double.parseDouble(request.getParameter("saldoMilhas"));

    }

    private void associar(HttpServletRequest request, HttpServletResponse response) {


    }

    private void listarClentesAssociados(HttpServletRequest request, HttpServletResponse response) {
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) {
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) {
    }
}
