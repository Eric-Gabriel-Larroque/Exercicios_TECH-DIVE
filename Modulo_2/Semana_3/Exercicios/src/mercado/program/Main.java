package mercado.program;

import mercado.connection.JdbcConnection;
import mercado.models.dao.ClassificacaoDAO;
import mercado.models.dao.MarcaDAO;
import mercado.models.dao.ProdutoDAO;
import mercado.models.entitys.Classificacao;
import mercado.models.entitys.Marca;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mercado.util.Interacao.*;

public class Main {

    public static void main(String[] args) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        try(Connection connection = jdbcConnection.connect()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO(connection);
            MarcaDAO marcaDAO = new MarcaDAO(connection);
            System.out.println("Conexão com BD realizada com sucesso!");
            int operacao = -1;
            Object entidade = new Object();
            while(entidade!=null) {
                entidade = selecionaTabela(connection);
                operacao = operacoesDAO(entidade);
                switch (entidade.getClass().getSimpleName()){
                    case "ProdutoDAO" -> produtoDAO.operacoes(operacao);
                    case "ClassificacaoDAO" -> classificacaoDAO.operacoes(operacao);
                    case "MarcaDAO" -> marcaDAO.operacoes(operacao);
                }
            }

        }catch (SQLException e) {
            System.err.println("Não foi possível se conectar ao Banco de Dados. Erro - "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro: "+e.getMessage());
            e.printStackTrace();
        }

    }
}