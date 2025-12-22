package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.EmpresaDAO;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Empresa;
import sam.model.domain.Usuario;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaService {
    private final Connection conexao;
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    private final AtividadeService atividadeService = new AtividadeService();
    public EmpresaService() {
        this.conexao = Conexao.getConnection();
    }

    public void cadastrarEmpresa(Empresa empresa, int usuarioExecutor) throws SQLException {

        if(validarEmpresa(empresa)){
            throw new RuntimeException("a empresa que voce quer cadastrar nao existe ou o cnpj é invalido");
        }
        try {
            empresa.setCNPJ(empresa.getCNPJ().replaceAll("\\D","").replaceAll("\\.",""));
            empresaDAO.salvar(empresa);

            AtividadeReferencia refEmpresa = new AtividadeReferencia();
            refEmpresa.setTipoEntidade(TipoEntidades.EMPRESA.name());
            refEmpresa.setEntidadeId((long) empresa.getId());
            List<AtividadeReferencia> refs = List.of(refEmpresa);

            atividadeService.registrarAtividadeComReferencias(
                    TipoAtividades.CADASTRO_EMPRESA.name(),
                    "A empresa " + empresa.getNome() + " foi cadastrada",
                    (long) usuarioExecutor,
                    refs
            );
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public List<Empresa> listarEmpresas(){
        return empresaDAO.listarTodas();
    }
    public void excluir(int id, int usuarioExecutor){
        try {
            empresaDAO.excluirEmpresa(id);

            AtividadeReferencia refEmpresa = new AtividadeReferencia();
            refEmpresa.setTipoEntidade(TipoEntidades.EMPRESA.name());
            refEmpresa.setEntidadeId((long) buscarEmpresa(id).getIdEmpresa());
            List<AtividadeReferencia> refs = List.of(refEmpresa);

            atividadeService.registrarAtividadeComReferencias(TipoAtividades.CADASTRO_EMPRESA.name(), "a empresa: " + buscarEmpresa(id).getNome() + " foi excluida", (long) usuarioExecutor, refs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Empresa buscarEmpresa(int id){
        Empresa empresa = empresaDAO.buscarPorId(id);
        return empresa;
    }

    public void atualizarEmpresa(Empresa empresa,int usuarioExecutor){
        if (validarEmpresa(empresa)) {
            throw new RuntimeException();
        }
        try {
            String nomeAntigo = empresaDAO.buscarPorId(empresa.getId()).getNome();
            String CNPJAntigo = empresaDAO.buscarPorId(empresa.getId()).getCNPJ();
            double milheiroSegurancaAntigo = empresaDAO.buscarPorId(empresa.getId()).getMilheiroSeguranca();

            AtividadeReferencia refEmpresa = new AtividadeReferencia();
            refEmpresa.setTipoEntidade(TipoEntidades.EMPRESA.name());
            refEmpresa.setEntidadeId((long) empresa.getIdEmpresa());
            List<AtividadeReferencia> refs = List.of(refEmpresa);

            empresaDAO.atualizarEmpresa(empresa);
            atividadeService.registrarAtividade(TipoAtividades.EDICAO_EMPRESA.name(), "a empresa: " + nomeAntigo +" teve seus dados alterados de: <br>" +
                    "Nome: "+ nomeAntigo +
                    "<br>CNPJ: "+ CNPJAntigo +
                    "<br>Milheiro de segurança: "+ milheiroSegurancaAntigo +
                    "<br><br>para: <br>" +
                    "<br>Nome: "+ empresa.getNome() +
                    "<br>CNPJ: "+ empresa.getCNPJ() +
                    "<br>Milheiro de segurança: "+empresa.getMilheiroSeguranca()
                    , (long) usuarioExecutor);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    private boolean validarCnpj(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");
        cnpj = cnpj.replaceAll("\\.", "");

        if (cnpj.length() != 14) return false;

        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};


            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos1[i];
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;


            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos2[i];
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;


            return digito1 == (cnpj.charAt(12) - '0') &&
                    digito2 == (cnpj.charAt(13) - '0');
        } catch (Exception e) {
            return false;
        }
    }
    public boolean validarEmpresa(Empresa empresa){

        if(empresa == null || empresa.getNome() == null) return true;

        if (empresa.getMilheiroSeguranca() < 0)  return true;

        return !validarCnpj(empresa.getCNPJ());
    }

}