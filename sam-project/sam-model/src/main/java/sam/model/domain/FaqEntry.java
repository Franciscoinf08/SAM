package sam.model.domain;

import sam.model.domain.util.FaqStatus;

public class FaqEntry {
    private Long id;
    private String titulo;
    private String pergunta;
    private String resposta;
    private FaqStatus status;

    public FaqEntry(String titulo, String pergunta, String resposta) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.status = FaqStatus.ATIVA;
    }

    public FaqEntry(Long id, String titulo, String pergunta, String resposta) {
        this.id = id;
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.status = FaqStatus.ATIVA;
    }

    public FaqEntry(Long id, String titulo, String pergunta, String resposta, FaqStatus status) {
        this.id = id;
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public FaqStatus getStatus() {
        return status;
    }

    public void setStatus(FaqStatus status) {
        this.status = status;
    }
}
