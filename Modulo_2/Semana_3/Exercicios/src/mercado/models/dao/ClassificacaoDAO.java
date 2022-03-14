package mercado.models.dao;

import mercado.models.entitys.Classificacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static mercado.util.Validacao.validaNumeros;
import static mercado.util.Validacao.validaString;

public class ClassificacaoDAO {

    private Connection conn;

    public ClassificacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public void criar(Classificacao classificacao) {
        String sql = "INSERT INTO classificacao VALUES(DEFAULT, ?)";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1,classificacao.getNomeClassificacao());
            preparedStatement.execute();

            System.out.println("Inserção de registros realizada com sucesso!");

        }catch (SQLException e) {
            System.err.println("Não foi possível inserir um registro na tabela classificação. Erro: "+e.getMessage());
        }
    }

    public void listar() {
        String sql = "SELECT * FROM classificacao";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                System.out.println("ID={ "+id+" }, Nome={ "+nome+" }");
            }
        }catch (SQLException e) {
            System.err.println("Não foi possível listar as classificações. Erro: "+e.getMessage());
        }
    }

    public void listaPeloId(int id) {
        String sql = "SELECT * FROM classificacao WHERE id= ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            String nome = resultSet.getString("nome");
            System.out.println("ID={ "+id+" }, Nome={ "+nome+" }");

        }catch (SQLException e){
            System.err.println("Não foi possível listar a classificacao pelo id. Erro: "+e.getMessage());
        }
    }

    public void listaPeloNome(String nome) {
        String sql = "SELECT * from classificacao WHERE nome = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1,nome);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            System.out.println("id={ "+resultSet.getInt("id")+ " }, Nome={ "+
                    resultSet.getString("nome")+" }");
        }catch (SQLException e) {
            System.err.println("Nome não encontrado na tabela.");
        }
    }

    public void atualizar(int resgitroEscolhido) {
        String nome = "";
        int id = 1;
        String sql = "UPDATE classificacao SET nome = ? WHERE id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1,nome);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
            System.out.println("Atualização realizada com sucesso!");
        }catch (SQLException e) {
            System.err.println("Não foi possível atualizar a tabela. Erro: "+e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE from classificacao WHERE id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            System.out.println("Deleção da classificação realizada com sucesso!");
        }catch (SQLException e) {
            System.err.println("Não foi possível realizar a deleção da classificação. Erro: "+e.getMessage());
        }
    }

    public void operacoes(int operacao) {
        switch (operacao){
            case 0 -> listar();
            case 1 -> listaPeloNome(validaString("Insira um nome\n--> "));
            case 2 -> listaPeloId(validaNumeros("Insira um ID contido na tabela\n--> "));
            case 3 -> criar(new Classificacao());
            case 4 -> deletar(validaNumeros("Insira uma ID do registro que você quer deletar\n--> "));
        }
    }
}
