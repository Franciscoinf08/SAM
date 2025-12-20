package sam.model.service;

import java.util.List;
import java.sql.SQLException;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Solicitacao;
import sam.model.dao.SolicitacoesDAO;
import sam.model.domain.util.TipoAtividades;

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
            String desctricao = "O usuario " + sol.getNome() +" ID: "+ sol.getIdUsuario() +" mandou uma solicitacao para tornar-se gestor";
            atividadeService.registrarAtividade(TipoAtividades.SOLICITACAO_GESTOR, desctricao, sol.getIdUsuario());
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
            String descricao = "O usuario: "+longId+" foi aceito como gestor";
            atividadeService.registrarAtividade(TipoAtividades.SOLICITACAO_ACEITA, descricao, (long) usuarioExecutor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void recusarPedido(String id, int usuarioExecutor) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.recusarPedido(longId);
            String descricao = "O usuario: "+longId+" foi recusado como gestor";
            atividadeService.registrarAtividade(TipoAtividades.SOLICITACAO_RECUSADA, descricao,(long) usuarioExecutor);
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
            String descricao = "O usuario: "+longId+" voltou a ser cliente";
            atividadeService.registrarAtividade(TipoAtividades.TORNAR_CLIENTE,descricao, (long) usuarioExecutor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
