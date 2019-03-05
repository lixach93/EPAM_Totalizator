package by.training.lihodievski.final_project.dao.impl.opponent;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Opponent;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class OpponentDaoAbstract extends AbstractGenericDao<Opponent> {

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
        return "INSERT into totalizator.opponent (league_id, name) value (?, ?)";
    }

    protected String getSelectSqlOpponentByLeague(){
        return "SELECT opponent_id, name from totalizator.opponent where league_id = ?";
    }
    public abstract List<Opponent> getOpponentByLeague(League league) throws DaoException;
}
