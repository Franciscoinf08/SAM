package sam.model.service;


import sam.model.common.exception.PersistenciaException;
import sam.model.dao.NotificacaoDAO;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.UsuarioProgramaDAO;
import sam.model.domain.Notificacao;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void enviar(Notificacao notificacao) throws SQLException {

        try{
            notificacaoDAO.salvar(notificacao);

        } catch (SQLException e) {
            throw new RuntimeException("erroi ao enviar notificacao" + e.getMessage(),e);
        }

    }
    public void enviarNotificacoesProgramaExpirando() {
        UsuarioProgramaDAO upDAO = new UsuarioProgramaDAO();
        List<Integer> usuarios = upDAO.buscarUsuariosComProgramaExpirando(3);
        String titulo = "Programa de fidelidade próximo do vencimento";
        String mensagem = "Um dos seus programas de fidelidade está próximo do vencimento(3 dias).";

        for (Integer idUsuario : usuarios) {
            Notificacao n = new Notificacao(mensagem, titulo, idUsuario);
            enviarSeNaoExistir(n);
        }
    }
    public void enviarMilhasExpirando() {
        ProgramaFidelidadeDAO pfDAO = new ProgramaFidelidadeDAO();
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        List<Usuario> lista = new ArrayList<>();
        try {
            lista = usuarioDAO.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<ProgramaFidelidade> programaFidelidades = pfDAO.listarComMilhasExpirando();
        String titulo = "Milhas do Expirando";


        for (ProgramaFidelidade p : programaFidelidades){
            String mensagem = "As milhas do programa de fidelidade " + pfDAO.buscarPorId(p.getIdProgramaFidelidade()).getNome() +
                    "estao expirando";
            for (Usuario u : lista){
                if (u.getTipo() == UsuarioTipo.GESTOR){
                    Notificacao n = new Notificacao(mensagem, titulo, Math.toIntExact(u.getId()));
                    enviarSeNaoExistir(n);
                }
            }
        }


    }
    public void enviarSeNaoExistir(Notificacao notificacao) {
        try {
            notificacaoDAO.salvarSeNaoExistir(notificacao);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }
}
