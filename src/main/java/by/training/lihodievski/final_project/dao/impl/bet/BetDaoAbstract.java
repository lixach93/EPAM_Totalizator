package by.training.lihodievski.final_project.dao.impl.bet;

import by.training.lihodievski.final_project.bean.Bet;
import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public abstract class BetDaoAbstract extends AbstractGenericDao<Bet> {

    public abstract int getCountActiveBetForUser(User user) throws DaoException;
    public abstract List<Bet> getActiveBettingLimitForUser(User user, int page) throws DaoException;
    public abstract List<Bet> getResultBettingForUser(User user) throws DaoException;
    public abstract boolean insertBet(Bet bet) throws DaoException;
    public abstract Double getBetMoneyByEvent(Event event) throws DaoException;
    public abstract List<Bet> getBetsByEvent(Event event) throws DaoException;
    public abstract boolean setWinner(List<Bet> winner, double winMoney, Event event) throws DaoException;


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
        return "INSERT into totalizator.bet (user_id,event_id, winner_id, team_first_score, team_second_score, money)" +
                " VALUE (?, ?, ?, ?, ?, ?)";
    }

    String getUpdateUserBalanceQuery(){
        return "UPDATE totalizator.user SET money = ? WHERE user_id = ?";
    }
    String getPaymentQuery(){
        return "UPDATE totalizator.bet b inner join user u" +
                " on b.user_id = u.user_id" +
                " join event e on" +
                " b.event_id = e.event_id" +
                " SET  b.win_money = ?,u.money = u.money + ?,e.payment = ?,e.win_percent = ?" +
                " WHERE b.user_id = ? and b.event_id = ?";
    }
    String getBetMoneyByEventQuery(){
        return "SELECT sum(money) as money from totalizator.bet where event_id = ?";
    }

    String getCompetitionByIdQuery(){
        return "SELECT  event.event_id,competition.status FROM event " +
                " JOIN competition on competition.competition_id = event.competition_id" +
                " WHERE event.event_id = ?";
    }
    String getBettingByEvent(){
        return  "SELECT bet.bet_id,event.event_id,competition.competition_id," +
                "bet.user_id,t1.name,t2.name,competition.team_first_result," +
                "competition.team_second_result,competition.winner as winner_result,rate_type.name," +
                "bet.winner_id,bet.team_first_score,bet.team_second_score," +
                "bet.money as betMoney,competition.status,bet.win_money" +
                " FROM bet join event on" +
                " event.event_id = bet.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on bet.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where bet.event_id = ? ";
    }


    String getCountActiveBetForUserQuery(){
        return  "SELECT count(bet.bet_id) as betId" +
                " FROM bet join event on" +
                " event.event_id = bet.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on bet.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where competition.status = 'new' and bet.user_id = ?";
    }

    String getActiveBetLimitQuery() {
        return  "SELECT bet.bet_id,event.event_id,competition.competition_id," +
                "bet.user_id,t1.name,t2.name,competition.team_first_result," +
                "competition.team_second_result,competition.winner as winner_result,rate_type.name," +
                "bet.winner_id,bet.team_first_score,bet.team_second_score," +
                "bet.money as betMoney,competition.status,bet.win_money" +
                " FROM bet join event on" +
                " event.event_id = bet.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on bet.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where competition.status = 'new' and bet.user_id = ? limit ?,?";
    }
    String getSelectSqlResultBetting(){
        return  "SELECT bet.bet_id,event.event_id,competition.competition_id," +
                "bet.user_id,t1.name,t2.name,competition.team_first_result," +
                "competition.team_second_result,competition.winner as winner_result,rate_type.name," +
                "bet.winner_id,bet.team_first_score,bet.team_second_score," +
                "bet.money as betMoney,competition.status,bet.win_money" +
                " FROM bet join event on" +
                " event.event_id = bet.event_id" +
                " join competition on" +
                " competition.competition_id = event.competition_id" +
                " join user u on bet.user_id = u.user_id" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " join rate_type on" +
                " rate_type.rate_id = event.rate_id"+
                " where event.payment = 1 and bet.user_id = ?";
    }

    String getEventByPaymentQuery(){
        return "SELECT  event.event_id from event " +
                " join competition on competition.competition_id = event.competition_id" +
                " where event.event_id = ? and payment = 0 and  competition.status = 'finished'";
    }


}
