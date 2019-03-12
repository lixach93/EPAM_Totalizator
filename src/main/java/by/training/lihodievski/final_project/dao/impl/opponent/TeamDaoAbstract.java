package by.training.lihodievski.final_project.dao.impl.opponent;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Team;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class TeamDaoAbstract extends AbstractGenericDao<Team> {

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
        return "SELECT ";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT into totalizator.team (league_id, name) value (?, ?)";
    }

    protected String getSelectSqlOpponentByLeague(){
        return "SELECT team_id, name from totalizator.team where league_id = ?";
    }
    public abstract List<Team> getOpponentByLeague(League league) throws DaoException;
}
