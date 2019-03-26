package by.training.lihodievski.final_project.connection;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.training.lihodievski.final_project.util.Constants.DATABASE_PROPERTIES;
import static by.training.lihodievski.final_project.util.Constants.DB_POOL;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger (ConnectionPool.class);
    private BlockingQueue<ProxyConnection> unUseConnections;
    private BlockingQueue<ProxyConnection> useConnections;
    private static final ConnectionPool INSTANCE = new ConnectionPool ();

    private ConnectionPool()  {
    }

    public static ConnectionPool getInstance(){
        return INSTANCE;
    }

    public void init() throws ConnectionPoolException {
        final int POOL_SIZE = Integer.parseInt (ResourceBundle.getBundle (DATABASE_PROPERTIES).getString (DB_POOL ));
        unUseConnections = new ArrayBlockingQueue<> (POOL_SIZE,true);
        useConnections = new ArrayBlockingQueue<> (POOL_SIZE,true);
        for(int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection (ConnectorDB.getConnection ());
                unUseConnections.offer (connection);
            } catch (SQLException e) {
                LOGGER.fatal ("Exception create poolConnection ", e);
                throw new ConnectionPoolException ("Exception create poolConnection ", e);
            } catch (ClassNotFoundException e) {
                LOGGER.fatal ("Exception register driver ", e);
                throw new ConnectionPoolException ("Exception register driver ", e);
            }
        }
    }

    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection = null;
        try {
            connection = unUseConnections.take ();
            useConnections.offer (connection);
        } catch (InterruptedException e) {
            LOGGER.error ("Exception takeConnection ", e);
            throw new ConnectionPoolException("Failed to take connection from pool.", e);
        }
        return connection;
    }

    void returnConnection(ProxyConnection proxyConnection) {
        LOGGER.info("UnUseConnections/UseConnections before return Connection: " + unUseConnections.size() +  " " + useConnections.size ());
        useConnections.remove (proxyConnection);
        unUseConnections.offer (proxyConnection);
        LOGGER.info("UnUseConnections/UseConnections after return Connection: " + unUseConnections.size() +  " " + useConnections.size ());
    }

    public void destroy() throws ConnectionPoolException {

        for(ProxyConnection proxyConnection : useConnections){
            try {
                proxyConnection.closeConnection ();
            } catch (SQLException e) {
                LOGGER.error ("Exception close using connections ", e);
                throw new ConnectionPoolException ("Exception close using connections", e);
            }
        }
        for(ProxyConnection proxyConnection : unUseConnections){
            try {
                proxyConnection.closeConnection ();
            } catch (SQLException e) {
                LOGGER.error ("Exception close unUsing connections ", e);
                throw new ConnectionPoolException ("Exception close unUsing connections", e);
            }
        }
    }


}
