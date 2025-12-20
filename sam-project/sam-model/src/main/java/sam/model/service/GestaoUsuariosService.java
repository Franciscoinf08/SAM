package sam.model.service;

import sam.model.dao.UsuarioDAO;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Usuario;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;
import sam.model.domain.util.UsuarioTipo;
import sam.model.helper.UsuarioHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestaoUsuariosService {

    private final UsuarioDAO usuarioDAO;
    private final AtividadeService atividadeService = new AtividadeService();

    public GestaoUsuariosService() {
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public Usuario pesquisar(Long id) throws SQLException {
        try {
            return usuarioDAO.pesquisar(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Usuario pesquisarConta(String cpf, String senha) throws PersistenciaException, SQLException {
        try {
            return usuarioDAO.pesquisarPorCPFSenha(cpf, senha);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void cadastrar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!"".equals(UsuarioHelper.validarCadastroUsuario(usuario)))
            throw new PersistenciaException(UsuarioHelper.validarCadastroUsuario(usuario));
        String descricao =  "O usuario: " + usuario.getNome() + " foi cadastrado ao sistema";
        try {

            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(usuario.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);

            usuarioDAO.inserir(usuario);
            atividadeService.registrarAtividadeComReferencias(TipoAtividades.CADASTRO_USUARIO.name(), descricao, usuario.getId(), refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void atualizar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!"".equals(UsuarioHelper.validarAtualizacaoUsuario(usuario)))
            throw new PersistenciaException(UsuarioHelper.validarAtualizacaoUsuario(usuario));
        Usuario antigo = pesquisar(usuario.getId());
        String descricao = descricaoAlteracao(antigo, usuario);
        try {
            usuarioDAO.atualizar(usuario);
            AtividadeReferencia ref = new AtividadeReferencia();
            ref.setTipoEntidade(TipoEntidades.USUARIO.name());
            ref.setEntidadeId(usuario.getId());
            List<AtividadeReferencia> refs = new ArrayList<>();
            refs.add(ref);
            atividadeService.registrarAtividadeComReferencias(TipoAtividades.ALTERACAO_PERFIL.name(), descricao, usuario.getId(), refs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private String descricaoAlteracao(Usuario antigo, Usuario novo){
        String descricao = "o perfil de: "+ antigo.getId()+ " foi alterado.<br>" +
                "Antigo: " +
                "<br>Nome: " + antigo.getNome() +
                "<br>cpf: " + antigo.getCPF() +
                "<br>Email: " + antigo.getEmail() + "<br>" +
                "<br>Novo: <br>" +
                "<br>Nome: " + novo.getNome() +
                "<br>cpf: " + novo.getCPF() +
                "<br>Email: " + novo.getEmail();
        return descricao;
    }

    public List<Usuario> getListaClientes(Usuario usuario) throws SQLException {
        if (usuario.getTipo() != UsuarioTipo.GESTOR)
            throw new RuntimeException("Acesso negado");

        return usuarioDAO.getListaClientes(usuario);
    }


    public Usuario buscarPorEmail(String email) throws SQLException {
        return usuarioDAO.pesquisarPorEmail(email);
    }
}

