package sam.model.domain.util;

public enum TipoAtividades {
    CADASTRO_USUARIO("Cadastro de Usuário"), //ja foi
    ALTERACAO_PERFIL("Alteração de Perfil"), //ja foi
    BLOQUEIO_ACESSO_USUARIO("Bloqueio de Usuário"), //ja foi
    ATIVACAO_ACESSO_USUARIO("Ativacao de Usuario"), //ja foi
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

    SOLICITACAO_GESTOR("Solicitação para Gestor"), //ja foi
    TORNAR_CLIENTE("Tornar cliente"), //ja foi
    SOLICITACAO_ACEITA("Solicitacao para gestor aceita"), //ja foi
    SOLICITACAO_RECUSADA("Solicitacao para gestor recusada"), //ja foi
    DENUNCIA("Denúncia"),

    BLOQUEIO_USUARIO("usuario bloqueado por outro "),
    DESBLOQUEIO_USUARIO("usuario desbloqueado por outro "),
    ACEITAR_SOLICITACAO_ASSOCIAR("associacao cliente gestor aceita "), //ja foi
    ACEITAR_SOLICITACAO_DESASSOCIAR("desassociacao cliente gestor aceita"), //ja foi

    SOLICITACAO_ASSOCIACAO("solicitacao de associacao cliente gestor"), //ja foi
    SOLICITACAO_DESASSOCIACAO("solicitacao de desassociacao cliente gestor"), //ja foi

    RECUSAR_DESASOCIACAO_CLIENTE("desassociacao cliente gestor recusada"), //ja foi
    RECUSAR_SOLICITACAO_SEM_TIPO("solicitacao sem tipo especificado recusada"),
    RECUSAR_ASSOCIACAO_CLIENTE("associacao cliente gestor recusada"); //ja foi

    private final String descricao;

    TipoAtividades(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
