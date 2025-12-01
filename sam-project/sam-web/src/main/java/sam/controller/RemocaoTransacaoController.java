package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;
import sam.model.service.GestaoTransacoesService;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RemocaoTransacaoController", urlPatterns = {"/RemocaoTransacaoController"})
public class RemocaoTransacaoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        System.out.println("Parameters received:");
        System.out.println();
        for (Map.Entry<String, String[]> e : params.entrySet()) {
            System.out.print("  " + e.getKey() + " = ");
            String[] vals = e.getValue();
            for (int i = 0; i < vals.length; i++) {
                System.out.print(vals[i] + (i < vals.length-1 ? ", " : ""));
            }
            System.out.println();
        }
        System.out.println();

        String path = null;
        try {
            if (((Usuario) request.getSession().getAttribute("usuario")).getTipo() == UsuarioTipo.CLIENTE)
                path = "/core/cliente/dashboard.jsp";
            else
                path = "/core/gestor/apuracao.jsp";
        } catch (NullPointerException e) {
            RequestDispatcher rd = request.getRequestDispatcher("");
            rd.forward(request, response);
        }

        Long id;

        try {
            id = Long.parseLong(request.getParameter("remover"));
        } catch (RuntimeException e) {
            e.printStackTrace();

            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
            return;
        }

        GestaoTransacoesService manterTransacao = new GestaoTransacoesService();

        try {
            manterTransacao.remover(id);
        } catch (PersistenciaException e) {
            e.printStackTrace();
            String erro = e.getLocalizedMessage();
            request.setAttribute("erro", erro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
    }
}
