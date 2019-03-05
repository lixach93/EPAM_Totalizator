package by.training.lihodievski.final_project.dao.impl.league;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class LeagueDaoAbstract extends AbstractGenericDao<League> {

    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSQL() {
        return null;
    }

    @Override
    protected String getSelectSql() {
        return null;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT into totalizator.league (category_id, name) value (?, ?)";
    }

    protected String getSelectSqlByCategory() {
        return "SELECT league_id, name FROM totalizator.league where category_id = ?";
    }

    public abstract List<League> getLeaguesByCategory(Category category) throws DaoException;
}
