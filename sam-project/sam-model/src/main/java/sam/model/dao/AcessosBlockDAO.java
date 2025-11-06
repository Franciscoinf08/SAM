package sam.model.dao; //FAZER USANDO O BANCO DE DADOS

import java.util.HashSet;
import java.util.Set;

public class AcessosBlockDAO {
    private String usuario; //CPF
    private Set<String> recursos;

    public AcessosBlockDAO(String usuario) {
        this.usuario = usuario;
        this.recursos = new HashSet();
    }

    public void add(String recurso) {
        recursos.add(recurso);
    }

    public void delete(String recurso){
        recursos.remove(recurso);
    }

    public boolean check(String recurso) {
        return recursos.contains(recurso);
    }
}
