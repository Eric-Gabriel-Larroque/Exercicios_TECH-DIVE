package mercado.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClassificacaoDAO {

    private Connection conn;

    public ClassificacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public void criar() {
        String sql = "INSERT INTO classificacao VALUES(DEFAULT, ?)";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            System.out.println("Criando registro na Base de Dados...");
            preparedStatement.setString(1,"Alimento");
            preparedStatement.execute();

            System.out.println("Inserção de registros realizada com sucesso!");

        }catch (SQLException e) {
            System.err.println("Não foi possível inserir um registro na tabela classificação. Erro: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void listar() {
        String sql = "SELECT * FROM classificacao";
    }
}
