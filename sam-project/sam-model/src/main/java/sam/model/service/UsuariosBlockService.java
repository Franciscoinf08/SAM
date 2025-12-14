package sam.model.service;

import java.sql.SQLException;
import sam.model.dao.UsuariosBlockDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.AssociacoesClientesDAO;
import sam.model.domain.Usuario;

public class UsuariosBlockService {
    private UsuariosBlockDAO bloqueios;
    private UsuarioDAO usuarios;
    private AssociacoesClientesDAO associacoes;

    public UsuariosBlockService() {
        this.bloqueios = UsuariosBlockDAO.getInstance();
        this.usuarios = UsuarioDAO.getInstance();
        this.associacoes = AssociacoesClientesDAO.getInstance();
    }

    public void bloquear(Long idUsuario, Long idBloqueado) throws SQLException {
        Usuario usuario = usuarios.pesquisar(idUsuario);
        Usuario bloqueado = usuarios.pesquisar(idBloqueado);
        
        // CHEGA SE ESTÃO ASSOCIADOS PARA DESASSOCIAR
        if(usuario.getIdGestor() == bloqueado.getId() || bloqueado.getIdGestor() == usuario.getId()){
            associacoes.aprovarDesassociacao(idUsuario);
        }
        
        bloqueios.bloquear(idUsuario, idBloqueado);
    }

    public void desbloquear(Long idUsuario, Long idBloqueado) throws SQLException {
        bloqueios.desbloquear(idUsuario, idBloqueado);
    }

    public boolean check(Long idUsuario, Long idBloqueado) throws SQLException {
        return bloqueios.check(idUsuario, idBloqueado);
    }
}
