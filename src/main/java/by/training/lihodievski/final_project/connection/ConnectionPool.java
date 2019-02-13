package by.training.lihodievski.final_project.connection;


import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger (ConnectionPool.class);
    private BlockingQueue<ProxyConnection> unUseConnections;
    private BlockingQueue<ProxyConnection> useConnections;
    private final static ConnectionPool INSTANCE = new ConnectionPool ();

    private ConnectionPool()  {
    }

    public static ConnectionPool getInstance(){
        return INSTANCE;
    }

    public void init() throws ConnectionPoolException {
        final int POOL_SIZE = Integer.parseInt (ResourceBundle.getBundle ("database").getString ("db.poolSize"));
        unUseConnections = new ArrayBlockingQueue<> (POOL_SIZE,true);
        useConnections = new ArrayBlockingQueue<> (POOL_SIZE,true);
        for(int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection (ConnectorDB.getConnection ());
                unUseConnections.offer (connection);
            } catch (SQLException e) {
                throw new ConnectionPoolException ("Exception create poolConnection ", e);
            } catch (ClassNotFoundException e) {
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
                throw new ConnectionPoolException ("Exception close using connections", e);
            }
        }
        for(ProxyConnection proxyConnection : unUseConnections){
            try {
                proxyConnection.closeConnection ();
            } catch (SQLException e) {
                throw new ConnectionPoolException ("Exception close unUsing connections", e);
            }
        }
    }


}
