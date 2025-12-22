package sam.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import sam.model.dao.UsuariosBlockDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.AssociacoesClientesDAO;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Usuario;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;

public class UsuariosBlockService {
    private UsuariosBlockDAO bloqueios;
    private UsuarioDAO usuarios;
    private AssociacoesClientesDAO associacoes;
    private final AtividadeService atividadeService = new AtividadeService();

    public UsuariosBlockService() {
        this.bloqueios = UsuariosBlockDAO.getInstance();
        this.usuarios = UsuarioDAO.getInstance();
        this.associacoes = AssociacoesClientesDAO.getInstance();
    }

    public void bloquear(Long idUsuario, Long idBloqueado) throws SQLException {
        Usuario usuario = usuarios.pesquisar(idUsuario);
        Usuario bloqueado = usuarios.pesquisar(idBloqueado);
        
        System.out.println("usuario.getIdGestor(): " + usuario.getIdGestor());
        System.out.println("bloqueado.getIdGestor(): " + bloqueado.getIdGestor());
        
        // SE O BLOQUEADO É CLIENTE DO USUÁRIO
        if (Objects.equals(bloqueado.getIdGestor(), usuario.getId())) {
            associacoes.aprovarDesassociacao(bloqueado.getId());
        }

        // SE O USUÁRIO É CLIENTE DO BLOQUEADO
        if (Objects.equals(usuario.getIdGestor(), bloqueado.getId())) {
            associacoes.aprovarDesassociacao(usuario.getId());
        }
        AtividadeReferencia ref = new AtividadeReferencia();
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(idUsuario);
        List<AtividadeReferencia> refs = new ArrayList<>();
        refs.add(ref);
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(idBloqueado);
        refs.add(ref);
        bloqueios.bloquear(idUsuario, idBloqueado);
        String descricao = "o usuario "+ idUsuario+" bloqueou o usuario " + idBloqueado;
        atividadeService.registrarAtividadeComReferencias(TipoAtividades.BLOQUEIO_USUARIO.name(), descricao, idUsuario, refs);
    }

    public void desbloquear(Long idUsuario, Long idBloqueado) throws SQLException {
        AtividadeReferencia ref = new AtividadeReferencia();
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(idUsuario);
        List<AtividadeReferencia> refs = new ArrayList<>();
        refs.add(ref);
        ref.setTipoEntidade(TipoEntidades.USUARIO.name());
        ref.setEntidadeId(idBloqueado);
        refs.add(ref);
        bloqueios.desbloquear(idUsuario, idBloqueado);
        String descricao = "o usuario "+ idUsuario+" desbloqueou o usuario " + idBloqueado;
        atividadeService.registrarAtividadeComReferencias(TipoAtividades.DESBLOQUEIO_USUARIO.name(), descricao, idUsuario, refs);
    }

    public boolean check(Long idUsuario, Long idBloqueado) throws SQLException {
        return bloqueios.check(idUsuario, idBloqueado);
    }
}
