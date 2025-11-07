package sam.model.computer;

public interface ComputerMilhas {

    boolean isVantajosaCompra(double milheiroSeguranca, double precoMilheiro);
    /**
     * @param milheiroSeguranca provém de um valor pré-estabelecido do registro de programas de fidelidade
     */

    double calculaValorMilheiro(double quantidadeMilhasMes, double bonusMilhas, double mesesProgFidelidade);

    
    
    
    
}
