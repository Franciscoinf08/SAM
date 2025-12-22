package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.PropostaDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Feedback;
import sam.model.domain.Proposta;
import sam.model.domain.Usuario;
import sam.model.domain.util.StatusProposta;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PropostaService {
    private final Connection conexao;
    private final PropostaDAO propostaDAO = new PropostaDAO();
    public PropostaService() {
        this.conexao = Conexao.getConnection();
    }
    private StatusProposta statusEnum = StatusProposta.ESPERA;
    String status = statusEnum.name();

    public void registrarProposta(Long idCliente, Long idGestor, String origem, String destino, String observacoes, LocalDate dataIda, LocalDate dataVolta, int numAdultos, int numCriancas, double valorEmDinheiro, Long valorEmMilhas, double taxas)throws Exception{

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        Usuario cliente = usuarioDAO.pesquisar(idCliente);
        Usuario gestor = usuarioDAO.pesquisar(idGestor);


        if (cliente == null)
            throw new Exception("Cliente não encontrado.");
        if (gestor == null)
            throw new Exception("Gestor não encontrado.");


        Proposta proposta = new Proposta(cliente, gestor, status, valorEmDinheiro, valorEmMilhas, taxas, observacoes, dataIda, dataVolta, origem, destino, numAdultos, numCriancas);

        try {
            propostaDAO.inserir(proposta);
        }catch (SQLException e){
            throw new RuntimeException("Erro ao inserir proposta:" + e.getMessage());
        }
    }
    public void excluirProposta(Long idProposta) {
        try {

            propostaDAO.excluir(idProposta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir proposta", e);
        }
    }

    public void atualizarStatus(Long idProposta, StatusProposta statusProposta) {
        String status = statusProposta.name();
        try {
            propostaDAO.atualizarStatus(idProposta, status);
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar proposta:" + e.getMessage());
        }
    }
}


