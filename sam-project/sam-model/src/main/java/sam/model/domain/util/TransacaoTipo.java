package sam.model.domain.util;

public enum TransacaoTipo {
    COMPRA,
    VENDA;

    public static TransacaoTipo strTo(String strTipo) {
        switch (strTipo) {
            case "COMPRA" :
                return COMPRA;
            case "VENDA" :
                return VENDA;
        }

        throw new RuntimeException(strTipo + " não é um tipo válido.");
    }
}
