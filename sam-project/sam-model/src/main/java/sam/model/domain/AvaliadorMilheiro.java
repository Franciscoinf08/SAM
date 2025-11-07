package sam.model.domain;

public class AvaliadorMilheiro{

    int milheiroSeguranca = 


    public boolean isVantajosaCompra(double milheiroSeguranca){
        return milheiroSeguranca < calculaValorMilheiro(0, milheiroSeguranca, milheiroSeguranca);
    }

    private double calculaValorMilheiro(int quantidadeMilhasMes, double bonusMilhas, double mesesProgFidelidade){
        return (quantidadeMilhasMes*mesesProgFidelidade + bonusMilhas)/mesesProgFidelidade;

    }
    
}
