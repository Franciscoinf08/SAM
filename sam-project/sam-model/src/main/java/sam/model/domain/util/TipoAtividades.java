package sam.model.domain.util;

public enum TipoAtividades {
    CADASTRO_USUARIO("Cadastro de Usuário"), //ja foi
    ALTERACAO_PERFIL("Alteração de Perfil"), //ja foi
    BLOQUEIO_USUARIO("Bloqueio de Usuário"), //ja foi
    ATIVACAO_USUARIO("Ativacao de Usuario"), //ja foi
    MUDANCA_SENHA("Mudança de Senha"),

    CADASTRO_EMPRESA("Cadastro de Empresa"), //ja foi
    EDICAO_EMPRESA("Edição de Empresa"), //ja foi
    EXCLUSAO_EMPRESA("Exclusão de Empresa"), // ja foi

    CADASTRO_PROGRAMA("Cadastro de Programa de Fidelidade"), // ja foi
    EDICAO_PROGRAMA("Edição de Programa de Fidelidade"), //ja foi
    EXCLUSAO_PROGRAMA("Exclusão de Programa de Fidelidade"), //ja foi
    ASSOCIACAO_CLIENTE_PROGRAMA("Associação de Cliente ao Programa"), //ja foi
    DESASSOCIACAO_CLIENTE_PROGRAMA("Desassociacao de Cliente ao programa"), //ja foi

    TRANSACAO_VENDA("Transação de Venda"), //ja foi
    TRANSACAO_COMPRA("Transação de Compra"), //ja foi
    TRANSACAO_EXCLUSAO("Exclusao de transacao"), //ja foi

    SOLICITACAO_GESTOR("Solicitação para Gestor"),
    DENUNCIA("Denúncia"),
    AVALIACAO("Avaliação");

    private final String descricao;

    TipoAtividades(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
