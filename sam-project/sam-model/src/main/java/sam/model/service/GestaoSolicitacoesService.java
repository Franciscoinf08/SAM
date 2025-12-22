package sam.model.service;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Solicitacao;
import sam.model.dao.SolicitacoesDAO;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;

public class GestaoSolicitacoesService {

    private final SolicitacoesDAO solicitacoesDAO;
    private final AtividadeService atividadeService = new AtividadeService();

    public GestaoSolicitacoesService() {
        this.solicitacoesDAO = SolicitacoesDAO.getInstance();
    }

    public List<Solicitacao> listarEmail(String email) throws SQLException {
        try {
            return solicitacoesDAO.listarEmail(email);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Solicitacao> listarTodos() throws SQLException {
        try {
            return solicitacoesDAO.listarTodos();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void adicionarPedido(Solicitacao sol) throws SQLException {
        try {
            if (sol.getPagamento() == null || sol.getPagamento().trim().isEmpty()) {
                throw new SQLException("Forma de pagamento inválida");
            }
            solicitacoesDAO.adicionarPedido(sol);

            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.SOLICITACAO.name());
            ref.setEntidadeId(sol.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(sol.getIdUsuario());
            refs.add(ref);

            String desctricao = "O usuario " + sol.getNome() +" ID: "+ sol.getIdUsuario() +" mandou uma solicitacao para tornar-se gestor";
            atividadeService.registrarAtividadeComReferencias(TipoAtividades.SOLICITACAO_GESTOR.name(), desctricao, sol.getIdUsuario(), refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void cancelarPedido(String id) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.cancelarPedido(longId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void aprovarPedido(String id, int usuarioExecutor) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.aprovarPedido(longId);

            Solicitacao sol = solicitacoesDAO.pesquisar(longId);
            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.SOLICITACAO.name());
            ref.setEntidadeId(sol.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(sol.getIdUsuario());
            refs.add(ref);

            String descricao = "O usuario: "+longId+" foi aceito como gestor";
            atividadeService.registrarAtividadeComReferencias(TipoAtividades.SOLICITACAO_ACEITA.name(), descricao, (long) usuarioExecutor, refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void recusarPedido(String id, int usuarioExecutor) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.recusarPedido(longId);

            Solicitacao sol = solicitacoesDAO.pesquisar(longId);
            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.SOLICITACAO.name());
            ref.setEntidadeId(sol.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(sol.getIdUsuario());
            refs.add(ref);

            String descricao = "O usuario: "+longId+" foi recusado como gestor";
            atividadeService.registrarAtividadeComReferencias(TipoAtividades.SOLICITACAO_RECUSADA.name(), descricao,(long) usuarioExecutor, refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void solicitarPagamento(String id) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.aguardarPagamento(longId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public Solicitacao pesquisar(String id) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            return solicitacoesDAO.pesquisar(longId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void tornarCliente(String id, int usuarioExecutor) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.tornarCliente(longId);

            Solicitacao sol = solicitacoesDAO.pesquisar(longId);
            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.SOLICITACAO.name());
            ref.setEntidadeId(sol.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(sol.getIdUsuario());
            refs.add(ref);

            String descricao = "O usuario: "+longId+" voltou a ser cliente";
            atividadeService.registrarAtividadeComReferencias(TipoAtividades.TORNAR_CLIENTE.name(),descricao, (long) usuarioExecutor, refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
