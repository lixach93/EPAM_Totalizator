package by.training.lihodievski.final_project.connection;

import java.sql.SQLException;

public class AutoRollback implements AutoCloseable {

    private ProxyConnection conn;
    private boolean committed;

    public AutoRollback(ProxyConnection conn) throws SQLException {
        this.conn = conn;
    }

    public void commit() throws SQLException {
        conn.commit();
        committed = true;
    }

    @Override
    public void close() throws SQLException {
        if(!committed) {
            conn.rollback();
        }
    }

}

