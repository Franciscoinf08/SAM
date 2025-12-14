package sam.model.domain.util;

public enum AssociacaoClienteTipo {
    DESASSOCIAR,
    ASSOCIAR;

    public static AssociacaoClienteTipo strTo(String strTipo) {
        switch (strTipo) {
            case "DESASSOCIAR" :
                return DESASSOCIAR;
            case "ASSOCIAR" :
                return ASSOCIAR;
        }

        throw new RuntimeException(strTipo + " não é um tipo válido.");
    }
}
