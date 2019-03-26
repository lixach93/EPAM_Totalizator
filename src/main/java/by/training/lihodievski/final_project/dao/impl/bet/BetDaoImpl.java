package by.training.lihodievski.final_project.dao.impl.bet;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.connection.AutoRollback;
import by.training.lihodievski.final_project.connection.AutoSetAutoCommit;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class BetDaoImpl extends BetDaoAbstract {

    private static final Logger LOGGER = LogManager.getLogger (BetDaoImpl.class);
    private static BetDaoImpl INSTANCE = new BetDaoImpl ();

    private BetDaoImpl() {

    }
    public static BetDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, Bet object) throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Bet object) throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected List<Bet> parseResultSet(ResultSet resultSet, List<Bet> list) throws DaoException {
        try {
            while (resultSet.next ()){
                Bet bet = new Bet ();
                Event event = new Event ();
                Team teamFirst = new Team ();
                teamFirst.setTeamName (resultSet.getString (TEAM_FIRST_NAME ));
                Team teamSecond = new Team ();
                teamSecond.setTeamName (resultSet.getString (TEAM_SECOND_NAME));
                Competition competition = new Competition ();
                competition.setFirstTeam (teamFirst);
                competition.setSecondTeam (teamSecond);
                competition.setFirstTeamResult (resultSet.getInt (TEAM_FIRST_RESULT));
                competition.setSecondTeamResult (resultSet.getInt (TEAM_SECOND_RESULT));
                competition.setWinner (resultSet.getInt (WINNER_RESULT));
                Rate rate = Rate.valueOf (resultSet.getString (RATE_TYPE_NAME).toUpperCase ());
                event.setRate (rate);
                event.setCompetition (competition);
                User user = new User ();
                user.setId (resultSet.getLong (USER_ID));
                bet.setId (resultSet.getLong (BET_ID));
                bet.setWinner (resultSet.getInt (WINNER_ID));
                bet.setTeamFirstScore (resultSet.getInt (TEAM_FIRST_SCORE));
                bet.setTeamSecondScore (resultSet.getInt (TEAM_SECOND_SCORE));
                bet.setMoney (resultSet.getDouble (BET_MONEY));
                bet.setWinMoney (resultSet.getDouble (WIN_MONEY));
                bet.setUser (user);
                bet.setEvent (event);
                list.add (bet);
            }
        }catch (SQLException e) {
            LOGGER.error ("Exception in BetDaoImpl.class ", e);
           throw new DaoException (e);
        }
        return list;
    }




    @Override
    public boolean insertBet(Bet object) throws DaoException {
        String insertQuery = getInsertSql ();
        String updateUserBalanceQuery = getUpdateUserBalanceQuery ();
        String competitionByIdQuery = getCompetitionByIdQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement insert = connection.prepareStatement (insertQuery);
             PreparedStatement update = connection.prepareStatement (updateUserBalanceQuery);
             PreparedStatement select = connection.prepareStatement (competitionByIdQuery);){

            select.setLong (1, object.getEvent ().getId ());
            ResultSet rS = select.executeQuery ();
            if(rS.next () && rS.getString (COMPETITION_STATUS).equals (STATUS_NEW)) {
                insert.setLong (1, object.getUser ().getId ());
                insert.setLong (2, object.getEvent ().getId ());
                insert.setLong (3, object.getWinner ());
                insert.setLong (4, object.getTeamFirstScore ());
                insert.setLong (5, object.getTeamSecondScore ());
                insert.setDouble (6, object.getMoney ());
                insert.executeUpdate ();
                update.setLong (2, object.getUser ().getId ());
                update.setDouble (1, object.getUser ().getMoney ());
                update.executeUpdate ();
                rollback.commit ();
                return true;
            }else{
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in insertBet in BetDaoImpl.class ", e);
           throw new DaoException (e);
        }
    }

    @Override
    public Double getBetMoneyByEvent(Event event) throws DaoException {
        String betMoneyByEventQuery = getBetMoneyByEventQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (betMoneyByEventQuery);) {
            preparedStatement.setLong (1, event.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            if(resultSet.next ()){
                return resultSet.getDouble (1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getBetMoneyByEvent in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }

        return 0.0;
    }

    @Override
    public List<Bet> getBetsByEvent(Event event) throws DaoException {
        String query = getBettingByEvent ();
        List<Bet> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1, event.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getBetsByEvent in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return bettingList;
    }

    @Override
    public boolean setWinner(List<Bet> winner, double winMoney, Event event) throws DaoException {
        String paymentQuery = getPaymentQuery ();
        String eventByPaymentQuery = getEventByPaymentQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement updateStatement = connection.prepareStatement (paymentQuery);
             PreparedStatement selectStatement = connection.prepareStatement (eventByPaymentQuery);){

            selectStatement.setLong (1, event.getId ());
            ResultSet resultSet = selectStatement.executeQuery ();
            if (resultSet.next ()) {
                updateStatement.setLong (6, event.getId ());
                updateStatement.setBoolean (3, event.isPayment ());
                updateStatement.setDouble (4, event.getWinPercent ());
                for (Bet bet : winner) {
                    updateStatement.setDouble (1, winMoney);
                    updateStatement.setDouble (2, winMoney);
                    updateStatement.setLong (5, bet.getUser ().getId ());
                    updateStatement.setLong (7, bet.getId ());

                    updateStatement.executeUpdate ();
                }
                rollback.commit ();
                return true;
            }else {
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in setWinner in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    public int getCountActiveBetForUser(User user) throws DaoException {
        String query = getCountActiveBetForUserQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1,user.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return  resultSet.getInt (BET_ID_ALIAS );
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getCountActiveBetForUser in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    public List<Bet> getActiveBettingLimitForUser(User user, int page) throws DaoException {
        String query = getActiveBetLimitQuery ();
        List<Bet> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1,user.getId ());
            preparedStatement.setInt (2, page);
            preparedStatement.setInt (3, PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getActiveBettingLimitForUser in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return bettingList;
    }


    @Override
    public List<Bet> getResultForUser(User user, int page) throws DaoException {
        String query = getResultQuery ();
        List<Bet> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1, user.getId ());
            preparedStatement.setLong (2, page);
            preparedStatement.setLong (3, PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getResultForUser in  BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return bettingList;
    }

    @Override
    public int getCountResultForUser(User user) throws DaoException {
        String query = getCountResultQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1,user.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return  resultSet.getInt (BET_ID_ALIAS );
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getCountResultForUser in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }
}
