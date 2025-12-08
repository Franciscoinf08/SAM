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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String action = request.getParameter("action");
        if("excluir".equals(action)) {
            excluir(request, response);
            return;
        }
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int idFormulario = Integer.parseInt(idStr);

                FormObjetivos formParaEditar = FormObjetivosDao.buscarPorId(idFormulario);

                if (formParaEditar != null) {
                    request.setAttribute("formulario", formParaEditar);
                } else {
                    request.setAttribute("erro", "Formulário não encontrado para o ID: " + idStr);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "ID de formulário inválido.");
            }
        }

        request.getRequestDispatcher("/core/cliente/objetivos.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idFormularioStr = request.getParameter("id");
        boolean isUpdate = idFormularioStr != null && !idFormularioStr.isEmpty();


        FormObjetivos formObjetivos = new FormObjetivos();

        LocalDate dataDoEnvio = LocalDate.now();
        Long idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getId();

        if (isUpdate) {
            try {
                // Assume que o ID no seu Model é Integer (int)
                formObjetivos.setId(Integer.parseInt(idFormularioStr));
            } catch (NumberFormatException e) {
                // Erro se o ID não for um número válido, trata como falha.
                System.err.println("ID de formulário inválido para atualização.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de formulário inválido.");
                return;
            }
        }

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

        boolean sucesso;
        if (isUpdate) {
            sucesso = FormObjetivosDao.atualizar(formObjetivos);
        } else {
            sucesso = FormObjetivosDao.inserir(formObjetivos);
        }

        // 4. Redirecionamento (igual ao seu código)
        if (sucesso) {
            String contextPath = request.getContextPath();
            // Redireciona para a página de sucesso
            response.sendRedirect(contextPath + "/core/cliente/sucesso.jsp");
        } else {
            request.setAttribute("erro", "Erro ao salvar os objetivos. Tente novamente.");
            request.getRequestDispatcher("/core/cliente/objetivos.jsp").forward(request, response);
        }
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Long idUsuario = usuario.getId();
        FormObjetivosDao.excluir(id, idUsuario);
        response.sendRedirect(request.getContextPath()+ "/core/cliente/selecao-formularios.jsp");

    }
}