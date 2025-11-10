package sam.model.helper;

public class CalculoHelper {

    public static double calcularMilhasTotais(double milhasMes, double meses, double bonusPercentual) {
        return (milhasMes * meses) * (1 + (bonusPercentual / 100.0));
    }

    public static double calcularValorMilheiro(double preco, double milhasTotais) {
        return (preco / milhasTotais) * 1000;
    }

    public static double calcularDiferencaPercentual(double milheiroSeguranca, double valorMilheiro) {
        return ((milheiroSeguranca - valorMilheiro) / milheiroSeguranca) * 100;
    }

    public static String classificaValorMilheiro(double diferenca) {
        if (diferenca < 0) return "Desvantajoso";
        if (diferenca < 10) return "Razoável";
        if (diferenca < 20) return "Vantajoso";
        return "Muito vantajoso";
    }
}

