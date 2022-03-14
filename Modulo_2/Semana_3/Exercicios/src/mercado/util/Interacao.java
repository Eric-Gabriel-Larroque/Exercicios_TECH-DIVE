package mercado.util;
import mercado.models.dao.ClassificacaoDAO;
import mercado.models.dao.MarcaDAO;
import mercado.models.dao.ProdutoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static int escolherOpcao(String mensagem, Object... opcoesPossiveis) {
        boolean repetir = true;
        List<Integer> opcoes = new ArrayList<>();
        String listarOpcoes = "";
        int resposta = 0;

        for(int i = 0; i < opcoesPossiveis.length;i++) {
            opcoes.add(i+1);
            listarOpcoes+= "\n"+(i+1)+" - "+opcoesPossiveis[i];
        }
        while(repetir) {
            resposta = validaNumeros(mensagem+listarOpcoes+"\n--> ");
            if(!opcoes.contains(resposta)) {
                System.err.println("Escolha um número dentre as opções disponíveis.");
            }else {
                repetir = false;
            }
        }
        --resposta;
        return  resposta;
    }

    public static Object selecionaTabela(Connection conn) throws SQLException {
        String[] nomeEntidades = {"Produto","Classificacao","Marca","Sair"};
        Object entidade = null;
        int entidadeEscolhida = escolherOpcao("Com qual das entidades vai querer trabalhar?",nomeEntidades);

         switch (entidadeEscolhida) {
             case 0:
                 entidade = new ProdutoDAO(conn);
                 break;
             case 1:
                 entidade = new ClassificacaoDAO(conn);
                 break;
             case 2:
                 entidade = new MarcaDAO(conn);
                 break;
             case 3:
                 System.out.println("Volte sempre! :)");
                 System.exit(0);
                 break;
        }
        return entidade;
    }

    public static int operacoesDAO(Object entidade) {
        List<String> listaOperacoes = new ArrayList<>();
        String[] operacoesDAO = {"Listar tabela inteira","Listar pelo Nome","Listar pelo ID","Criar registros","Deletar registros","Voltar"};
        Collections.addAll(listaOperacoes,operacoesDAO);
        if(entidade.getClass().getSimpleName().equals("ProdutoDAO")) {
            listaOperacoes.add(1,"Listar com JOIN");
        }
        int operacao = -1;
        while(operacao!=5) {
            operacao = escolherOpcao("O que deseja fazer?", listaOperacoes.toArray());
        }
        return operacao;
    }
}
