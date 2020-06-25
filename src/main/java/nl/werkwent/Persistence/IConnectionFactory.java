package nl.werkwent.Persistence;

import java.sql.Connection;

public interface IConnectionFactory {
    Connection getConnection();
}
