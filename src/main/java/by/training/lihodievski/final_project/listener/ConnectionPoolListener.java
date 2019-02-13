package by.training.lihodievski.final_project.listener;

import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private ConnectionPool connectionPool = ConnectionPool.getInstance ();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.init ();
        } catch (ConnectionPoolException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.destroy ();
        } catch (ConnectionPoolException e) {
            e.printStackTrace ();
        }
    }
}
