package sam.model.helper;

import sam.model.dao.TransacaoDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Transacao;

import java.math.BigDecimal;
import java.sql.SQLException;

public class TransacaoHelper {

    private static final TransacaoDAO transacaoDAO;
    private static final UsuarioDAO usuarioDAO;

    static {
        transacaoDAO = TransacaoDAO.getInstance();
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public static String validarCadastroTransacao(Transacao transacao) throws SQLException {
        String erro = "";
        if (!validarCliente(transacao.getIdCliente()))
            erro += "Cliente inválido ";
        if (!validarQuantidade(transacao.getQuantidade()))
            erro += "Quantidade inválida ";
        if (!validarValor(transacao.getValor()))
            erro += "Valor inválido ";
        if (!validarBonus(transacao.getBonus()))
            erro += "Bônus inválido ";
        return erro;
    }

    private static boolean validarCliente(Long idCliente) throws SQLException {
        return !(idCliente == null || usuarioDAO.pesquisar(idCliente) == null);
    }

    private static boolean validarQuantidade(int quantidade) {
        return quantidade >= 1;
    }

    private static boolean validarValor(BigDecimal valor) {
        return !(valor == null || valor.scale() != 2 || valor.compareTo(new BigDecimal("0.01")) < 0);
    }

    private static boolean validarBonus(int bonus) {
        return bonus >= 0;
    }
}
