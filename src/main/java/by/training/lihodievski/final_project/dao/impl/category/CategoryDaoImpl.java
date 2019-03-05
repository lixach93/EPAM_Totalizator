package by.training.lihodievski.final_project.dao.impl.category;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends CategoryDaoAbstract {

    private final static CategoryDaoImpl INSTANCE = new CategoryDaoImpl ();
    private CategoryDaoImpl() {
    }

    public static CategoryDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Category> parseResultSet(ResultSet resultSet, List<Category> list) throws DaoException {
        try{
            while (resultSet.next ()){
                Category category = Category.valueOf (resultSet.getString ("name").toUpperCase ());
                list.add (category);
            }
            }catch (SQLException e){
                throw new DaoException (e);
            }
            return list;
    }



    @Override
     protected void preparedStatementInsert(PreparedStatement preparedStatement, Category object) throws DaoException {
        try {
            preparedStatement.setString (1,object.getNameCategory ());
        } catch (SQLException e) {
            throw new DaoException ();
        }
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Category object) throws DaoException {
        try {
            preparedStatement.setString (1,object.getNameCategory ());
            preparedStatement.setLong (2,object.getId ());
        } catch (SQLException e) {
            throw new DaoException ();
        }
    }

    @Override
    public Category getCategoryById(long id) throws DaoException {
        String sqlQuery = getSelectCategoryById ();
        List<Category> categories = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            PreparedStatement statement = connection.prepareStatement (sqlQuery)){
            statement.setLong (1,id);
            ResultSet resultSet = statement.executeQuery ();
            categories = parseResultSet (resultSet, categories);
        }catch (SQLException e){
            throw new DaoException (e);
        }catch (ConnectionPoolException e){
            throw new DaoException (e);
        }
        return categories.iterator ().next ();
    }
}
