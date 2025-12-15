package sam.model.domain;

import java.time.LocalDate;
import java.util.List;
import sam.model.domain.FormObjetivos;

public class Proposta {

    private int id;
    private Usuario cliente;
    private Usuario gestor;
    private String status;
    private double valorEmDinheiro;
    private int valorEmMilhas;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataResposta;
    private LocalDate dataCancelamento;
    private FormObjetivos objetivo;

    public Proposta() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getGestor() {
        return gestor;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorEmDinheiro() {
        return valorEmDinheiro;
    }

    public void setValorEmDinheiro(double valorEmDinheiro) {
        this.valorEmDinheiro = valorEmDinheiro;
    }

    public int getValorEmMilhas() {
        return valorEmMilhas;
    }

    public void setValorEmMilhas(int valorEmMilhas) {
        this.valorEmMilhas = valorEmMilhas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDate dataResposta) {
        this.dataResposta = dataResposta;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public FormObjetivos getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(FormObjetivos objetivo) {
        this.objetivo = objetivo;
    }

}

