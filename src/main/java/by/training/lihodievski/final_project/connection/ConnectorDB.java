package by.training.lihodievski.final_project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

class ConnectorDB {

    static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName ("com.mysql.jdbc.Driver");
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String password = resource.getString("db.password");
        return DriverManager.getConnection(url,user,password);

    }

}
