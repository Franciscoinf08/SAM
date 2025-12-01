package sam.model.domain.util;

public enum Status {
    PENDENTE,
    AGUARDANDO,
    RECUSADO,
    APROVADO,
    CANCELADO;

    public static Status strTo(String strStatus) {
        switch (strStatus) {
            case "PENDENTE" :
                return PENDENTE;
            case "AGUARDANDO" :
                return AGUARDANDO;
            case "RECUSADO" :
                return RECUSADO;
            case "APROVADO" :
                return APROVADO;
            case "CANCELADO" :
                return CANCELADO;
        }

        throw new RuntimeException(strStatus + " não é um tipo válido.");
    }
}
