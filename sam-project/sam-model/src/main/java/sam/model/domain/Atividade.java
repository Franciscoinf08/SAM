package sam.model.domain;

import sam.model.domain.util.TipoAtividades;

import java.time.LocalDateTime;

public class Atividade {
    private Long id;
    private String tipo;
    private String descricao;
    private Long usuarioExecutorId;
    private LocalDateTime dataHora;

    public Atividade() {

    }

    public Atividade(String tipo, String descricao, Long usuarioExecutorId) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.usuarioExecutorId = usuarioExecutorId;
        this.dataHora = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getUsuarioExecutorId() {
        return usuarioExecutorId;
    }

    public void setUsuarioExecutorId(Long usuarioExecutorId) {
        this.usuarioExecutorId = usuarioExecutorId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
