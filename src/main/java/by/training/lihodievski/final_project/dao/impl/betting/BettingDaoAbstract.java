package by.training.lihodievski.final_project.dao.impl.betting;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class BettingDaoAbstract extends AbstractGenericDao<Betting> {

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
        return "INSERT into totalizator.betting (user_id,competition_rate_id, winner_id, opponnet_firts_score, opponnet_second_score, money)" +
                " value (?, ?, ?, ?, ?, ?)";
    }

    protected String getUpdateSqlUserBalance(){
        return "UPDATE totalizator.user SET money = ? WHERE user_id = ?";
    }
    protected String getUpdateSqlWinMoney(){
        return "UPDATE totalizator.betting SET win_money = ? WHERE user_id = ? and competition_rate_id = ?";
    }
    protected String getSelectSqlMoney(){
        return "SELECT sum(money) as money from totalizator.betting where competition_rate_id = ?";
    }

    public abstract void insertBetting(Betting betting) throws DaoException;

    public abstract Double getMoneyForCompRate(CompetitionRate competitionRate) throws DaoException;

    public String getSelectSqlByCompRate(){
        return  "SELECT betting.user_id,u.money,winner_id, opponnet_firts_score, opponnet_second_score  from totalizator.betting join user u\n" +
                "on betting.user_id = u.user_id  \n" +
                "where competition_rate_id = ? ";
    }

    public String getUpdateSqlForCompRate(){
        return "UPDATE totalizator.competition_rate SET payment = ? WHERE competition_rate_id = ?";
    }
    public abstract List<Betting> getAllBettingByCompRate(CompetitionRate competitionRate) throws DaoException;

    public abstract void setWinner(List<Betting> winner, double winMoney, CompetitionRate competitionRate) throws DaoException;
}
