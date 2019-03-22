package by.training.lihodievski.final_project.dao.impl.opponent;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Team;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class TeamDaoAbstract extends AbstractGenericDao<Team> {

    public abstract List<Team> getTeamByLeague(League league) throws DaoException;

    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSql() {
        return null;
    }

    @Override
    protected String getSelectSql() {
        return "SELECT ";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT into totalizator.team (league_id, name) VALUE (?, ?)";
    }

    String getTeamByLeagueQuery(){
        return "SELECT team_id, name FROM totalizator.team WHERE league_id = ?";
    }

}
