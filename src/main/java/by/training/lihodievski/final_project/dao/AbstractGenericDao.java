package by.training.lihodievski.final_project.dao;

import by.training.lihodievski.final_project.bean.Entity;
import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenericDao<T extends Entity> implements GenericDao<T>  {

    private static final Logger LOGGER = LogManager.getLogger (AbstractGenericDao.class);
    protected final ConnectionPool connectionPool;
    protected abstract String getDeleteSQL() throws DaoException;
    protected abstract String getUpdateSql() throws DaoException;
    protected abstract String getSelectSql() throws DaoException;
    protected abstract String getInsertSql() throws DaoException;
    protected abstract void preparedStatementInsert(PreparedStatement preparedStatement, T object) throws DaoException;
    protected abstract void preparedStatementUpdate(PreparedStatement preparedStatement, T object) throws DaoException;
    protected abstract List<T> parseResultSet(ResultSet resultSet, List<T> list) throws DaoException;


    protected AbstractGenericDao() {
        this.connectionPool = ConnectionPool.getInstance ();
    }

    @Override
    public List<T> getAll() throws DaoException {
        String selectSql = getSelectSql ();
        List<T> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (selectSql)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getAll in AbstractGenericDao.class ", e);
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public void delete(T object) throws DaoException {
        String deleteSql = getDeleteSQL();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (deleteSql)){
            preparedStatement.setLong (1,object.getId ());
            preparedStatement.execute ();
        }catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in delete in AbstractGenericDao.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    public void update(T object) throws DaoException {
        String UPDATE_SQL = getUpdateSql ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (UPDATE_SQL)){
            preparedStatementUpdate (preparedStatement, object);
            preparedStatement.executeUpdate ();
        }catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in update in AbstractGenericDao.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    public void insert(T object) throws DaoException {
        String insertQuery = getInsertSql ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (insertQuery)){
            preparedStatementInsert(preparedStatement,object);
            preparedStatement.execute ();
        }catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in insert in AbstractGenericDao.class ", e);
            throw new DaoException (e);
        }
    }


}
