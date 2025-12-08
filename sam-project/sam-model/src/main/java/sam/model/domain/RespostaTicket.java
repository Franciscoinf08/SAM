package sam.model.domain;

public class RespostaTicket {
    private Long id;
    private Long idUsuario;
    private Long idPergunta;
    private String descricao;

    public RespostaTicket(Long id, Long idUsuario, Long idPergunta, String descricao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPergunta = idPergunta;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }
}
