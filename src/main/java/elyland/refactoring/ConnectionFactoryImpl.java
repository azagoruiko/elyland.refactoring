package elyland.refactoring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactoryImpl implements ConnectionFactory {
    Connection currentConnection;
    
    public ConnectionFactoryImpl(String connectionString, String dbuser, String dbpass) {
        try {
            currentConnection = DriverManager.getConnection(connectionString, dbuser, dbpass);
        } catch (SQLException ex) {
            throw new RuntimeException("Can't connect to the database", ex);
        }
    }

    public Connection getConnection() {
        return currentConnection;
    }
    
}
