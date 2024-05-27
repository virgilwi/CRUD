package repository.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for database connections.
 */
public class DatabaseUtil {
private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
private static final String JDBC_USER = "sa";
private static final String JDBC_PASSWORD = "";

/**
 * Gets a connection to the database.
 *
 * @return a connection to the database
 * @throws SQLException if a database access error occurs
 */
public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
       }
}
