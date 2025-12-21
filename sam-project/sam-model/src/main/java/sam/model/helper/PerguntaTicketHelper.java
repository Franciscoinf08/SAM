package sam.model.helper;

import sam.model.domain.PerguntaTicket;

public class PerguntaTicketHelper {

    public static String validarPergunta(PerguntaTicket pergunta) {
        String erro = "";
        if (pergunta.getId() != null && pergunta.getId() <= 0)
            erro += "Id inválido ";
        if ("".equals(pergunta.getTitulo()))
            erro += "Título inválido";
        return erro;
    }
}
