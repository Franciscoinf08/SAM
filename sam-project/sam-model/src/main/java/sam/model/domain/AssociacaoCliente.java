package sam.model.domain;

import sam.model.domain.util.AssociacaoClienteTipo;

public class AssociacaoCliente {
    private Long id;
    private Long idCliente;
    private Long idGestor;
    private AssociacaoClienteTipo tipo;
    
    public AssociacaoCliente(Long idCliente, Long idGestor, AssociacaoClienteTipo tipo){
        this.idCliente = idCliente;
        this.idGestor = idGestor;
        this.tipo = tipo;
    }
    
    public void setId(Long id){ this.id = id; }
    
    public Long getId() { return id; }
    
    public void setIdCliente(Long id){ this.idCliente = id; }
    
    public Long getIdCliente() { return idCliente; }
    
    public void setIdGestor(Long id){ this.idGestor = id; }
    
    public Long getIdGestor() { return idGestor; }
    
    public void setId(AssociacaoClienteTipo tipo){ this.tipo = tipo; }
    
    public AssociacaoClienteTipo getTipo() { return tipo; }
    
}
