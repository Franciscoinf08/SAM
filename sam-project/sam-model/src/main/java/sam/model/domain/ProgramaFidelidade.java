/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sam.model.domain;


public class ProgramaFidelidade {
    
    private int idProgramaFidelidade;
    private String nome;
    private double bonusMilhas;
    private int qtdeMilhasMes;
    private int duracao;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    private int idEmpresa;

    public ProgramaFidelidade(String nome, double bonusMilhas, int qtdeMilhasMes, int duracao, int idEmpresa) {
        this.nome = nome;
        this.bonusMilhas = bonusMilhas;
        this.qtdeMilhasMes = qtdeMilhasMes;
        this.duracao = duracao;
        this.idEmpresa = idEmpresa;
    }

    public ProgramaFidelidade(){}
    public void setIdProgramaFidelidade(int idProgramaFidelidade) {
        this.idProgramaFidelidade = idProgramaFidelidade;
    }

    public int getIdProgramaFidelidade() {
        return idProgramaFidelidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getBonusMilhas() {
        return bonusMilhas;
    }

    public void setBonusMilhas(double bonusMilhas) {
        this.bonusMilhas = bonusMilhas;
    }

    public int getQtdeMilhasMes() {
        return qtdeMilhasMes;
    }

    public void setQtdeMilhasMes(int qtdeMilhasMes) {
        this.qtdeMilhasMes = qtdeMilhasMes;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
}
