package by.training.lihodievski.final_project.dao;

import by.training.lihodievski.final_project.bean.Entity;
import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenericDao<T extends Entity> implements GenericDao<T>  {
    
    private final ConnectionPool connectionPool;
    protected abstract String getDeleteSQL();
    protected abstract String getUpdateSQL();
    protected abstract String getSelectSQL();
    protected abstract String getInsertSQL();

    //?
    AbstractGenericDao() {
        this.connectionPool = ConnectionPool.getInstance ();
    }

    @Override
    public List<T> getAll() throws DaoException {
        String SELECT_SQL = getSelectSQL ();
        List<T> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (SELECT_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException e) {
            e.printStackTrace ();
        } catch (ConnectionPoolException e) {
            e.printStackTrace ();
        }
        return list;
    }


    @Override
    public void delete(T object) throws DaoException {
        String DELETE_SQL = getDeleteSQL();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (DELETE_SQL)){
            preparedStatement.setLong (1,object.getId ());
            preparedStatement.execute ();
        }catch (SQLException e) {
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
            throw  new DaoException (e);
        }
    }


    @Override
    public void update(T object) throws DaoException {
        String UPDATE_SQL = getUpdateSQL ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (UPDATE_SQL)){
            preparedStatementUpdate (preparedStatement, object);
            preparedStatement.execute ();
        }catch (SQLException e) {
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
            throw  new DaoException (e);
        }
    }

    @Override
    public void insert(T object) throws DaoException {
        String INSERT_SQL = getInsertSQL ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (INSERT_SQL)){
            preparedStatementInsert(preparedStatement,object);
            preparedStatement.execute ();
        }catch (SQLException e) {
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
            throw  new DaoException (e);
        }
    }

    protected abstract void preparedStatementInsert(PreparedStatement preparedStatement, T object) throws DaoException;
    protected abstract void preparedStatementUpdate(PreparedStatement preparedStatement, T object) throws DaoException;
    protected abstract List<T> parseResultSet(ResultSet resultSet, List<T> list) throws DaoException;
}
