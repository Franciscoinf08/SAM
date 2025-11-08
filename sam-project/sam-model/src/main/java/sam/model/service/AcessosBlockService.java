package sam.model.service;

import java.sql.SQLException;
import sam.model.dao.AcessosBlockDAO;

public class AcessosBlockService {

    private final AcessosBlockDAO bloqueios;

    public AcessosBlockService() {
        this.bloqueios = new AcessosBlockDAO();
    }

    public void bloquear(String recurso, String usuario) throws SQLException {
        try {
            bloqueios.bloquear(recurso, usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void ativar(String recurso, String usuario) throws SQLException {
        try {
            bloqueios.ativar(recurso, usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public boolean check(String recurso, String usuario) throws SQLException {
        try {
            return bloqueios.check(recurso, usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
