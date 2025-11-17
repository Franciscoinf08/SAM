package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.TransacaoDAO;
import sam.model.domain.Transacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.TransacaoTipo;
import sam.model.domain.util.UsuarioTipo;
import sam.model.helper.TransacaoHelper;

import java.math.BigDecimal;
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

    public BigDecimal getValorTotalCliente(Usuario cliente) throws SQLException {
        BigDecimal valor = BigDecimal.ZERO;

        for (Transacao transacao : listarPorCliente(cliente))
            if (transacao.getTipo() == TransacaoTipo.COMPRA)
                valor = valor.subtract(transacao.getValor());
            else
                valor = valor.add(transacao.getValor());

        return valor;
    }

    public int getQuantidadeTotalCliente(Usuario cliente) throws SQLException {
        int quantidade = 0;

        for (Transacao transacao : listarPorCliente(cliente)) {
            if (transacao.getTipo() == TransacaoTipo.COMPRA)
                quantidade += transacao.getQuantidade();
            else
                quantidade -= transacao.getQuantidade();
        }

        return quantidade;
    }

    public int getBonusTotalCliente(Usuario cliente) throws SQLException {
        int bonus = 0;

        for (Transacao transacao : listarPorCliente(cliente))
            bonus += transacao.getBonus();

        return bonus;
    }

    public int getMilhasTotalCliente(Usuario cliente) throws SQLException {
        return getQuantidadeTotalCliente(cliente) + getBonusTotalCliente(cliente);
    }
}
