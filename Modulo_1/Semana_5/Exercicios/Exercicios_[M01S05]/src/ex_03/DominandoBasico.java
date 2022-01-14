package ex_03;

import static java.util.Currency.getInstance;
import static java.util.Locale.GERMANY;
import static java.util.Calendar.DAY_OF_MONTH;


public abstract class DominandoBasico {

    public static void main(String[] args) {


        System.out.printf("Moeda corrente da Alemanha: %s\n",getInstance(GERMANY));
        System.out.printf("Dia do mês: %d\n",DAY_OF_MONTH-2);
    }

}
