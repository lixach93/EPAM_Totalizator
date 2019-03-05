package by.training.lihodievski.final_project.dao.impl.category;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import javax.naming.OperationNotSupportedException;


public abstract class CategoryDaoAbstract extends AbstractGenericDao<Category> {

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM totalizator.category  where id = ?";
    }

    @Override
    protected String getUpdateSQL() {
       return  "UPDATE totalizator.category set name = ? where id = ?";
    }

    @Override
    protected String getSelectSql() {

        return "SELECT name FROM totalizator.category";
    }

    @Override
    protected String getInsertSql() {

        return "чуй";
    }

    protected String getSelectCategoryById(){
        return "select name from totalizator.category where category_id = ? ";
    }

    public abstract Category getCategoryById(long id) throws DaoException;
}
