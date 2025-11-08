package sam.controller;

import java.time.LocalDate;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sam.model.domain.FormObjetivos;
import sam.model.dao.FormObjetivosDao;

@WebServlet(name = "processarObjetivos", urlPatterns = "/processarObjetivos")
public class processarObjetivos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormObjetivos formObjetivos = new FormObjetivos();

        formObjetivos.setNome(request.getParameter("nome"));
        formObjetivos.setEmail(request.getParameter("email"));

        String dataStr = request.getParameter("data_ultima_atualizacao");
        if (dataStr != null && !dataStr.isEmpty()) {
            try {
                formObjetivos.setData(LocalDate.parse(dataStr));
            } catch (Exception e) {
                System.err.println("Erro ao converter data: " + e.getMessage());
            }
        }

        formObjetivos.setObjetivosGerais(request.getParameter("objetivos_gerais"));
        formObjetivos.setObjetivosEspecificos(request.getParameter("objetivos_especificos"));
        formObjetivos.setDestPrincipal(request.getParameter("destino_principal"));

        String numPessoasStr = request.getParameter("num_pessoas");
        if (numPessoasStr != null && !numPessoasStr.isEmpty()) {
            try {
                formObjetivos.setNumPessoas(Integer.parseInt(numPessoasStr));
            } catch (NumberFormatException e) {
                System.err.println("Erro na conversão de 'num_pessoas': " + e.getMessage());
            }
        }

        formObjetivos.setPrefCompanhia(request.getParameter("companhias_aereas"));

        String orcTotalStr = request.getParameter("orcamento_total");
        if (orcTotalStr != null && !orcTotalStr.isEmpty()) {
            try {
                formObjetivos.setOrcTotal(Float.parseFloat(orcTotalStr));
            } catch (NumberFormatException e) {
                System.err.println("Erro na conversão de 'orcamento_total': " + e.getMessage());
            }
        }

        formObjetivos.setNivelDetalhamento(request.getParameter("detalhamento_orcamento"));
        formObjetivos.setReqEspecificos(request.getParameter("outros_requisitos_viagem"));

        boolean sucesso = FormObjetivosDao.inserir(formObjetivos);

        if (sucesso) {
            response.sendRedirect("sucesso.jsp");
        } else {
            request.setAttribute("mensagemErro", "Erro ao salvar os objetivos. Tente novamente.");
            request.getRequestDispatcher("/seuFormulario.jsp").forward(request, response);
        }

    }
}