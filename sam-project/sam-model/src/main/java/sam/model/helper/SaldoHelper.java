package sam.model.helper;

import sam.model.domain.Transacao;
import sam.model.domain.Usuario;
import sam.model.domain.util.TransacaoTipo;
import sam.model.service.GestaoTransacoesService;

import java.math.BigDecimal;
import java.sql.SQLException;

public class SaldoHelper {
    private static final GestaoTransacoesService manterTransacao;

    static {
        manterTransacao = new GestaoTransacoesService();
    }

    public static BigDecimal getSaldoDinheiroCliente(Usuario cliente) throws SQLException {
        BigDecimal valor = BigDecimal.ZERO;

        for (Transacao transacao : manterTransacao.listarPorCliente(cliente))
            if (transacao.getTipo() == TransacaoTipo.COMPRA)
                valor = valor.subtract(transacao.getValor());
            else
                valor = valor.add(transacao.getValor());

        return valor;
    }

    public static int getMilhasCompradasCliente(Usuario cliente) throws SQLException {
        int milhas = 0;

        for (Transacao transacao : manterTransacao.listarAtivasPorCliente(cliente)) {
            if (transacao.getTipo() == TransacaoTipo.COMPRA)
                milhas += transacao.getQuantidade();
        }

        return milhas;
    }

    public static int getMilhasVendidasCliente(Usuario cliente) throws SQLException {
        int milhas = 0;

        for (Transacao transacao : manterTransacao.listarAtivasPorCliente(cliente)) {
            if (transacao.getTipo() == TransacaoTipo.VENDA)
                milhas += transacao.getQuantidade();
        }

        return milhas;
    }

    public static int getSaldoQuantidadeCliente(Usuario cliente) throws SQLException {
        int saldo = 0;

        for (Transacao transacao : manterTransacao.listarAtivasPorCliente(cliente)) {
            if (transacao.getTipo() == TransacaoTipo.COMPRA)
                saldo += transacao.getQuantidade();
            else
                saldo -= transacao.getQuantidade();
        }

        return saldo;
    }

    public static int getBonusCliente(Usuario cliente) throws SQLException {
        int bonus = 0;

        for (Transacao transacao : manterTransacao.listarAtivasPorCliente(cliente))
            bonus += transacao.getBonus();

        return bonus;
    }

    public static int getSaldoMilhasCliente(Usuario cliente) throws SQLException {
        int saldo = 0;

        for (Transacao transacao : manterTransacao.listarAtivasPorCliente(cliente)) {
            if (transacao.getTipo() == TransacaoTipo.COMPRA)
                saldo += transacao.getQuantidade();
            else
                saldo -= transacao.getQuantidade();
            saldo += transacao.getBonus();
        }

        return saldo;
    }
}
