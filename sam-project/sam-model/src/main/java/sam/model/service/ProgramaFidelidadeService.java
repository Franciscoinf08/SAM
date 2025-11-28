package sam.model.service;

import sam.model.dao.Conexao;
import sam.model.dao.EmpresaDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.ProgramaFidelidade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProgramaFidelidadeService {



    public void cadastrarProgramaFidelidade(ProgramaFidelidade p){
        if(validarProgramaFidelidade(p)){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            conexao.setAutoCommit(false);
            ProgramaFidelidadeDAO programaFidelidadeDAO = new ProgramaFidelidadeDAO(conexao);
            programaFidelidadeDAO.salvar(p);
            conexao.commit();

        } catch (SQLException e) {
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {
                }
            }
            throw new RuntimeException("Erro ao cadastrar o programa de fidelidade.", e);

        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    // Logar erro ao fechar
                }
            }
        }
    }
    public void atualizarProgramaFidelidade(ProgramaFidelidade p){
        if(validarProgramaFidelidade(p)){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            conexao.setAutoCommit(false);
            ProgramaFidelidadeDAO programaFidelidadeDAO = new ProgramaFidelidadeDAO(conexao);
            programaFidelidadeDAO.atualizarProgramaFidelidade(p);
            conexao.commit();

        } catch (SQLException e) {
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {
                }
            }
            throw new RuntimeException("Erro ao atualizar o programa de fidelidade.", e);

        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    // Logar erro ao fechar
                }
            }
        }
    }

    public ProgramaFidelidade buscarProgramaFidelidade(int id){
        Connection conexao = null;
        ProgramaFidelidade pf = null;
        try {
            conexao = Conexao.getConnection();
            ProgramaFidelidadeDAO programaFidelidadeDAO = new ProgramaFidelidadeDAO(conexao);
            pf = programaFidelidadeDAO.buscarPorId(id);
            if (validarProgramaFidelidade(pf)) {
                throw new RuntimeException("Programa de Fidelidade nao e valido");
            }
            pf = programaFidelidadeDAO.buscarPorId(id);

        }finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    // Logar erro ao fechar
                }
            }
        }

        return pf;
    }

    public void deletarProgramaFidelidade(int id){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            conexao.setAutoCommit(false);
            ProgramaFidelidadeDAO programaFidelidadeDAO = new ProgramaFidelidadeDAO(conexao);
            programaFidelidadeDAO.excluirProgramaFidelidade(id);
            conexao.commit();

        } catch (SQLException e) {
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {
                }
            }
            throw new RuntimeException("Erro ao atualizar o programa de fidelidade.", e);

        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    // Logar erro ao fechar
                }
            }
        }
    }
    public List<ProgramaFidelidade> listarProgramaFidelidadePorEmpresa(int idEmpresa){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);
            ProgramaFidelidadeDAO pfDAO = new ProgramaFidelidadeDAO(conexao);
            if(empresaDAO.buscarPorId(idEmpresa) == null){
                throw new RuntimeException("Empresa nao encontrado");
            }
            return pfDAO.listarPorEmpresa(idEmpresa);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }
    public List<ProgramaFidelidade> listarTodosProgramaFidelidade(){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            ProgramaFidelidadeDAO pfDAO = new ProgramaFidelidadeDAO(conexao);
            return pfDAO.listarTodos();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ignored) {}
            }
        }
    }
    private boolean validarProgramaFidelidade(ProgramaFidelidade p){
        if(p == null || p.getNome() == null) return true;

        if(p.getPrecoMensal() <= 0) return true;

        return p.getDuracao() == 0 || p.getQtdeMilhasMes() == 0;
    }
}
