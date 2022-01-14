package ex_4;

import ex_3.Empregado;

public class Administrador extends Empregado {

    //Ajuda de custos determinado como 20% do salário base do empregador
    private final double ajudaDeCustos = (this.getSalarioBase()/100)*20;

    public Administrador(String nome, String endereco, String telefone, double salarioBase) {
        super(nome, endereco, telefone, salarioBase);
    }

    @Override
    public void calcularSalário() {
        final double impostoSobreSalario = (this.getSalarioBase()/100)*this.getImposto();
        final double salarioReal = (this.getSalarioBase() - impostoSobreSalario)+ajudaDeCustos;
        System.out.printf("\nCom o salário base de R$: %.2f, o INSS sobre o salário é de %.1f por cento.\nCom uma ajuda de " +
                        "custos de R$: %.2f, o salário real ficará no valor de R$: %.2f.",
                this.getSalarioBase(),this.getImposto(),this.ajudaDeCustos, salarioReal);
    }
}
