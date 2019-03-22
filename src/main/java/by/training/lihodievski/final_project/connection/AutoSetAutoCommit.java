package by.training.lihodievski.final_project.connection;


import java.sql.SQLException;

public class AutoSetAutoCommit implements AutoCloseable {

    private ProxyConnection conn;
    private boolean originalAutoCommit;

    public AutoSetAutoCommit(ProxyConnection conn, boolean autoCommit) throws SQLException {
        this.conn = conn;
        originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(autoCommit);
    }

    @Override
    public void close() throws SQLException {
        conn.setAutoCommit(originalAutoCommit);
    }

}