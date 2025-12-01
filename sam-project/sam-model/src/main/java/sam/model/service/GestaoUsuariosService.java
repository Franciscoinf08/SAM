package sam.model.service;

import sam.model.dao.UsuarioDAO;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;
import sam.model.helper.UsuarioHelper;

import java.sql.SQLException;
import java.util.List;

public class GestaoUsuariosService {

    private final UsuarioDAO usuarioDAO;

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
        try {
            usuarioDAO.inserir(usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void atualizar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!"".equals(UsuarioHelper.validarAtualizacaoUsuario(usuario)))
            throw new PersistenciaException(UsuarioHelper.validarAtualizacaoUsuario(usuario));

        try {
            usuarioDAO.atualizar(usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}

