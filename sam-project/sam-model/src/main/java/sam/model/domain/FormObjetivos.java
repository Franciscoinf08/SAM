package sam.model.domain;

import java.time.LocalDate;

public class FormObjetivos {
    private Integer id;
    private Long id_usuario;
    private String titulo_formulario;
    private LocalDate data;
    private String objetivosGerais;
    private String objetivosEspecificos;
    private String destPrincipal;
    private Integer numPessoas;
    private String prefCompanhia;
    private Float orcTotal;
    private String nivelDetalhamento;
    private String reqEspecificos;

    public Long getId_usuario(){return id_usuario;}

    public void setId_usuario(Long id_usuario){this.id_usuario=id_usuario;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getTitulo() {return titulo_formulario;}

    public void setTitulo(String titulo) {this.titulo_formulario = titulo;}

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

