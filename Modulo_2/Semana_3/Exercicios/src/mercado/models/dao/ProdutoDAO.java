package mercado.models.dao;

import static mercado.util.Validacao.*;
import mercado.models.entitys.Classificacao;
import mercado.models.entitys.Marca;
import mercado.models.entitys.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public void criar(Produto produto) {
        String sql = "INSERT INTO produto VALUES(DEFAULT, 1, 1, ?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1,produto.getNomeProduto());
            preparedStatement.setDouble(2,produto.getPreco());
            preparedStatement.execute();

            System.out.println("Criação de registro na tabela realizada com sucesso!");

        }catch (SQLException e) {
            System.err.println("Não foi possível inserir registros na tabela. Erro: "+e.getMessage());
        }
    }

    public void listarCategoriaMarca() {
        String sql = "SELECT p.id AS ID, p.nome AS nome_produto, p.preco AS preco, c.nome AS classificacao, m.nome AS marca FROM produto p INNER JOIN classificacao c ON p.classificacao_id=c.id INNER JOIN marca m ON p.marca_id=m.id";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
           while(resultSet.next()) {
               Produto produto = new Produto(
                    resultSet.getString("nome_produto"),
                    resultSet.getDouble("preco"),
                    resultSet.getInt("ID"),
                    new Classificacao(resultSet.getString("marca"),resultSet.getInt("ID")),
                    new Marca(resultSet.getString("classificacao"),resultSet.getInt("ID"))
               );

               System.out.println("Produto = { "+produto.getNomeProduto()+" }, Preço = { "+produto.getPreco()+" }, " +
                       "Classificação = { "+produto.getClassificacao().getNomeClassificacao()+" }, " +
                       "Marca = { "+produto.getMarca().getNomeMarca()+" }");
           }
        }catch (SQLException e) {
            System.err.println("Não foi possível listar os produtos. Erro: "+e.getMessage());
        }
    }

    public void listar() {
        String sql = "SELECT * FROM produto";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                int id = resultSet.getInt("id");
                int classificacao_id = resultSet.getInt("classificacao_id");
                int marca_id = resultSet.getInt("marca_id");

                System.out.println("Nome={ "+nome+" }, Preço={ "+preco+"}, ID={ "+id+" }, " +
                        "Classificacao_ID={ "+classificacao_id+" }, Marca_ID={ "+marca_id+" }");
            }
        }catch (SQLException e) {
            System.err.println("Não foi possível listar os produtos. Erro: "+e.getMessage());
        }
    }

    public void listaPeloId(int id) {
        String sql = "SELECT * FROM PRODUTO WHERE id= ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();

                System.out.println("Produto_ID={ "+resultSet.getInt("id")+" }, Marca_ID={ "+resultSet.getInt("marca_id")+" }, " +
                        "Classificacao_ID={ "+resultSet.getInt("classificacao_id")+" }, Nome={ "+resultSet.getString("nome")+" }, " +
                        "Preço={ "+resultSet.getDouble("preco")+" }");

        }catch (SQLException e){
            System.err.println("Não foi possível listar o produto pelo id. Erro: "+e.getMessage());
        }
    }

    public void listaPeloNome(String nome) {
        String sql = "SELECT * from produto WHERE nome = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1,nome);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
                System.out.println("Produto_ID={ "+resultSet.getInt("id")+" }, Marca_ID={ "+resultSet.getInt("marca_id")+" }, " +
                        "Classificacao_ID={ "+resultSet.getInt("classificacao_id")+" }, Nome={ "+resultSet.getString("nome")+" }, " +
                        "Preço={ "+resultSet.getDouble("preco")+" }");

        }catch (SQLException e) {
            System.err.println("Nome não encontrado na tabela.");
        }
    }

    public void atualizar(int resgitroEscolhido) {
        String[] dadosAtualizaveis = {"nome","preco"};

        String sql = "UPDATE produto SET "+dadosAtualizaveis[resgitroEscolhido]+" = ? WHERE id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(2,"PS4");
            preparedStatement.setString(3,"id");
            preparedStatement.setInt(4,1);
            preparedStatement.execute();
            System.out.println("Atualização realizada com sucesso!");
        }catch (SQLException e) {
            System.err.println("Não foi possível atualizar a tabela. Erro: "+e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE from produto WHERE id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            System.out.println("Deleção do produto realizada com sucesso!");
        }catch (SQLException e) {
            System.err.println("Não foi possível realizar a deleção do produto. Erro: "+e.getMessage());
        }
    }

    public void operacoes(int operacao) {
        switch (operacao){
            case 0 -> listar();
            case 1 -> listarCategoriaMarca();
            case 2 -> listaPeloNome(validaString("Insira um nome\n--> "));
            case 3 -> listaPeloId(validaNumeros("Insira um ID contido na tabela\n--> "));
            case 4 -> criar(new Produto());
            case 5 -> deletar(validaNumeros("Insira o ID do produto que você quer deletar\n--> "));
        }
    }

}
