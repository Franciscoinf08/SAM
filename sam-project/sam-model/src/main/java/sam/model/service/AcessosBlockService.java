package sam.model.service;

import java.sql.SQLException;
import sam.model.dao.AcessosBlockDAO;

public class AcessosBlockService {

    private AcessosBlockDAO bloqueios;

    public AcessosBlockService() {
        this.bloqueios = AcessosBlockDAO.getInstance();
    }

    public void bloquear(String recurso, String usuario) throws SQLException {
        bloqueios.bloquear(recurso, usuario);
    }

    public void ativar(String recurso, String usuario) throws SQLException {
        bloqueios.ativar(recurso, usuario);
    }

    public boolean check(String recurso, String usuario) throws SQLException {
        return bloqueios.check(recurso, usuario);
    }
}
