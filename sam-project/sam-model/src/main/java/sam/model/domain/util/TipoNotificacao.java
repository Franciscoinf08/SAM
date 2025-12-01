package sam.model.domain.util;

public enum TipoNotificacao {
    EXPIRACAO_PROGRAMA("Um programa de fidelidade esta acabando!!"),
    OFERTA_PROGRAMA("Temos uma proposta vantajosa para voce!!"),
    REUNIAO_CHEGANDO("Sua reunião está chegando!!"),
    NOTIFICACAO_GESTOR("Seu gestor tem um novo aviso para você"),
    OUTROS("");
    private final String descricao;

    TipoNotificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
