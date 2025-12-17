package sam.model.service;


import sam.model.common.exception.PersistenciaException;
import sam.model.dao.NotificacaoDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.UsuarioProgramaDAO;
import sam.model.domain.Notificacao;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
        System.out.println("Esta chegando na funcao enviarNotificacoesProgramaExpirando()");
        List<Integer> usuarios = upDAO.buscarUsuariosComProgramaExpirando(3);
        String titulo = "Programa de fidelidade próximo do vencimento";
        String mensagem = "Um dos seus programas de fidelidade está próximo do vencimento(3 dias).";


        System.out.println("Quantidade de usuarios encontrados: " + usuarios.size());
        System.out.println("Usuarios: " + usuarios);

        for (Integer idUsuario : usuarios) {
            System.out.println("a lista esta chegando corretamente " + idUsuario);
            Notificacao n = new Notificacao(mensagem, titulo, idUsuario);
            enviarSeNaoExistir(n);
        }
    }
    public void enviarSeNaoExistir(Notificacao notificacao) {
        System.out.println("chegou na ultima funcao do service");
        try {
            notificacaoDAO.salvarSeNaoExistir(notificacao);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }
}
