package sam.model.helper;

import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.domain.Transacao;
import sam.model.domain.util.TransacaoTipo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public class TransacaoHelper {

    private static final UsuarioDAO usuarioDAO;
    private static final ProgramaFidelidadeDAO programaFidelidadeDAO;

    static {
        usuarioDAO = UsuarioDAO.getInstance();
        programaFidelidadeDAO = new ProgramaFidelidadeDAO();
    }

    public static String validarCadastroTransacao(Transacao transacao) throws SQLException {
        String erro = "";
        if (!validarCliente(transacao.getIdCliente()))
            erro += "Cliente inválido ";
        if (!validarPrograma(transacao.getIdProgramaFidelidade()))
            erro += "Programa de Fidelidade inválido";
        if (!validarData(transacao.getData()))
            erro += "Data inválida ";
        else if (!validarDataExpiracao(transacao.getData(), transacao.getDataExpiracao()))
            erro += "Data de Expiração inválida";
        if (!validarQuantidade(transacao.getQuantidade()))
            erro += "Quantidade inválida ";
        if (!validarValor(transacao.getValor()))
            erro += "Valor inválido ";
        if (!validarBonus(transacao.getBonus(), transacao.getTipo()))
            erro += "Bônus inválido ";
        return erro;
    }

    private static boolean validarCliente(Long idCliente) throws SQLException {
        return !(idCliente == null || usuarioDAO.pesquisar(idCliente) == null);
    }

    private static boolean validarPrograma(Long idPrograma) {
        return !(idPrograma == null || programaFidelidadeDAO.buscarPorId(idPrograma.intValue()) == null);
    }

    private static boolean validarData(Date data) {
        return data != null;
    }

    private static boolean validarDataExpiracao(Date data, Date dataExpiracao) {
        return dataExpiracao != null && dataExpiracao.after(data) && !dataExpiracao.equals(data);
    }

    private static boolean validarQuantidade(int quantidade) {
        return quantidade >= 1;
    }

    private static boolean validarValor(BigDecimal valor) {
        return !(valor == null || valor.scale() != 2 || valor.compareTo(new BigDecimal("0.01")) < 0);
    }

    private static boolean validarBonus(int bonus, TransacaoTipo tipo) {
        return bonus == 0 || (bonus > 0 && tipo == TransacaoTipo.COMPRA);
    }
}
