package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Notificacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.AlcanceNotificacao;

import java.sql.SQLException;
import java.util.List;

public class GestaoNotificacao {

    private final NotificacaoService servico = new NotificacaoService();
    private final UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

    public void enviarPorAlcance(Notificacao original, AlcanceNotificacao alcance)
            throws PersistenciaException, SQLException {

        List<Usuario> usuarios = usuarioDAO.listarPorTipo(alcance.name().toLowerCase());

        for (Usuario u : usuarios) {
            Notificacao copia = new Notificacao(original);
            copia.setDestinatario(u);
            servico.enviar(copia);
        }
    }
}
