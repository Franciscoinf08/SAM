package sam.model.domain.util;

public enum TipoEntidades {
    USUARIO("Usuário"),
    EMPRESA("Empresa"),
    PROGRAMA_FIDELIDADE("Programa de Fidelidade"),
    TRANSACAO("Transação"),
    SOLICITACAO("Solicitação"),
    DENUNCIA("Denúncia");

    private final String descricao;

    TipoEntidades(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
