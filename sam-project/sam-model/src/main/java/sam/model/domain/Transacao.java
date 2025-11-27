package sam.model.domain;

import sam.model.domain.util.TransacaoStatus;
import sam.model.domain.util.TransacaoTipo;

import java.math.BigDecimal;
import java.sql.Date;

public class Transacao {
    private Long id;
    private long idProgramaFidelidade;
    private Long idCliente;
    private Date data;
    private Date dataExpiracao;
    private int quantidade;
    private TransacaoTipo tipo;
    private BigDecimal valor;
    private int bonus;
    private TransacaoStatus status;

    public Transacao(Long idProgramaFidelidade, Long idCliente, Date data, Date dataExpiracao, int quantidade, TransacaoTipo tipo, BigDecimal valor, int bonus) {
        this.idProgramaFidelidade = idProgramaFidelidade;
        this.idCliente = idCliente;
        this.data = data;
        this.dataExpiracao = dataExpiracao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor = valor;
        this.bonus = bonus;
        this.status = TransacaoStatus.ATIVA;
    }

    public Transacao(Long idProgramaFidelidade, Long idCliente, Date data, Date dataExpiracao, int quantidade, TransacaoTipo tipo, BigDecimal valor, int bonus, TransacaoStatus status) {
        this.idProgramaFidelidade = idProgramaFidelidade;
        this.idCliente = idCliente;
        this.data = data;
        this.dataExpiracao = dataExpiracao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor = valor;
        this.bonus = bonus;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getIdProgramaFidelidade() { return idProgramaFidelidade; }

    public Long getIdCliente() { return idCliente; }

    public Date getData() {
        return data;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
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
