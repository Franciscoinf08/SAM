package sam.model.service;

import java.util.List;
import java.sql.SQLException;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Solicitacao;
import sam.model.dao.SolicitacoesDAO;

public class GestaoSolicitacoesService {

    private final SolicitacoesDAO solicitacoesDAO;

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

    public void aprovarPedido(String id) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.aprovarPedido(longId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void recusarPedido(String id) throws SQLException {
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.recusarPedido(longId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void solicitarPagamento(String id) throws SQLException {
        try {
            /* PEGA O EMAIL DO USUÁRIO
            ENVIA EMAIL SOLICITANDO O PAGAMENTO (ENVIANDO AS INFORMAÇÕES NECESSÁRIAS)
            QUANDO O EMAIL FOR ENVIADO, MUDA O STATUS DA SOLICITAÇÃO PARA "AGUARDANDO"*/
            long longId = Long.parseLong(id);
            solicitacoesDAO.aguardarPagamento(longId);
            String email = solicitacoesDAO.pesquisarEmail(longId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
