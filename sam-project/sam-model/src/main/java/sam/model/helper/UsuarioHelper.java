package sam.model.helper;

import sam.model.dao.UsuarioDAO;
import sam.model.domain.Usuario;

import java.sql.SQLException;
import java.util.Objects;

public class UsuarioHelper {

    private static final UsuarioDAO usuarioDAO;

    static {
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public static String validarAtualizacaoUsuario(Usuario usuario) throws SQLException {
        String erro = "";
        if (!validarEmail(usuario.getEmail()))
            erro += "E-mail inválido ";
        else if (!validarAtualizacaoEmail(usuario))
            erro += "E-mail já cadastrado ";
        return erro;
    }

    public static String validarCadastroUsuario(Usuario usuario) throws SQLException {
        String erro = "";
        if (!validarEmail(usuario.getEmail()))
            erro += "E-mail inválido ";
        else if (!validarCadastroEmail(usuario))
            erro += "E-mail já cadastrado ";
        if (!validarCPF(usuario.getCPF()))
            erro += "CPF inválido ";
        else if (!validarCadastroCPF(usuario))
            erro += "CPF já cadastrado";
        return erro;
    }

    private static boolean validarAtualizacaoEmail(Usuario usuario) throws SQLException {
        return validarCadastroEmail(usuario) || usuario.equals(usuarioDAO.pesquisarPorEmail(usuario.getEmail()));
    }

    private static boolean validarCadastroEmail(Usuario usuario) throws SQLException {
        return usuarioDAO.pesquisarPorEmail(usuario.getEmail()) == null;
    }

    private static boolean validarCadastroCPF(Usuario usuario) throws SQLException {
        return usuarioDAO.pesquisarPorCPF(usuario.getCPF()) == null;
    }

    public static boolean validarCPF(String cpf) {
        if (Objects.equals(cpf, "00000000000") || cpf.length() != 11)
            return false;
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        int soma = 0;
        int resto;

        for (int i = 1; i <= 9; i++)
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i - 1))) * (11 - i);
        resto = (soma * 10) % 11;
        if (resto == 10)
            resto = 0;
        if (resto != Integer.parseInt(String.valueOf(cpf.charAt(9))))
            return false;

        soma = 0;
        for (int i = 1; i <= 10; i++)
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i - 1))) * (12 - i);
        resto = (soma * 10) % 11;
        if (resto == 10)
            resto = 0;
        return resto == Integer.parseInt(String.valueOf(cpf.charAt(10)));
    }

    public static boolean validarEmail(String email) {
        String usuario;
        String dominio;
        try {
            usuario = email.substring(0, email.indexOf("@"));
            dominio = email.substring(email.indexOf("@") + 1);
        } catch (Exception e) {
            return false;
        }

        return !usuario.isEmpty() &&
                !dominio.isEmpty() &&
                !usuario.contains("@") &&
                !dominio.contains("@") &&
                !usuario.contains(" ") &&
                !dominio.contains(" ") &&
                dominio.indexOf(".") >= 1 &&
                dominio.lastIndexOf(".") < dominio.length() - 1;
    }
}