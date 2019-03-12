package by.training.lihodievski.final_project.dao.impl.betting;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class BettingDaoAbstract extends AbstractGenericDao<Betting> {

    public abstract List<Betting> getActiveBettingForUser(User user) throws DaoException;
    public abstract List<Betting> getResultBettingForUser(User user) throws DaoException;

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
        return "INSERT into totalizator.betting (user_id,event_id, winner_id, team_first_score, team_second_score, money)" +
                " value (?, ?, ?, ?, ?, ?)";
    }

    protected String getUpdateUserBalanceQuery(){
        return "UPDATE totalizator.user SET money = ? WHERE user_id = ?";
    }
    protected String getUpdateSqlWinMoney(){
        return "UPDATE totalizator.betting SET win_money = ? WHERE user_id = ? and competition_rate_id = ?";
    }
    protected String getSelectSqlMoney(){
        return "SELECT sum(money) as money from totalizator.betting where event_id = ?";
    }

    public abstract boolean insertBetting(Betting betting) throws DaoException;

    public abstract Double getMoneyForCompRate(Event event) throws DaoException;
    String getCompetitionByIdQuery(){
        return "SELECT  event.event_id,competition.status from event " +
                " join competition on competition.competition_id = event.competition_id" +
                " where event.event_id = ?";
    }
    public String getBettingByEvent(){
        return  "SELECT betting.betting_id,event.event_id,competition.competition_id," +
                "betting.user_id,t1.name,t2.name,competition.team_first_result," +
                "competition.team_second_result,competition.winner as winner_result,rate_type.name," +
                "betting.winner_id,betting.team_first_score,betting.team_second_score," +
                "betting.money as betMoney,competition.status,betting.win_money,u.money" +
                " FROM betting join event on" +
                " event.event_id = betting.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on betting.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where betting.event_id = ? ";
    }

    public String getUpdateSqlForCompRate(){
        return "UPDATE totalizator.event SET payment = ? WHERE event_id = ?";
    }
    public abstract List<Betting> getAllBettingByCompRate(Event event) throws DaoException;

    public abstract void setWinner(List<Betting> winner, double winMoney, Event event) throws DaoException;

    protected String getActiveBettingQuery(){
        return  "SELECT betting.betting_id,event.event_id,competition.competition_id," +
                "betting.user_id,t1.name,t2.name,competition.team_first_result," +
                "competition.team_second_result,competition.winner as winner_result,rate_type.name," +
                "betting.winner_id,betting.team_first_score,betting.team_second_score," +
                "betting.money as betMoney,competition.status,betting.win_money,u.money" +
                " FROM betting join event on" +
                " event.event_id = betting.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on betting.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where competition.status = 'new' and betting.user_id = ?";
    }
    protected String getSelectSqlResultBetting(){
        return  "SELECT betting.betting_id,event.event_id,competition.competition_id," +
                "betting.user_id,t1.name,t2.name,competition.team_first_result," +
                "competition.team_second_result,competition.winner as winner_result,rate_type.name," +
                "betting.winner_id,betting.team_first_score,betting.team_second_score," +
                "betting.money as betMoney,competition.status,betting.win_money,u.money" +
                " FROM betting join event on" +
                " event.event_id = betting.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on betting.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where event.payment = 1 and betting.user_id = ?";
    }

}
