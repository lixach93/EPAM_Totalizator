package by.training.lihodievski.final_project.dao;

import by.training.lihodievski.final_project.bean.Category;



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
    protected String getSelectSQL() {

        return "SELECT category_id,name FROM totalizator.category";
    }

    @Override
    protected String getInsertSQL() {

        return "INSERT INTO totalizator.category ('name') VALUES (?)";
    }
}
