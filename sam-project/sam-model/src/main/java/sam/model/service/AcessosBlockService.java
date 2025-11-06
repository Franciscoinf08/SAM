//CONECTAR COM A CLASS AcessosBlockDAO
package sam.model.service;

import sam.model.dao.AcessosBlockDAO;

public class AcessosBlockService {
    private AcessosBlockDAO bloqueios;

    public AcessosBlockService(String usuario) {
        this.bloqueios = new AcessosBlockDAO(usuario); //OU getInstance
    }

    public void add(String recurso) {
       
    }

    public void delete(String recurso){
        
    }

    public boolean check(String recurso) {
        
    }
}