package sam.model.domain.util;

public enum TransacaoStatus {
    ATIVA,
    REMOVIDA,
    EXPIRADA;

    public static TransacaoStatus strTo(String strTipo) {
        switch (strTipo) {
            case "ATIVA" :
                return ATIVA;
            case "REMOVIDA" :
                return REMOVIDA;
            case "EXPIRADA" :
                return EXPIRADA;
        }

        throw new RuntimeException(strTipo + " não é um tipo válido.");
    }
}