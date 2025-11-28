package sam.model.service;

import sam.model.dao.Conexao;
import sam.model.dao.EmpresaDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;
import sam.model.helper.CalculoHelper;
import java.sql.Connection;
import java.sql.SQLException;

public class AvaliadorProgramaFidelidadeService {

    public AvaliadorProgramaFidelidadeService() {}
    


    public void avaliarPrograma(ProgramaFidelidade programa) throws Exception{
        Connection conexao = null;
        try{
            conexao = Conexao.getConnection();
            conexao.setAutoCommit(false);

            EmpresaDAO empresaDAO = new EmpresaDAO(conexao);
            ProgramaFidelidadeDAO programaDAO = new ProgramaFidelidadeDAO(conexao);

            Empresa empresa = empresaDAO.buscarPorId(programa.getIdEmpresa());
            double preco = programa.getPrecoMensal();
            double milhasMes = programa.getQtdeMilhasMes();
            double meses = programa.getDuracao();
            double bonus = programa.getBonusMilhas();
            double milheiroSeguranca = empresa.getMilheiroSeguranca();


            double milhasTotais = CalculoHelper.calcularMilhasTotais(milhasMes, meses, bonus);
            double valorMilheiro = CalculoHelper.calcularValorMilheiro(preco, milhasTotais);

            double diferenca = CalculoHelper.calcularDiferencaPercentual(milheiroSeguranca, valorMilheiro);
            String avaliacao = CalculoHelper.classificaValorMilheiro(diferenca);

            programa.setAvaliacao(avaliacao);
            registraAvaliacaoDAO(programa, programaDAO);
            conexao.commit();
        } catch(SQLException erro){
            if (conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException rollbackEx) {}
            }
            throw new Exception(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                }  catch (SQLException closeEx) {}
            }
        }
    }

    private void registraAvaliacaoDAO(ProgramaFidelidade programaAvaliado, ProgramaFidelidadeDAO pfDAO) throws SQLException{
        pfDAO.atualizarProgramaFidelidade(programaAvaliado);
    }
}

