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
    private final Connection conexao;

    public AvaliadorProgramaFidelidadeService() {
        this.conexao = Conexao.getConnection();
    }
    


    public void avaliarPrograma(int idPrograma) throws Exception{
        
        EmpresaDAO empresaDAO = new EmpresaDAO();
        ProgramaFidelidadeDAO pfDAO = new ProgramaFidelidadeDAO();
        
        Empresa empresa = empresaDAO.buscarPorId(idPrograma);
        ProgramaFidelidade programa = pfDAO.buscarPorId(idPrograma);

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
        registraAvaliacaoDAO(programa, pfDAO);    }

    private void registraAvaliacaoDAO(ProgramaFidelidade programaAvaliado, ProgramaFidelidadeDAO pfDAO) throws SQLException{
        pfDAO.salvar(programaAvaliado);

    }
}

