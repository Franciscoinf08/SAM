package sam.model.helper;

import sam.model.dao.UsuarioDAO;
import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;

import java.sql.SQLException;

public class UsuarioHelper {

    private static final UsuarioDAO usuarioDAO;

    static {
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public static String validarAtualizacaoUsuario(Usuario usuario) throws PersistenciaException, SQLException {
        String erro = "";
        if (!validarAtualizacaoEmail(usuario))
            erro += "E-mail já cadastrado ";
        if (!validarAtualizacaoCPF(usuario))
            erro += "CPF já cadastrado";
        return erro;
    }

    public static String validarCadastroUsuario(Usuario usuario) throws PersistenciaException, SQLException {
        String erro = "";
        if (!validarCadastroEmail(usuario))
            erro += "E-mail já cadastrado ";
        if (!validarCadastroCPF(usuario))
            erro += "CPF já cadastrado";
        return erro;
    }

    private static boolean validarAtualizacaoEmail(Usuario usuario) throws PersistenciaException, SQLException {
        return validarCadastroEmail(usuario) || usuario.equals(usuarioDAO.pesquisarPorEmail(usuario.getEmail()));
    }

    private static boolean validarAtualizacaoCPF(Usuario usuario) throws PersistenciaException, SQLException {
        return validarCadastroCPF(usuario) || usuario.equals(usuarioDAO.pesquisarPorCPF(usuario.getCPF()));
    }

    private static boolean validarCadastroEmail(Usuario usuario) throws PersistenciaException, SQLException {
        return usuarioDAO.pesquisarPorEmail(usuario.getEmail()) == null;
    }

    private static boolean validarCadastroCPF(Usuario usuario) throws PersistenciaException, SQLException {
       return usuarioDAO.pesquisarPorCPF(usuario.getCPF()) == null;
    }
}
