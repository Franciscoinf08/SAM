package sam.model.domain.util;

public enum FaqStatus {
    ATIVA,
    REMOVIDA;

    public static FaqStatus strTo(String strTipo) {
        switch (strTipo) {
            case "ATIVA" :
                return ATIVA;
            case "REMOVIDA" :
                return REMOVIDA;
        }

        throw new RuntimeException(strTipo + " não é um tipo válido.");
    }
}
