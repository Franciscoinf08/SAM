package sam.model.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sam.model.domain.AssociacaoCliente;
import sam.model.dao.AssociacoesClientesDAO;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.util.AssociacaoClienteTipo;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;

public class GestaoAssociacoesClientesService {
    
    private final AssociacoesClientesDAO associacoesDAO;
    private final AtividadeService atividadeService = new AtividadeService();

    public GestaoAssociacoesClientesService() {
        this.associacoesDAO = AssociacoesClientesDAO.getInstance();
    }

    public void adicionarPedido(AssociacaoCliente associacao) throws SQLException {
        try {
            associacoesDAO.adicionarPedido(associacao);

            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(associacao.getIdCliente());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(associacao.getIdGestor());
            refs.add(ref);
            String descricao1 = "o usuario "+associacao.getIdGestor()+ " enviou um pedido de associacao como cliente para o usuario "+ associacao.getIdCliente();
            String descricao2 = "o usuario "+associacao.getIdGestor()+ " enviou um pedido de desassociacao como cliente para o usuario "+ associacao.getIdCliente();
            if(associacao.getTipo() == AssociacaoClienteTipo.ASSOCIAR)
                atividadeService.registrarAtividadeComReferencias(TipoAtividades.SOLICITACAO_ASSOCIACAO.name(),descricao1,associacao.getIdGestor(), refs);
            else
                atividadeService.registrarAtividadeComReferencias(TipoAtividades.SOLICITACAO_DESASSOCIACAO.name(),descricao2,associacao.getIdGestor(), refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void aprovar(Long idCliente, Long idGestor, String tipo) throws SQLException {

        AtividadeReferencia ref = new AtividadeReferencia();
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(idGestor);
        List<AtividadeReferencia> refs = new ArrayList<>();
        refs.add(ref);
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(idCliente);
        refs.add(ref);

        String descricao1 = "o usuario "+idCliente+ " aceitou a solicitacao de associacao do "+ idGestor;
        String descricao2 = "o usuario "+idCliente+ " aceitou a solicitacao de desassociacao do "+ idGestor;


        try {
            if("ASSOCIAR".equals(tipo)){
                associacoesDAO.aprovarAssociacao(idCliente, idGestor);
                atividadeService.registrarAtividadeComReferencias(TipoAtividades.ACEITAR_SOLICITACAO_ASSOCIAR.name(), descricao1, idGestor, refs);
            } else {
                associacoesDAO.aprovarDesassociacao(idCliente);
                atividadeService.registrarAtividadeComReferencias(TipoAtividades.ACEITAR_SOLICITACAO_DESASSOCIAR.name(), descricao2, idGestor, refs);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void recusar(Long idPedido) throws SQLException {
        try {
            associacoesDAO.deleta(idPedido);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<AssociacaoCliente> listarCliente(Long cliente) throws SQLException {
        try {
            return associacoesDAO.listarCliente(cliente);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public List<AssociacaoCliente> listarGestor(Long gestor) throws SQLException {
        try {
            return associacoesDAO.listarGestor(gestor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
