package market2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Input {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOG = LogManager.getLogger(Input.class);

    public static int chooseOption(String message, Object... possibleOptions) {
        boolean repeat = true;
        List<Integer> options = new ArrayList<>();
        String optionsList = "";
        int answer = 0;

        for(int i = 0; i < possibleOptions.length;i++) {
            options.add(i+1);
            optionsList+= "\n"+(i+1)+" - "+possibleOptions[i];
        }
        while(repeat) {
            answer = validateNumber(message+optionsList+"\n--> ");
            if(!options.contains(answer)) {
                System.err.println("Escolha um número dentre as opções disponíveis.");
            }else {
                repeat = false;
            }
        }
        --answer;
        return  answer;
    }

    public String validateString(String message){
        boolean repeat = true;
        String input = null;
        while(repeat) {
            System.out.print(message);
            input = SCANNER.nextLine();
            if(input==null||input.isBlank()||input.isEmpty()) {
                LOG.info("Entrada de dados inválida. Tente novamente");
            }else {
                repeat = false;
            }
        }
        return input;
    }

    public static double validateDouble(String message){
        boolean repeat = true;
        double answer = 0;
        do{
            try{
                System.out.print(message);
                answer =
                        Double.parseDouble(SCANNER.nextLine());
                repeat = false;
            }catch (NumberFormatException numberFormatException) {
                LOG.info("Somente números são permitidos");
            }
        }while(repeat);
        return answer;
    }

    public static int validateNumber(String message){
        boolean repeat = true;
        int answer = 0;
        do{
            try{
                System.out.print(message);
                answer =
                        Integer.parseInt(SCANNER.nextLine());
                repeat = false;
            }catch (NoSuchElementException nullPointerException ) {
                LOG.info("Entrada de dados vazia");
            }catch (NumberFormatException numberFormatException) {
                LOG.info("Somente números são permitidos");
            }
        }while(repeat);
        return answer;
    }

    public int chooseTable() {
       return chooseOption("Com qual tabela você gostaria de trabalhar?",
              new String[]{"Produto", "Categoria", "Cliente"});
    }
}
