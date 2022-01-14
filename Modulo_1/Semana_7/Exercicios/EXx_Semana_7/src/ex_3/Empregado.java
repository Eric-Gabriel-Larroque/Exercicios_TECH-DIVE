package ex_3;

import ex_1.Pessoa;

public class Empregado extends Pessoa {

    private final int codigoSetor = (int) Math.ceil(Math.random()*(9999)+1000);
    private double salarioBase;
    private double imposto;

    public Empregado(String nome, String endereco, String telefone, double salarioBase) {
        super(nome, endereco, telefone);
        this.salarioBase = salarioBase;
        setImposto();
    }

    public int getCodigoSetor() {
        return codigoSetor;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    private void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getImposto() {
        return imposto;
    }

    private void setImposto() {
        /* Cálculo alíquota INSS

        * Até R$ 1.100,00 (salário-mínimo) – 7,5%
          Entre R$ 1.100,01 e R$ 2.203,48 – 9%
          Entre R$ 2.203,49 e R$ 3.305,22 – 12%
          Entre R$ 3.305,23 e 6.433,57 – 14%
        * */

        if(salarioBase<=1100.00) {
            this.imposto = 7.5;
        } else if (salarioBase<=2203.48) {
            this.imposto = 9;
        } else if (salarioBase <= 3305.22) {
            this.imposto = 12;
        } else {
            this.imposto = 14;
        }
    }

    public void calcularSalário() {
        final double impostoSobreSalario = (salarioBase/100)*imposto;
        final double salarioReal = salarioBase - impostoSobreSalario;
        System.out.printf("\nCom o salário base de R$: %.2f, o INSS sobre o salário é de %.1f por cento. Ficando assim no valor de R$: %.2f.",
                salarioBase,imposto,salarioReal);
    }
}
