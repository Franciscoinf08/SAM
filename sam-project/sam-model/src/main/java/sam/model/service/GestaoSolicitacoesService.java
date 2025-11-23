package sam.model.service;

import java.util.List;
import java.sql.SQLException;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Solicitacao;
import sam.model.dao.SolicitacoesDAO;

public class GestaoSolicitacoesService {
    private final SolicitacoesDAO solicitacoesDAO;
    
    public GestaoSolicitacoesService(){
        this.solicitacoesDAO = new SolicitacoesDAO();
    }
    
    public List<Solicitacao> listarEmail(String email) throws PersistenciaException, SQLException{
        try {
            return solicitacoesDAO.listarEmail(email);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public List<Solicitacao> listarTodos() throws PersistenciaException, SQLException{
        try {
            return solicitacoesDAO.listarTodos();
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void adicionarPedido(Solicitacao sol) throws PersistenciaException, SQLException{
        try {
            solicitacoesDAO.adicionarPedido(sol);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void cancelarPedido(String id) throws PersistenciaException, SQLException{
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.cancelarPedido(longId);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void aprovarPedido(String id) throws PersistenciaException, SQLException{
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.aprovarPedido(longId);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void recusarPedido(String id) throws PersistenciaException, SQLException{
        try {
            long longId = Long.parseLong(id);
            solicitacoesDAO.recusarPedido(longId);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void solicitarPagamento(String id) throws PersistenciaException, SQLException{
        try {
            /* PEGA O EMAIL DO USUÁRIO
            ENVIA EMAIL SOLICITANDO O PAGAMENTO (ENVIANDO AS INFORMAÇÕES NECESSÁRIAS)
            QUANDO O EMAIL FOR ENVIADO, MUDA O STATUS DA SOLICITAÇÃO PARA "AGUARDANDO"*/
            long longId = Long.parseLong(id);
            solicitacoesDAO.aguardarPagamento(longId);
            String email = solicitacoesDAO.pesquisaId(longId);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
}
