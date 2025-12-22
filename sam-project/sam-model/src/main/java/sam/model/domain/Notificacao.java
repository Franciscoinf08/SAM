package sam.model.domain;


import java.time.LocalDateTime;

public class Notificacao {
    int idNotificacao;
    LocalDateTime data;
    String descricao;
    String titulo;
    int idDestinatario;
    boolean lida;

    public Notificacao(String descricao, String titulo, int idDestinatario) {
        this.data = LocalDateTime.now();
        this.descricao = descricao;
        this.titulo = titulo;
        this.idDestinatario = idDestinatario;
        this.lida = false;
    }

    public Notificacao() {
        this.data = LocalDateTime.now();
    }


    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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

    public int getDestinatario() {
        return idDestinatario;
    }

    public void setDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }
    public String getResumo() {
        if (descricao == null) return "";
        return descricao.length() <= 80
                ? descricao
                : descricao.substring(0, 80) + "...";
    }

    public int getId() {
        return idNotificacao;
    }
    public void setId(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }
}
