package sam.model.service;

import sam.model.dao.AtividadeDAO;
import sam.model.dao.AtividadeReferenciaDAO;
import sam.model.domain.Atividade;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.util.TipoAtividades;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AtividadeService {

    private AtividadeDAO atividadeDAO = new AtividadeDAO();
    private AtividadeReferenciaDAO referenciaDAO = new AtividadeReferenciaDAO();


    public void registrarAtividade(
            TipoAtividades tipo,
            String descricao,
            Long usuarioExecutorId
    ) throws SQLException {

        Atividade atividade = new Atividade();
        atividade.setTipo(tipo.name());
        atividade.setDescricao(descricao);
        atividade.setUsuarioExecutorId(usuarioExecutorId);
        atividade.setDataHora(LocalDateTime.now());

        atividadeDAO.inserir(atividade);
    }

    public void registrarAtividadeComReferencias(
            String tipo,
            String descricao,
            Long usuarioExecutorId,
            List<AtividadeReferencia> referencias
    ) throws SQLException {

        Atividade atividade = new Atividade();
        atividade.setTipo(tipo);
        atividade.setDescricao(descricao);
        atividade.setUsuarioExecutorId(usuarioExecutorId);
        atividade.setDataHora(LocalDateTime.now());

        Long atividadeId = atividadeDAO.inserir(atividade);

        if (atividadeId == null) {
            throw new SQLException("Falha ao registrar atividade");
        }

        for (AtividadeReferencia ref : referencias) {
            ref.setAtividadeId(atividadeId);
            referenciaDAO.inserir(ref);
        }
    }
    public List<Atividade> listarTodas() throws SQLException {
        return atividadeDAO.listarTodas();
    }

    public Atividade buscarPorId(Long id) throws SQLException {
        return atividadeDAO.buscarPorId(id);
    }

    public List<AtividadeReferencia> listarReferencias(Long atividadeId) throws SQLException {
        return referenciaDAO.listarPorAtividade(atividadeId);
    }
}
