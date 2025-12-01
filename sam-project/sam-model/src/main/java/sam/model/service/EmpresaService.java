package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.EmpresaDAO;
import sam.model.domain.Empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpresaService {
    private final Connection conexao;
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    public EmpresaService() {
        this.conexao = Conexao.getConnection();
    }

    public void cadastrarEmpresa(Empresa empresa){
        if(validarEmpresa(empresa)){
            throw new RuntimeException("a empresa que voce quer cadastrar nao existe ou o cnpj é invalido");
        }
        try {
            empresa.setCNPJ(empresa.getCNPJ().replaceAll("\\D","").replaceAll("\\.",""));
            empresaDAO.salvar(empresa);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public List<Empresa> listarEmpresas(){
        return empresaDAO.listarTodas();
    }
    public void excluir(int id){
        try {
            empresaDAO.excluirEmpresa(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Empresa buscarEmpresa(int id){
        Empresa empresa = empresaDAO.buscarPorId(id);
        return empresa;
    }

    public void atualizarEmpresa(Empresa empresa){
        if (validarEmpresa(empresa)) {
            throw new RuntimeException();
        }
        try {
            empresaDAO.atualizarEmpresa(empresa);
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