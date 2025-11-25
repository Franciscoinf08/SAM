package sam.model.domain.util;

import sam.model.domain.Transacao;

import java.util.Comparator;

public class OrdenarTransacaoPorDataExpiracao implements Comparator<Transacao> {
    @Override
    public int compare(Transacao transacao1, Transacao transacao2) {
        return transacao1.getDataExpiracao().compareTo(transacao2.getDataExpiracao());
    }
}
