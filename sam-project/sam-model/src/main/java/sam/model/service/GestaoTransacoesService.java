package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.TransacaoDAO;
import sam.model.domain.Transacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;
import sam.model.helper.TransacaoHelper;

import java.sql.SQLException;
import java.util.List;

public class GestaoTransacoesService {

    private final TransacaoDAO transacaoDAO;

    public GestaoTransacoesService() {
        transacaoDAO = TransacaoDAO.getInstance();
    }

    public void cadastrar(Transacao transacao) throws SQLException, PersistenciaException {
        if (!"".equals(TransacaoHelper.validarCadastroTransacao(transacao)))
            throw new PersistenciaException(TransacaoHelper.validarCadastroTransacao(transacao));

        try {
            transacaoDAO.inserir(transacao);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Transacao> listarPorCliente(Usuario cliente) throws SQLException {
        if (cliente.getTipo() != UsuarioTipo.CLIENTE)
            throw new RuntimeException("Acesso negado");

        return transacaoDAO.pesquisarPorCliente(cliente);
    }

    public List<Transacao> listarPorGestor(Usuario Gestor) throws SQLException {
        if (Gestor.getTipo() != UsuarioTipo.CLIENTE)
            throw new RuntimeException("Acesso negado");

        return transacaoDAO.pesquisarPorGestor(Gestor);
    }
}
