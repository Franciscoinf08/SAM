package sam.model.service;

import java.sql.SQLException;
import sam.model.dao.AcessosBlockDAO;
import sam.model.domain.util.TipoAtividades;

public class AcessosBlockService {

    private AcessosBlockDAO bloqueios;
    private final AtividadeService atividadeService = new AtividadeService();

    public AcessosBlockService() {
        this.bloqueios = AcessosBlockDAO.getInstance();
    }

    public void bloquear(String recurso, String usuario, int usuarioExecutor) throws SQLException {
        String descricao = "o usuario: "+ usuario + " foi bloqueado pelo desenvolvedor: "+ usuarioExecutor;
        bloqueios.bloquear(recurso, usuario);
        atividadeService.registrarAtividade(TipoAtividades.BLOQUEIO_USUARIO, descricao, (long) usuarioExecutor);
    }

    public void ativar(String recurso, String usuario, int usuarioExecutor) throws SQLException {
        String descricao = "o usuario: "+ usuario + " foi ativado pelo desenvolvedor: " + usuarioExecutor;
        bloqueios.ativar(recurso, usuario);
        atividadeService.registrarAtividade(TipoAtividades.ATIVACAO_USUARIO, descricao, (long) usuarioExecutor);
    }

    public boolean check(String recurso, String usuario) throws SQLException {
        return bloqueios.check(recurso, usuario);
    }
}
