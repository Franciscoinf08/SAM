package sam.model.domain;

import sam.model.domain.util.TransacaoStatus;
import sam.model.domain.util.TransacaoTipo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transacao {
    private Long id;
    private final Long idCliente;
    private final Timestamp data;
    private int quantidade;
    private TransacaoTipo tipo;
    private BigDecimal valor;
    private int bonus;
    private TransacaoStatus status;

    public Transacao(Long idCliente, Timestamp data, int quantidade, TransacaoTipo tipo, BigDecimal valor, int bonus) {
        this.data = data;
        this.idCliente = idCliente;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor = valor;
        this.bonus = bonus;
        this.status = TransacaoStatus.ATIVA;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getIdCliente() { return idCliente; }

    public Timestamp getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TransacaoTipo getTipo() { return tipo; }

    public void setTipo(TransacaoTipo tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }

    public void setValor(BigDecimal valor) { this.valor = valor; }

    public int getBonus() { return bonus; }

    public void setBonus(int bonus) { this.bonus = bonus;  }

    public TransacaoStatus getStatus() {
        return status;
    }

    public void setStatus(TransacaoStatus status) {
        this.status = status;
    }
}
