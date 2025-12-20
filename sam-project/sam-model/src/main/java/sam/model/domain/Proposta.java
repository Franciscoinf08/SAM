package sam.model.domain;

import java.time.LocalDate;

public class Proposta {

    private int id;
    private Usuario cliente;
    private Usuario gestor;
    private String status;
    private double valorEmDinheiro;
    private Long valorEmMilhas;
    private double taxas;
    private String observacoes;
    private LocalDate dataIda;
    private LocalDate dataVolta;
    private String origem;
    private String destino;
    private int numAdultos;
    private int numCriancas;

    public Proposta() {
    }

    public Proposta(Usuario cliente, Usuario gestor, String status,
                    double valorEmDinheiro, Long valorEmMilhas, double taxas,
                    String observacoes, LocalDate dataIda, LocalDate dataVolta,
                    String origem, String destino, int numAdultos, int numCriancas) {

        this.cliente = cliente;
        this.gestor = gestor;
        this.status = status;
        this.valorEmDinheiro = valorEmDinheiro;
        this.valorEmMilhas = valorEmMilhas;
        this.taxas = taxas;
        this.observacoes = observacoes;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.origem = origem;
        this.destino = destino;
        this.numAdultos = numAdultos;
        this.numCriancas = numCriancas;
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

    public Long getValorEmMilhas() {
        return valorEmMilhas;
    }

    public void setValorEmMilhas(Long valorEmMilhas) {
        this.valorEmMilhas = valorEmMilhas;
    }

    public double getTaxas() {
        return taxas;
    }

    public void setTaxas(double taxas) {
        this.taxas = taxas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataIda() {
        return dataIda;
    }

    public void setDataIda(LocalDate dataIda) {
        this.dataIda = dataIda;
    }

    public LocalDate getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(LocalDate dataVolta) {
        this.dataVolta = dataVolta;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    public int getNumAdultos() {
        return numAdultos;
    }

    public void setNumAdultos(int numAdultos) {
        this.numAdultos = numAdultos;
    }

    public int getNumCriancas() {
        return numCriancas;
    }

    public void setNumCriancas(int numCriancas) {
        this.numCriancas = numCriancas;
    }

}
