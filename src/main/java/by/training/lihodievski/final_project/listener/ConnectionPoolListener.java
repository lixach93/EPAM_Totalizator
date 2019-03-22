package by.training.lihodievski.final_project.listener;

import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger (ConnectionPoolListener.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance ();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.init ();
        } catch (ConnectionPoolException e) {
            LOGGER.fatal ("Exception init connectionPool",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.destroy ();
        } catch (ConnectionPoolException e) {
            LOGGER.fatal ("Exception destroy connectionPool",e);
        }
    }
}
