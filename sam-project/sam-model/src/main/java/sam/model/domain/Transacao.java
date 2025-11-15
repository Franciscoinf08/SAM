package sam.model.domain;

import sam.model.domain.util.TransacaoTipo;

import java.math.BigDecimal;

public class Transacao {
    private Long id;
    private final Usuario cliente;
    private int quantidade;
    private TransacaoTipo tipo;
    private BigDecimal valor;
    private BigDecimal bonus;

    public Transacao(Usuario cliente, int quantidade, TransacaoTipo tipo, BigDecimal valor, BigDecimal bonus) {
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor = valor;
        this.bonus = bonus;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Usuario getCliente() { return cliente; }

    public Long getIdCliente() { return cliente.getId(); }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TransacaoTipo getTipo() { return tipo; }

    public void setTipo(TransacaoTipo tipo) { this.tipo = tipo; }

    public BigDecimal getBonus() { return bonus; }

    public void setBonus(BigDecimal bonus) { this.bonus = bonus;  }

    public BigDecimal getValor() { return valor; }

    public void setValor(BigDecimal valor) { this.valor = valor; }
}
