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
    private double precoMensal;
    private String avaliacao;

    public ProgramaFidelidade(String nome, double bonusMilhas, int qtdeMilhasMes, int duracao, double precoMensal, String avaliacao) {
        this.nome = nome;
        this.bonusMilhas = bonusMilhas;
        this.qtdeMilhasMes = qtdeMilhasMes;
        this.duracao = duracao;
        this.precoMensal = precoMensal;
        this.avaliacao = avaliacao;
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

    public double getPrecoMensal() {
        return precoMensal;
    }

    public void setPrecoMensal(double precoMensal) {
        this.precoMensal = precoMensal;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }




    
}
