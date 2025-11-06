package sam.model.service;

import sam.model.dao.UsuarioDAO;
import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.helper.UsuarioHelper;

import java.sql.SQLException;

public class GestaoUsuariosService {

    private final UsuarioDAO usuarioDAO;

    public GestaoUsuariosService() {
        usuarioDAO = UsuarioDAO.getInstance();
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

    public Long cadastrar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!UsuarioHelper.validarCadastroUsuario(usuario).equals(""))
            throw new PersistenciaException(UsuarioHelper.validarCadastroUsuario(usuario));

        try {
            return usuarioDAO.inserir(usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void atualizar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!UsuarioHelper.validarAtualizacaoUsuario(usuario).equals(""))
            throw new PersistenciaException(UsuarioHelper.validarAtualizacaoUsuario(usuario));

        try {
            usuarioDAO.atualizar(usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
