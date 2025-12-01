package sam.model.service;

import sam.model.dao.Conexao;

import sam.model.dao.EmpresaDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.ProgramaFidelidade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProgramaFidelidadeService {
    private ProgramaFidelidadeDAO programaFidelidadeDAO = new ProgramaFidelidadeDAO();
    private final Connection conexao;
    public ProgramaFidelidadeService() {
        this.conexao = Conexao.getConnection();
    }

    public void cadastrarProgramaFidelidade(ProgramaFidelidade p){
        if(validarProgramaFidelidade(p)){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }
        programaFidelidadeDAO.salvar(p);
    }
    public void atualizarProgramaFidelidade(ProgramaFidelidade p){
        if(validarProgramaFidelidade(p)){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }

        try {
            programaFidelidadeDAO.atualizarProgramaFidelidade(p);
        } catch (SQLException e){
            e.getMessage();
        }
    }

    public ProgramaFidelidade buscarProgramaFidelidade(int id){
        if (validarProgramaFidelidade(programaFidelidadeDAO.buscarPorId(id))) {
            throw  new RuntimeException("Programa de Fidelidade nao e valido");
        }
        return programaFidelidadeDAO.buscarPorId(id);
    }
    public void deletarProgramaFidelidade(int id){
        if(validarProgramaFidelidade(programaFidelidadeDAO.buscarPorId(id))){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }

        try{
            programaFidelidadeDAO.excluirProgramaFidelidade(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar programa de Fidelidade.");
        }
    }
    public List<ProgramaFidelidade> listarProgramaFidelidadePorEmpresa(int idEmpresa){
        EmpresaDAO empresaDAO = new EmpresaDAO();
        if(empresaDAO.buscarPorId(idEmpresa) == null){
            throw new RuntimeException("Empresa nao encontrado");
        }
        return programaFidelidadeDAO.listarPorEmpresa(idEmpresa);
    }

    private boolean validarProgramaFidelidade(ProgramaFidelidade p){
        if(p == null || p.getNome() == null) return true;

        if(p.getPrecoMensal() <= 0) return true;

        return p.getDuracao() == 0 || p.getQtdeMilhasMes() == 0 || p.getIdEmpresa() <= 0;
    }

    public List<ProgramaFidelidade> listarTodosProgramaFidelidade() {

        return programaFidelidadeDAO.listarTodos();

    }
}