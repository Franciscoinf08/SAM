package sam.model.service;

import sam.model.dao.EmpresaDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.domain.Empresa;
import sam.model.domain.ProgramaFidelidade;
import java.sql.Connection;

public class AvaliadorProgramaFidelidade {
    private final Connection conexao;
    private final ProgramaFidelidadeDAO programaDAO;
    private final EmpresaDAO empresaDAO;
     

    public AvaliadorProgramaFidelidade(Connection conexao) {
        this.conexao = conexao;
        this.programaDAO = new ProgramaFidelidadeDAO(conexao);
        this.empresaDAO = new EmpresaDAO();
        
    }

    public void avaliarPrograma(int idPrograma) throws Exception {
        ProgramaFidelidade programa = programaDAO.buscarPorId(idPrograma);
        Empresa empresa = empresaDAO.buscarPorId(programa.getEmpresa().getIdEmpresa());

        double preco = programa.getPrecoMensal();
        double milhasMes = programa.getQtdeMilhasMes();
        double meses = programa.getDuracao();
        double bonus = programa.getBonusMilhas();
        double milheiroSeguranca = empresa.getMilheiroSeguranca();

        
        double milhasTotais = (milhasMes * meses) * (1 + (bonus / 100.0));
        double valorMilheiro = (preco / milhasTotais) * 1000;

        
        double diferenca = ((milheiroSeguranca - valorMilheiro) / milheiroSeguranca) * 100;
        String classificacao;

        if (diferenca < 0) {
            classificacao = "Desvantajoso";
        } else if (diferenca < 10) {
            classificacao = "Razoável";
        } else if (diferenca < 20) {
            classificacao = "Vantajoso";
        } else {
            classificacao = "Muito vantajoso";
        }

        
        
    }
}

