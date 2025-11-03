
package sam.model.domain;

import java.util.List;

public class Empresa {
    private int idEmpresa;
    private String nome;
    private String CNPJ;
    private double milheiroSeguranca;
    private List<ProgramaFidelidade> listaProgramasFidelidade;
    
    public Empresa(String nome, String CNPJ, List<ProgramaFidelidade> lista, int id, int milheiroSeguranca){
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.listaProgramasFidelidade = lista;
        this.milheiroSeguranca = milheiroSeguranca;
    }

    public Empresa() {
    }

    public String getNome() {
        return nome;
    }
    
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public String getCNPJ() {
        return CNPJ;
    }

    public List<ProgramaFidelidade> getListaProgramasFidelidade() {
        return listaProgramasFidelidade;
    }

    public void setListaProgramasFidelidade(List<ProgramaFidelidade> listaProgramasFidelidade) {
        this.listaProgramasFidelidade = listaProgramasFidelidade;
    }
    public int getId(){
        return getIdEmpresa();
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public double getMilheiroSeguranca() {
        return milheiroSeguranca;
    }

    public void setMilheiroSeguranca(double milheiroSeguranca) {
        this.milheiroSeguranca = milheiroSeguranca;
    }
    

}

