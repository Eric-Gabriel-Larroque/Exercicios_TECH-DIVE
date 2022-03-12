package mercado.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import static mercado.util.Interacao.*;

public class JdbcConnection {

     private final String URL = "jdbc:postgresql://localhost/mercado?useTimezone=true?serverTimezone=UTC";
     private DataSource dataSource;

    public JdbcConnection() {
        String[] usuarioSenha = login();
        ComboPooledDataSource pool = new ComboPooledDataSource();
        pool.setJdbcUrl(URL);
        pool.setUser(usuarioSenha[0]);
        pool.setPassword(usuarioSenha[1]);

        this.dataSource = pool;
    }

    public Connection connect() throws SQLException {
        System.out.println("Preparando a conexão ao BD...");
        return this.dataSource.getConnection();
    }
}
