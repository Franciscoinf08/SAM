package sam.model.domain;

import java.time.LocalDate;

public class FormObjetivos {
    private String nome;
    private String email;

    private LocalDate data;

    private String objetivosGerais;
    private String objetivosEspecificos;
    private String destPrincipal;
    private Integer numPessoas;
    private String prefCompanhia;
    private Float orcTotal;
    private String nivelDetalhamento;
    private String reqEspecificos;


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObjetivosGerais() {
        return objetivosGerais;
    }

    public void setObjetivosGerais(String objetivosGerais) {
        this.objetivosGerais = objetivosGerais;
    }

    public String getObjetivosEspecificos() {
        return objetivosEspecificos;
    }

    public void setObjetivosEspecificos(String objetivosEspecificos) {
        this.objetivosEspecificos = objetivosEspecificos;
    }

    public String getDestPrincipal() {
        return destPrincipal;
    }

    public void setDestPrincipal(String destPrincipal) {
        this.destPrincipal = destPrincipal;
    }

    public Integer getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(Integer numPessoas) {
        this.numPessoas = numPessoas;
    }

    public String getPrefCompanhia() {
        return prefCompanhia;
    }

    public void setPrefCompanhia(String prefCompanhia) {
        this.prefCompanhia = prefCompanhia;
    }

    public Float getOrcTotal() {
        return orcTotal;
    }

    public void setOrcTotal(Float orcTotal) {
        this.orcTotal = orcTotal;
    }

    public String getNivelDetalhamento() {
        return nivelDetalhamento;
    }

    public void setNivelDetalhamento(String nivelDetalhamento) {
        this.nivelDetalhamento = nivelDetalhamento;
    }

    public String getReqEspecificos() {
        return reqEspecificos;
    }

    public void setReqEspecificos(String reqEspecificos) {
        this.reqEspecificos = reqEspecificos;
    }
}

