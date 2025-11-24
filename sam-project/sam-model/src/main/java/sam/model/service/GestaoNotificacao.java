package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Notificacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.AlcanceNotificacao;
import sam.model.domain.util.TipoNotificacao;

import java.util.List;

public class GestaoNotificacao {

    private final NotificacaoService notificacaoService;
    private final EmailService email;
    private final UsuarioDAO usuarioDAO;

    public GestaoNotificacao() {
        this.notificacaoService = new NotificacaoService();
        this.email = new EmailService();
        this.usuarioDAO = UsuarioDAO.getInstance();
    }

    public void selecionaAlcance(Notificacao notificacao, AlcanceNotificacao alcance) throws PersistenciaException {
        selecionaNome(notificacao);
        switch (alcance) {

            case INDIVIDUAL -> {
                if (notificacao.getDestinatario() == null)
                    throw new PersistenciaException("Destinatário deve ser informado no alcance INDIVIDUAL.");
                notificacaoService.enviar(notificacao);
            }

            case GERAL -> {
                List<Usuario> usuarios = usuarioDAO.listarUsuariosNaoAdm();
                enviarParaUsuarios(notificacao, usuarios);
            }

            case ALUNOS -> {
                List<Usuario> alunos = usuarioDAO.listarAlunos();
                enviarParaUsuarios(notificacao, alunos);
            }

            case PROFESSORES -> {
                List<Usuario> professores = usuarioDAO.listarProfessores();
                enviarParaUsuarios(notificacao, professores);
            }
        }
    }

    private void enviarParaUsuarios(Notificacao original, List<Usuario> usuarios) throws PersistenciaException {
        for (Usuario u : usuarios) {
            Notificacao copia = new Notificacao(original);
            copia.setDestinatario(u);

            notificacaoService.enviar(copia);
        }
    }
    private void selecionaNome(Notificacao notificacao){
        if(notificacao.getTipo() != TipoNotificacao.OUTROS){
            String tituloFinal = notificacao.getTipo().getDescricao();
            notificacao.setTitulo(tituloFinal);
        }
    }
}