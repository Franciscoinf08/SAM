package sam.model.helper;

import sam.model.domain.RespostaTicket;

public class RespostaTicketHelper {

    public static String validarResposta(RespostaTicket resposta) {
        String erro = "";
        if (resposta.getId() != null && resposta.getId() <= 0)
            erro += "Id inválido ";
        if (resposta.getIdUsuario() != null && resposta.getIdUsuario() <= 0)
            erro += "Usuário inválido ";
        if (resposta.getIdPergunta() != null && resposta.getIdPergunta() <= 0)
            erro += "Pergunta inválida ";
        if ("".equals(resposta.getDescricao()))
            erro += "Descrição inválida";
        return erro;
    }
}
