package mercado.util;
import static mercado.util.Validacao.*;
public class Interacao {

    public static String[] login() {
        boolean repetir = true;
        String[] dadosPessoais = new String[2];
        while(repetir) {
            System.out.println("Seja bem-vindo(a)!\n");
            dadosPessoais[0] = validaString("Usuário\n--> ");
            dadosPessoais[1] = validaString("Senha\n--> ");
            if(!dadosPessoais[0].equals("postgres")||!dadosPessoais[1].equals("admin")) {
                System.err.println("Usuário ou senha inválidos. Tente novamente");
            }else {
                repetir = false;
            }
        }

        return dadosPessoais;
    }
}
