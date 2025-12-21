package sam.model.domain;

import sam.model.domain.util.PerguntaTicketStatus;

public class PerguntaTicket {
    private Long id;
    private Long idUsuario;
    private String titulo;
    private String descricao;
    private PerguntaTicketStatus status;

    public PerguntaTicket(Long idUsuario, String titulo, String descricao) {
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descricao = descricao;
        status = PerguntaTicketStatus.ATIVA;
    }

    public PerguntaTicket(Long id, Long idUsuario, String titulo, String descricao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descricao = descricao;
        status = PerguntaTicketStatus.ATIVA;
    }

    public PerguntaTicket(Long id, Long idUsuario, String titulo, String descricao, PerguntaTicketStatus status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaTicketStatus getStatus() {
        return status;
    }

    public void setStatus(PerguntaTicketStatus status) {
        this.status = status;
    }
}
