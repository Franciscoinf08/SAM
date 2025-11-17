package sam.model.domain.util;

public enum TransacaoStatus {
    ATIVA,
    REMOVIDA;

    public static TransacaoStatus strTo(String strTipo) {
        switch (strTipo) {
            case "ATIVA" :
                return ATIVA;
            case "REMOVIDA" :
                return REMOVIDA;
        }

        throw new RuntimeException(strTipo + " não é um tipo válido.");
    }
}
