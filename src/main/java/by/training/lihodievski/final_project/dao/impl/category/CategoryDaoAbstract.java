package by.training.lihodievski.final_project.dao.impl.category;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;


public abstract class CategoryDaoAbstract extends AbstractGenericDao<Category> {

    public abstract Category getCategoryById(long id) throws DaoException;
    public abstract Category getCategoryByCompetition(Competition competition) throws DaoException;

    @Override
    protected String getDeleteSQL() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getUpdateSql() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getSelectSql() {
        return "SELECT name FROM totalizator.category";
    }

    @Override
    protected String getInsertSql() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    String getCategoryByIdQuery(){
        return "SELECT category.name FROM totalizator.category WHERE category_id = ? ";
    }
    String getCategoryByCompetitionQuery(){
        return " SELECT category.name FROM competition c" +
                " JOIN team t1 on " +
                " c.team_first = t1.team_id" +
                " JOIN team t2 on" +
                " c.team_second = t2.team_id" +
                " JOIN league l1 on " +
                " l1.league_id = t1.league_id" +
                " JOIN league l2 on" +
                " l2.league_id = t2.league_id" +
                " JOIN category  on" +
                " category.category_id = l1.category_id" +
                " JOIN category category2 on " +
                " category2.category_id = l2.category_id" +
                " WHERE  category.name = category2.name and c.competition_id = ?";
    }

}
