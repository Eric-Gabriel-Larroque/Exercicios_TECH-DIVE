package ex_5;

import ex_3.Empregado;

public class Operario extends Empregado {

    //Valor dos artigos produzidos pode variar de 500 a 2000 reais
   private final double valorProduzido = Math.ceil(Math.random()*(2000-500)+500);

    //Comissão definida como 30% do valor produzido pelo operário
    private final double comissao = (valorProduzido/100)*30;

    public Operario(String nome, String endereco, String telefone, double salarioBase) {
        super(nome, endereco, telefone, salarioBase);
    }

    @Override
    public void calcularSalário() {
        final double impostoSobreSalario = (this.getSalarioBase()/100)*this.getImposto();
        final double salarioReal = this.getSalarioBase() - impostoSobreSalario+comissao;
        System.out.printf("\nCom o salário base de R$: %.2f, o INSS sobre o salário é de %.1f por cento " +
                        "(ficando em %.2f reais). " +
                "\nCom o valor adicional da comissão de R$: %.2f, o salário fica em %.2f reais.",
                this.getSalarioBase(),this.getImposto(),impostoSobreSalario,comissao,salarioReal);
    }
}
