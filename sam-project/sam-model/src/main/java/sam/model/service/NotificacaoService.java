package sam.model.service;


import sam.model.dao.NotificacaoDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Notificacao;

import java.sql.SQLException;
import java.util.List;

public class NotificacaoService {
    private NotificacaoDAO notificacaoDAO;

    public NotificacaoService() {
        this.notificacaoDAO = new NotificacaoDAO();
    }

    public List<Notificacao> lista(int idUsuario) throws SQLException {
        return notificacaoDAO.listarPorUsuario(idUsuario);
    }

    public void marcarComoLida(int idNotificacao) {
        try {
            notificacaoDAO.marcarComoLida(idNotificacao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void enviarNotificacaoAutomatica(int idNotificacao, int idUsuario) {


    }
}
