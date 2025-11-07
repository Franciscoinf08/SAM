package sam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sam.model.dao.EmpresaDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProgramaFidelidadeController", urlPatterns = {"/programaFidelidade"})
public class ProgramaFidelidadeController extends HttpServlet{

    ProgramaFidelidadeDAO dao = new ProgramaFidelidadeDAO();
    EmpresaDAO empresaDAO = new EmpresaDAO();
    List<ProgramaFidelidade> fidelidades = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try{
            switch(action){
                case "listar":

                    break;
                case "excluir":
                    excluirProgramaFidelidade(request, response);
                    break;
                case "novo":
                    mostrarFormulario(request, response);
                    break;
                case "editar":

                    mostrarFormularioEdicao(request, response);
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("editar")) {
            editarProgramaFidelidade(request, response);
        }
        else {
            try {
                cadastrarProgramaFidelidade(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void cadastrarProgramaFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String nome = request.getParameter("nome");
        Double bonusMilhas = Double.valueOf(request.getParameter("bonusMilhas"));
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        int qtdeMilhasMes = Integer.parseInt(request.getParameter("qtdeMilhasMes"));
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        double preco = Double.parseDouble(request.getParameter("preco"));

        ProgramaFidelidade programaFidelidade = new ProgramaFidelidade(nome, bonusMilhas, duracao, qtdeMilhasMes, idEmpresa, preco);
        try {
            dao.salvar(programaFidelidade);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void editarProgramaFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProgramaFidelidade programaFidelidade = dao.buscarPorId(id);
        request.setAttribute("programaFidelidade", programaFidelidade);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/formularioProgramaFidelidade.jsp");

        response.sendRedirect(request.getContextPath() + "/programaFidelidade");
        dispatcher.forward(request,response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empresa> empresas = empresaDAO.listarTodas();
        request.setAttribute("empresas", empresas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/core/gestor/formularioProgramaFidelidade.jsp");
        dispatcher.forward(request,response);
    }
    private void excluirProgramaFidelidade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.excluirProgramaFidelidade(id);
        response.sendRedirect(request.getContextPath() + "/programaFidelidade");
    }
}
