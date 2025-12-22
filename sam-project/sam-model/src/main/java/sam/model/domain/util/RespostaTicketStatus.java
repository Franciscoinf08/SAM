package sam.model.domain.util;

public enum RespostaTicketStatus {
    ATIVA,
    REMOVIDA;

    public static RespostaTicketStatus strTo(String strTipo) {
        switch (strTipo) {
            case "ATIVA" :
                return ATIVA;
            case "REMOVIDA" :
                return REMOVIDA;
        }

        throw new RuntimeException(strTipo + " não é um tipo válido.");
    }
}
