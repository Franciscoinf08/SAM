package sam.ajax;

import sam.model.domain.ProgramaFidelidade;
import sam.model.service.ProgramaFidelidadeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PesquisarProgramasAjax", urlPatterns = {"/PesquisarProgramasAjax"})
public class PesquisarProgramasAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long idCliente = Long.parseLong(request.getParameter("idCliente"));

        ProgramaFidelidadeService manterPrograma = new ProgramaFidelidadeService();

        try {
            List<ProgramaFidelidade> listaProgramas = manterPrograma.listarPorCliente(idCliente);

            StringBuilder json = new StringBuilder("[");

            for (int i = 0; i < listaProgramas.size(); i++) {
                ProgramaFidelidade programa = listaProgramas.get(i);
                json.append("{\"id\":").append(programa.getIdProgramaFidelidade())
                        .append(",\"nome\":\"").append(programa.getNome()).append("\"}");
                if (i < listaProgramas.size() - 1) json.append(",");
            }
            json.append("]");

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(json.toString());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
