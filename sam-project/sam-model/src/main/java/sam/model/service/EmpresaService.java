package sam.model.service;

import sam.model.dao.Conexao;
import sam.model.dao.EmpresaDAO;
import sam.model.domain.Empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpresaService {

    public EmpresaService(){}

    public void cadastrarEmpresa(Empresa empresa){

        if(validarEmpresa(empresa)){
            throw new RuntimeException("a empresa que voce quer cadastrar nao existe ou o cnpj é invalido");
        }
        Connection conexao = null;

        try {
            conexao = Conexao.getConnection();
            conexao.setAutoCommit(false);

            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);

            empresa.setCNPJ(empresa.getCNPJ().replaceAll("\\D","").replaceAll("\\.",""));
            empresaDAO.salvar(empresa);
            conexao.commit();
        } catch (SQLException e) {
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException ex) {}
            }
            throw new RuntimeException(e.getMessage());
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

    public List<Empresa> listarEmpresas(){
        Connection conexao = null;
        try{
            conexao = Conexao.getConnection();
            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);
            return empresaDAO.listarTodas();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ignored) {
                }
            }
        }

    }
    public void excluir(int id){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);
            empresaDAO.excluirEmpresa(id);
        } catch (SQLException e) {
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException ex) {

                }
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {

                }
            }
        }
    }
    public Empresa buscarEmpresa(int id){
        Connection conexao = null;
        try{
            conexao = Conexao.getConnection();
            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);
            Empresa empresa = empresaDAO.buscarPorId(id);
            if (validarEmpresa(empresa)) {
                throw new RuntimeException();
            }
            return empresa;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {}
            }
        }


    }

    public void atualizarEmpresa(Empresa empresa){
        if (validarEmpresa(empresa)) {
            throw new RuntimeException();
        }
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);
            conexao.setAutoCommit(false);
            empresaDAO.atualizarEmpresa(empresa);
            conexao.commit();

        } catch (SQLException e) {
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException ex) {}
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {}
            }
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
