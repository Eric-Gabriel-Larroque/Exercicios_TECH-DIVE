package mercado.program;

import mercado.connection.JdbcConnection;
import mercado.models.dao.ProdutoDAO;
import java.sql.Connection;
import java.sql.SQLException;

import static mercado.util.Validacao.*;

public class Main {

    public static void main(String[] args) {
        String resposta;
        String[] operacoes = {"Listar tabelas","Criar registros","Atualizar registros","Deletar Registros","Sair"};
        JdbcConnection jdbcConnection = new JdbcConnection();
        try(Connection connection = jdbcConnection.connect()) {
            System.out.println("Conexão com BD realizada com sucesso!");
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);

            escolherOpcao("Bem-vindo(a)! O que deseja fazer?",operacoes);

        }catch (SQLException e) {
            System.err.println("Não foi possível se conectar ao Banco de Dados. Erro - "+e.getMessage());
        }
    }
}