package nl.werkwent.persistence;

import java.sql.Connection;

public interface IConnectionFactory {
    Connection getConnection();
}
