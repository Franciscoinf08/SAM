package sam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.dao.FormObjetivosDao;
import sam.model.domain.FormObjetivos;

import java.io.IOException;

@WebServlet("/VisualizarFormularioController")
public class VisualizarFormularioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idFormulario = Integer.parseInt(request.getParameter("id"));

            FormObjetivos form = FormObjetivosDao.buscarPorId(idFormulario);

            if (form == null) {
                response.sendRedirect("/core/mensagens-erro.jsp");
                return;
            }

            request.setAttribute("formulario", form);
            request.getRequestDispatcher("/core/gestor/visualizar-formulario.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/core/mensagens-erro.jsp");
        }
    }
}
