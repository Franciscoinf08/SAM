package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.NotificacaoDAO;
import sam.model.domain.Notificacao;

import java.util.List;

public class NotificacaoService {

    private final NotificacaoDAO dao = NotificacaoDAO.getInstance();

    public void enviar(Notificacao n) throws PersistenciaException {
        dao.inserir(n);
    }

    public List<Notificacao> listarPorUsuario(long idUsuario) throws PersistenciaException {
        return dao.listarPorUsuario(idUsuario);
    }

    public boolean marcarComoLida(long idNotif, long idUsuario) throws PersistenciaException {
        return dao.marcarComoLida(idNotif, idUsuario);
    }
}
