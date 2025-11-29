package sam.model.domain;

import sam.model.domain.util.Status;

public class Solicitacao {

    private Long id;
    private String nome;
    private String email;
    private String formaPagamento;
    private Status status;
    private Long id_usuario;

    public Solicitacao() {
    }

    public Solicitacao(String nome, String email, String formaPagamento, Long id_usuario) {
        this.nome = nome;
        this.email = email;
        this.formaPagamento = formaPagamento;
        this.status = Status.PENDENTE;
        this.id_usuario = id_usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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

    public String getPagamento() {
        return formaPagamento;
    }

    public void setPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Long getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

}
