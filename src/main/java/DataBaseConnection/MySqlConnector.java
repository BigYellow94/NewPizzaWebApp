package DataBaseConnection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector {
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pizza_web_app");
        dataSource.setUsername("root");
        dataSource.setPassword("LFEcfhs094!");
        dataSource.setMaxTotal(10); // Установите максимальное количество соединений
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
