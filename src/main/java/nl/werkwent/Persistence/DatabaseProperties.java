package nl.werkwent.Persistence;

import java.util.Properties;

public class DatabaseProperties {
    private Properties prop;

    public DatabaseProperties() {
        prop = new Properties();

        try {
            var stream = DatabaseProperties.class.getClassLoader().getResourceAsStream("database.properties");
            prop.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getdriver() {
        return prop.getProperty("driver");
    }

    public String getConnectionString() {
        return prop.getProperty("connectionstring");
    }
}
