import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pizza_web_app";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "LFEcfhs094!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(JDBC_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }
}