package sam.model.domain;

public class PerguntaTicket {
    private Long id;
    private Long idUsuario;
    private String titulo;
    private String descricao;

    public PerguntaTicket(Long id, Long idUsuario, String titulo, String descricao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
