package nl.werkwent.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements IConnectionFactory {
    DatabaseProperties databaseProperties = new DatabaseProperties();

    public ConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseProperties.getConnectionString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
