package by.training.lihodievski.final_project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static by.training.lihodievski.final_project.util.Constants.*;

class ConnectorDB {

    static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName (DRIVER);
        ResourceBundle resource = ResourceBundle.getBundle(DATABASE_PROPERTIES);
        String url = resource.getString(DB_URL);
        String user = resource.getString(DB_USER);
        String password = resource.getString(DB_PASSWORD);
        return DriverManager.getConnection(url,user,password);

    }

}
