package repository.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
private static final String JDBC_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";
private static final String JDBC_USER = "sa";
private static final String JDBC_PASSWORD = "";

public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
       }
}
