package mercado.models.dao;

import mercado.connection.JdbcConnection;
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

    public void criar() {
        String sql = "INSERT INTO produto VALUES(DEFAULT, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
        }catch (SQLException e) {
            System.err.println("Não fois possível inserir registros na tabela. Erro: "+e.getMessage());
            e.printStackTrace();
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
                    resultSet.getInt("ID")
               );
               produto.setMarca(new Marca(resultSet.getString("marca"),resultSet.getInt("ID")));
               produto.setClassificacao(new Classificacao(resultSet.getString("classificacao"),resultSet.getInt("ID")));

               System.out.println("Produto = { "+produto.getNomeProduto()+" }, Preço = { "+produto.getPreco()+" }, " +
                       "Classificação = { "+produto.getClassificacao().getNomeClassificacao()+" }, " +
                       "Marca = { "+produto.getMarca().getNomeMarca()+" }");
           }
        }catch (SQLException e) {
            System.err.println("Não foi possível listar os produtos. Erro: "+e.getMessage());
            e.printStackTrace();
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
                        "Classificacao_ID={ "+classificacao_id+" }, Marca_ID={ "+marca_id+"} ");
            }
        }catch (SQLException e) {
            System.err.println("Não foi possível listar os produtos. Erro: "+e.getMessage());
            e.printStackTrace();
        }
    }

}
