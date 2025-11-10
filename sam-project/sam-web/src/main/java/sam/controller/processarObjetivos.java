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
import sam.model.domain.Usuario;

@WebServlet(name = "processarObjetivos", urlPatterns = "/processarObjetivos")
public class processarObjetivos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormObjetivos formObjetivos = new FormObjetivos();

        LocalDate dataDoEnvio = LocalDate.now();
        Long idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getId();

        formObjetivos.setId_usuario(idUsuario);
        formObjetivos.setTitulo(request.getParameter("titulo"));
        formObjetivos.setData(dataDoEnvio);

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
            String contextPath = request.getContextPath();
            String urlCompleta = contextPath + "/core/cliente/sucesso.jsp";
            System.out.println("DEBUG REDIRECT: " + urlCompleta);

            response.sendRedirect(urlCompleta);
        } else {
            request.setAttribute("erro", "Erro ao salvar os objetivos. Tente novamente.");
            request.getRequestDispatcher("/core/cliente/objetivos.jsp").forward(request, response);
        }
    }
}