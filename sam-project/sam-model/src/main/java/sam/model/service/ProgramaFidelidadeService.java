package sam.model.service;

import sam.model.common.Conexao;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.EmpresaDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.util.TipoAtividades;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProgramaFidelidadeService {
    private ProgramaFidelidadeDAO programaFidelidadeDAO = new ProgramaFidelidadeDAO();
    private final AtividadeService atividadeService = new AtividadeService();
    private final Connection conexao;
    private final EmpresaService empresaService= new EmpresaService();
    public ProgramaFidelidadeService() {
        this.conexao = Conexao.getConnection();
    }

    public void cadastrarProgramaFidelidade(ProgramaFidelidade p, int usuarioExecutor){
        if(validarProgramaFidelidade(p)){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }
        programaFidelidadeDAO.salvar(p);
        try {
            atividadeService.registrarAtividade(TipoAtividades.CADASTRO_PROGRAMA, "o programa de fidelidade: " + p.getNome() + "foi cadastrado na empresa: "
                    + empresaService.buscarEmpresa(p.getIdEmpresa()).getNome(), (long) usuarioExecutor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void atualizarProgramaFidelidade(ProgramaFidelidade p, int usuarioExecutor){
        if(validarProgramaFidelidade(p)){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }
        ProgramaFidelidade pa = buscarProgramaFidelidade(p.getIdProgramaFidelidade());
        String descricao = descricaoAlteracao(pa, p);

        try {
            programaFidelidadeDAO.atualizarProgramaFidelidade(p);
            try{
                atividadeService.registrarAtividade(TipoAtividades.EDICAO_PROGRAMA, descricao, (long) usuarioExecutor);

            } catch (SQLException e){
                throw new RuntimeException("erro ao registrar atividade" + e.getMessage(), e);
            }
        } catch (SQLException e){
            throw new RuntimeException("erro ao atualizar programa" + e.getMessage(), e);
        }
    }

    public String descricaoAlteracao(ProgramaFidelidade pa, ProgramaFidelidade p){
        String nomeA = pa.getNome();
        double bonusMilhasA = pa.getBonusMilhas();
        int qtdeMilhasMesA = pa.getQtdeMilhasMes();
        int duracaoA = pa.getDuracao();
        double precoMensalA = pa.getPrecoMensal();
        Date dataExpiracaoMilhasA = pa.getDataExpiracaoMilhas();

        String descricao = "o programa de fidelidade: " + pa.getNome() + "da empresa: "
                + empresaService.buscarEmpresa(p.getIdEmpresa()).getNome() +"foi alterado de: <br>"+
                "<br>Nome: "+ nomeA+
                "<br>Bonus de milhas: "+bonusMilhasA +
                "<br>Quantidade de milhas por mes: "+qtdeMilhasMesA +
                "<br>Duracao: "+duracaoA +
                "<br>Preco Mensal: "+ precoMensalA+
                "<br>Data de expiracao das milhas: "+dataExpiracaoMilhasA +
                "<br><br>para: <br>"+
                "<br>Nome: "+ p.getNome()+
                "<br>Bonus de milhas: "+p.getBonusMilhas() +
                "<br>Quantidade de milhas por mes: " +p.getQtdeMilhasMes() +
                "<br>Duracao: "+p.getDuracao() +
                "<br>Preco Mensal: "+ p.getPrecoMensal()+
                "<br>Data de expiracao das milhas: "+p.getDataExpiracaoMilhas();
        return descricao;
    }

    public ProgramaFidelidade buscarProgramaFidelidade(int id){
        if (validarProgramaFidelidade(programaFidelidadeDAO.buscarPorId(id))) {
            throw  new RuntimeException("Programa de Fidelidade nao e valido");
        }
        return programaFidelidadeDAO.buscarPorId(id);
    }
    public void deletarProgramaFidelidade(int id, int usuarioExecutor){
        if(validarProgramaFidelidade(programaFidelidadeDAO.buscarPorId(id))){
            throw new RuntimeException("Programa de Fidelidade nao e valido");
        }
        ProgramaFidelidade p  = buscarProgramaFidelidade(id);
        try{
            programaFidelidadeDAO.excluirProgramaFidelidade(id);
            atividadeService.registrarAtividade(TipoAtividades.EXCLUSAO_PROGRAMA, "o programa de fidelidade: " + p.getNome() + "da empresa: "
                    + empresaService.buscarEmpresa(p.getIdEmpresa()).getNome() +"foi excluido" , (long) usuarioExecutor);
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

        return p.getDuracao() == 0 || p.getQtdeMilhasMes() == 0 || p.getIdEmpresa() <= 0 || p.getDataExpiracaoMilhas() == null;
    }

    public List<ProgramaFidelidade> listarTodosProgramaFidelidade() {
        return programaFidelidadeDAO.listarTodos();
    }

    public List<ProgramaFidelidade> listarPorCliente(Long idCliente) throws PersistenciaException, SQLException {
        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();
        if(manterUsuario.pesquisar(idCliente) == null)
            throw new PersistenciaException("Cliente nao encontrado");
        return programaFidelidadeDAO.listarPorCliente(idCliente);
    }
}