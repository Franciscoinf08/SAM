package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.TransacaoDAO;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Solicitacao;
import sam.model.domain.Transacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.*;
import sam.model.helper.DataHelper;
import sam.model.helper.TransacaoHelper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GestaoTransacoesService {

    private final TransacaoDAO transacaoDAO;
    private final AtividadeService atividadeService = new AtividadeService();

    public GestaoTransacoesService() {
        transacaoDAO = TransacaoDAO.getInstance();
    }

    public void cadastrar(Transacao transacao, int usuarioExecutor) throws SQLException, PersistenciaException {
        if (!"".equals(TransacaoHelper.validarCadastroTransacao(transacao)))
            throw new PersistenciaException(TransacaoHelper.validarCadastroTransacao(transacao));
        try {
            transacaoDAO.inserir(transacao);

            Long idCliente = transacaoDAO.pesquisar(transacao.getIdCliente()).getIdCliente();
            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.TRANSACAO.name());
            ref.setEntidadeId(transacao.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(idCliente);
            refs.add(ref);

            String descricao = "Houve uma transacao de " + transacao.getTipo() +" pelo Gestor " + usuarioExecutor;
            if(transacao.getTipo() == TransacaoTipo.VENDA)
                atividadeService.registrarAtividadeComReferencias(TipoAtividades.TRANSACAO_VENDA.name(), descricao, (long) usuarioExecutor, refs);
            if(transacao.getTipo() == TransacaoTipo.COMPRA)
                atividadeService.registrarAtividadeComReferencias(TipoAtividades.TRANSACAO_COMPRA.name(), descricao, (long) usuarioExecutor, refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Transacao> listarAtivasPorCliente(Usuario cliente) throws SQLException {
        List<Transacao> listaTransacoes = listarPorCliente(cliente);
        List<Transacao> listaTransacoesAtivas = new LinkedList<>();

        for (Transacao transacao : listaTransacoes) {
            if (transacao.getStatus() == TransacaoStatus.ATIVA)
                listaTransacoesAtivas.add(transacao);
        }

        return listaTransacoesAtivas;
    }

    public List<Transacao> listarPorCliente(Usuario cliente) throws SQLException {
        if (cliente.getTipo() != UsuarioTipo.CLIENTE)
            throw new RuntimeException("Acesso negado");

        return transacaoDAO.pesquisarPorCliente(cliente);
    }

    public List<Transacao> listarQuaseExpirandoPorCliente(Usuario cliente) throws SQLException {
        List<Transacao> listaTransacoes = listarAtivasPorCliente(cliente);
        List<Transacao> listaExpirando = new LinkedList<>();
        for (Transacao transacao : listaTransacoes) {
            if (transacao.getTipo() == TransacaoTipo.VENDA)
                continue;
            LocalDate dataExpiracao = LocalDate.parse(transacao.getDataExpiracao().toString());
            if (DataHelper.verificarProximidadeAgora(dataExpiracao, 1))
                listaExpirando.add(transacao);
        }
        listaExpirando.sort(new OrdenarTransacaoPorDataExpiracao());
        return listaExpirando;
    }

    public void remover(Long id, int  usuarioExecutor) throws SQLException, PersistenciaException {
        if (transacaoDAO.pesquisar(id) == null)
            throw new PersistenciaException("A transação não existe");
        String descricao = "A transacao " + id +" foi exluida pelo Gestor " + usuarioExecutor;
        try {
            transacaoDAO.remover(id);

            Long idCliente = transacaoDAO.pesquisar(id).getIdCliente();
            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.TRANSACAO.name());
            ref.setEntidadeId(id);
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(idCliente);
            refs.add(ref);

            atividadeService.registrarAtividadeComReferencias(TipoAtividades.TRANSACAO_EXCLUSAO.name(), descricao, (long) usuarioExecutor, refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}