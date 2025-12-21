package sam.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sam.model.dao.AcessosBlockDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Usuario;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;

public class AcessosBlockService {

    private AcessosBlockDAO bloqueios;
    private final AtividadeService atividadeService = new AtividadeService();
    private final UsuarioDAO  usuarioDAO = UsuarioDAO.getInstance();

    public AcessosBlockService() {
        this.bloqueios = AcessosBlockDAO.getInstance();
    }

    public void bloquear(String recurso, String usuario, int usuarioExecutor, String usuarioBloqueado) throws SQLException {
        Usuario bloqueado = usuarioDAO.pesquisarPorCPF(usuarioBloqueado);
        AtividadeReferencia ref = new AtividadeReferencia();
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(bloqueado.getId());
        List<AtividadeReferencia> refs = new ArrayList<>();
        refs.add(ref);

        String descricao = "o usuario: "+ usuario + " foi bloqueado pelo desenvolvedor: "+ usuarioExecutor;
        bloqueios.bloquear(recurso, usuario);
        atividadeService.registrarAtividadeComReferencias(TipoAtividades.BLOQUEIO_ACESSO_USUARIO.name(), descricao, (long) usuarioExecutor, refs);
    }

    public void ativar(String recurso, String usuario, int usuarioExecutor, String usuarioBloqueado) throws SQLException {
        String descricao = "o usuario: "+ usuario + " foi ativado pelo desenvolvedor: " + usuarioExecutor;
        bloqueios.ativar(recurso, usuario);

        Usuario bloqueado = usuarioDAO.pesquisarPorCPF(usuarioBloqueado);
        AtividadeReferencia ref = new AtividadeReferencia();
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(bloqueado.getId());
        List<AtividadeReferencia> refs = new ArrayList<>();
        refs.add(ref);

        atividadeService.registrarAtividadeComReferencias(TipoAtividades.ATIVACAO_ACESSO_USUARIO.name(), descricao, (long) usuarioExecutor, refs);
    }

    public boolean check(String recurso, String usuario) throws SQLException {
        return bloqueios.check(recurso, usuario);
    }
}
