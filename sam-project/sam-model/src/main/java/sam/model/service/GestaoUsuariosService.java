package sam.model.service;

import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.dao.UsuarioDAO;

import java.sql.SQLException;

// Placeholder
public class GestaoUsuariosService {

    private final UsuarioDAO usuarioDAO;

    public GestaoUsuariosService() {
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public Usuario pesquisarConta(String cpf, String senha) throws PersistenciaException, SQLException {
        return usuarioDAO.pesquisarPorCPFSenha(cpf, senha);
    }

    public void cadastrar(Usuario usuario) throws PersistenciaException, SQLException {
        usuarioDAO.inserir(usuario);
    }

    public void atualizar(Usuario usuario) throws PersistenciaException, SQLException {
        usuarioDAO.atualizar(usuario);
    }
}
