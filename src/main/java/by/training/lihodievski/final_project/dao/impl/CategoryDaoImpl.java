package by.training.lihodievski.final_project.dao.impl;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.dao.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.exception.DaoException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl extends CategoryDaoAbstract {

    private static final CategoryDaoImpl INSTANCE = new CategoryDaoImpl ();
    private CategoryDaoImpl() {
    }

    public static CategoryDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Category> parseResultSet(ResultSet resultSet, List<Category> list) throws DaoException {
        try{
            while (resultSet.next ()){
                Category category = new Category ();
                category.setId (resultSet.getLong ("category_id"));
                category.setNameCategory (resultSet.getString ("name"));
                list.add (category);
            }
            }catch (SQLException e){
                throw new DaoException ();
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
}
