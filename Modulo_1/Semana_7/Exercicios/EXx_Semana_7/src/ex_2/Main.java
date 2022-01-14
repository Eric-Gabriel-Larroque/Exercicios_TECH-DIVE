package ex_2;

public class Main {


    public static void main(String[] args) {
        Fornecedor fornecedor = new Fornecedor("Foo Bar","Rua 123","48 1111-1111",1200,1000);
        System.out.printf("O nome do fornecedor é %s, residente no logradouro %s, com telefone %s.\n" +
                "Seu valor de crétido é %.2f e sua dívida é de %.2f.\n\nA diferença entre os valores é de %.2f",
                fornecedor.getNome(),fornecedor.getEndereco(),fornecedor.getTelefone(),fornecedor.getValorCredito(),
                fornecedor.getValorDivida(),fornecedor.obterSaldo());


    }
}
