package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.NotificacaoDAO;
import sam.model.domain.Notificacao;

import java.util.List;

public class NotificacaoService {

    private final NotificacaoDAO notificacaoDAO;

    public NotificacaoService() {
        this.notificacaoDAO = NotificacaoDAO.getInstance();
    }

    public void enviar(Notificacao notificacao) throws PersistenciaException {
        notificacaoDAO.inserir(notificacao);
    }

    public List<Notificacao> listarPorUsuario(long idUsuario) throws PersistenciaException {
        return notificacaoDAO.listarTodas();
    }

    public List<Notificacao> listarTodas() throws PersistenciaException {
        return notificacaoDAO.listarTodas();
    }

    public boolean marcarComoLida(long idNotificacao, long idUsuario) throws PersistenciaException {
        return notificacaoDAO.marcarComoLida(idNotificacao, idUsuario);
    }

    public void excluir(long idNotificacao, long idUsuario) throws PersistenciaException {
        notificacaoDAO.deletar(idNotificacao, idUsuario);
    }
}
