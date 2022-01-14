package ex_6;

import ex_3.Empregado;

public class Vendedor extends Empregado {

    //Valor das vendas estipulada entre 10 a 30 mil
    private final double valorVendas = Math.ceil(Math.random()*(30000-10000)+10000);

    //Comissão definida como 5% do valor das vendas
    private final double comissao = (valorVendas/100)*5;

    public Vendedor(String nome, String endereco, String telefone, double salarioBase) {
        super(nome, endereco, telefone, salarioBase);
    }

    @Override
    public void calcularSalário() {
        final double impostoSobreSalario = (this.getSalarioBase()/100)*this.getImposto();
        final double salarioReal = this.getSalarioBase() - impostoSobreSalario+comissao;
        System.out.printf("\nCom o salário base de R$: %.2f, o INSS sobre o salário é de %.1f por cento " +
                        "(ficando em %.2f reais). " +
                        "\nCom o valor adicional da comissão das vendas de R$: %.2f, o salário fica em %.2f reais.",
                this.getSalarioBase(),this.getImposto(),impostoSobreSalario,comissao,salarioReal);
    }
}
