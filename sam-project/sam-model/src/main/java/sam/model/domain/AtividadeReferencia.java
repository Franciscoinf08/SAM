package sam.model.domain;


//essa classe apresenta quais entidades estao relacionas nas atividades do sistema - favor nao apagar esse comentario pra nao esquecer
public class AtividadeReferencia {
    private Long id;
    private Long atividadeId;
    private String tipoEntidade;
    private Long entidadeId;

    public AtividadeReferencia() {
    }

    public AtividadeReferencia(Long atividadeId, String tipoEntidade, Long entidadeId) {
        this.atividadeId = atividadeId;
        this.tipoEntidade = tipoEntidade;
        this.entidadeId = entidadeId;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    public String getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(String tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Long entidadeId) {
        this.entidadeId = entidadeId;
    }
}
