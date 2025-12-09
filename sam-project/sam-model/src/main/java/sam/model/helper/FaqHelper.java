package sam.model.helper;

import sam.model.domain.FaqEntry;

public class FaqHelper {

    public static String validarFaq(FaqEntry faq) {
        String erro = "";
        if ("".equals(faq.getTitulo()))
            erro += "Título inválido ";
        if ("".equals(faq.getResposta()))
            erro += "Resposta inválida";
        return erro;
    }
}
