package sam.model.service;


import java.sql.SQLException;
import java.util.List;
import sam.model.domain.AssociacaoCliente;
import sam.model.dao.AssociacoesClientesDAO;

public class GestaoAssociacoesClientesService {
    
    private final AssociacoesClientesDAO associacoesDAO;

    public GestaoAssociacoesClientesService() {
        this.associacoesDAO = AssociacoesClientesDAO.getInstance();
    }

    public void adicionarPedido(AssociacaoCliente associacao) throws SQLException {
        try {
            associacoesDAO.adicionarPedido(associacao);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public void aprovar(Long idCliente, Long idGestor, String tipo) throws SQLException {
        try {
            if("ASSOCIAR".equals(tipo)){
                associacoesDAO.aprovarAssociacao(idCliente, idGestor);
            } else {
                associacoesDAO.aprovarDesassociacao(idCliente);
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
