package by.training.lihodievski.final_project.dao.impl.league;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class LeagueDaoAbstract extends AbstractGenericDao<League> {

    public abstract List<League> getLeaguesByCategory(Category category) throws DaoException;
    public abstract League getLeagueById(long leagueId) throws DaoException;

    @Override
    protected String getDeleteSQL() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getUpdateSql() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getSelectSql() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getInsertSql() {
        return "INSERT into totalizator.league (category_id, name) VALUE (?, ?)";
    }

    String getLeagueByCategoryQuery() {
        return "SELECT league_id,name FROM totalizator.league WHERE category_id = ?";
    }
    String getLeagueByIdQuery() {
        return "SELECT league_id,name FROM totalizator.league WHERE league_id = ?";
    }



}
