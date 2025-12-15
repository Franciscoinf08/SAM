package sam.model.domain;

import sam.model.domain.util.RespostaTicketStatus;

public class RespostaTicket {
    private Long id;
    private Long idUsuario;
    private Long idPergunta;
    private String descricao;
    private RespostaTicketStatus status;

    public RespostaTicket(Long id, Long idUsuario, Long idPergunta, String descricao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPergunta = idPergunta;
        this.descricao = descricao;
        status = RespostaTicketStatus.ATIVA;
    }

    public RespostaTicket(Long idUsuario, Long idPergunta, String descricao) {
        this.idUsuario = idUsuario;
        this.idPergunta = idPergunta;
        this.descricao = descricao;
        status = RespostaTicketStatus.ATIVA;
    }

    public RespostaTicket(Long id, Long idUsuario, Long idPergunta, String descricao, RespostaTicketStatus status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPergunta = idPergunta;
        this.descricao = descricao;
        this.status = status;
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

    public RespostaTicketStatus getStatus() {
        return status;
    }

    public void setStatus(RespostaTicketStatus status) {
        this.status = status;
    }
}
