package by.training.lihodievski.final_project.dao.impl.betting;

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

public class BettingDaoImpl extends BettingDaoAbstract {

    private static final Logger LOGGER = LogManager.getLogger (BettingDaoImpl.class);
    private static BettingDaoImpl INSTANCE = new BettingDaoImpl ();

    private BettingDaoImpl() {

    }
    public static BettingDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, Betting object) throws DaoException {


    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Betting object) throws DaoException {

    }

    @Override
    protected List<Betting> parseResultSet(ResultSet resultSet, List<Betting> list) throws DaoException {
        try {
            while (resultSet.next ()){
                Betting betting = new Betting ();
                Event event = new Event ();
                Team teamFirst = new Team ();
                teamFirst.setNameTeam (resultSet.getString ("t1.name"));
                Team teamSecond = new Team ();
                teamSecond.setNameTeam (resultSet.getString ("t2.name"));
                Competition competition = new Competition ();
                competition.setFirstTeam (teamFirst);
                competition.setSecondTeam (teamSecond);
                competition.setFirstOpponentResult (resultSet.getInt ("team_first_result"));
                competition.setSecondOpponentResult (resultSet.getInt ("team_second_result"));
                competition.setWinner (resultSet.getInt ("winner_result"));
                Rate rate = Rate.valueOf (resultSet.getString ("rate_type.name").toUpperCase ());
                event.setRate (rate);
                event.setCompetition (competition);
                User user = new User ();
                user.setId (resultSet.getLong ("user_id"));
                user.setMoney (resultSet.getDouble ("u.money"));
                betting.setId (resultSet.getLong ("betting_id"));
                betting.setWinner (resultSet.getInt ("winner_id"));
                betting.setOpponentFirstScore (resultSet.getInt ("team_first_score"));
                betting.setOpponentSecondScore (resultSet.getInt ("team_second_score"));
                betting.setMoney (resultSet.getDouble ("betMoney"));
                betting.setWinMoney (resultSet.getDouble ("win_money"));
                betting.setUser (user);
                betting.setEvent (event);
                list.add (betting);
            }
        }catch (SQLException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
           throw new DaoException (e);
        }
        return list;
    }




    @Override
    public boolean insertBetting(Betting object) throws DaoException {
        String insertQuery = getInsertSql ();
        String updateUserBalanceQuery = getUpdateUserBalanceQuery ();
        String eventQuery = getCompetitionByIdQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement insert = connection.prepareStatement (insertQuery);
             PreparedStatement update = connection.prepareStatement (updateUserBalanceQuery);
             PreparedStatement select = connection.prepareStatement (eventQuery);){

            select.setLong (1, object.getEvent ().getId ());
            ResultSet rS = select.executeQuery ();
            if(rS.next () && rS.getString ("competition.status").equals ("new")) {
                insert.setLong (1, object.getUser ().getId ());
                insert.setLong (2, object.getEvent ().getId ());
                insert.setLong (3, object.getWinner ());
                insert.setLong (4, object.getOpponentFirstScore ());
                insert.setLong (5, object.getOpponentSecondScore ());
                insert.setDouble (6, object.getMoney ());
                insert.executeUpdate ();
                update.setLong (2, object.getUser ().getId ());
                update.setDouble (1, object.getUser ().getMoney ());
                update.executeUpdate ();
                rollback.commit ();
                return true;
            }else{
                autoSetAutoCommit.setOriginalAutoCommit (true);
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
           throw new DaoException (e);
        }
    }

    @Override
    public Double getMoneyForCompRate(Event event) throws DaoException {
        String query = getSelectSqlMoney ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);) {
            preparedStatement.setLong (1, event.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            if(resultSet.next ()){
                return resultSet.getDouble (1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
            throw new DaoException (e);
        }

        return 0.0;
    }

    @Override
    public List<Betting> getAllBettingByCompRate(Event event) throws DaoException {
        String query = getBettingByEvent ();
        List<Betting> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1, event.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return bettingList;
    }

    @Override
    public void setWinner(List<Betting> winner, double winMoney, Event event) throws DaoException {
        String updateBettingQuery = getUpdateSqlWinMoney ();
        String updateUserQuery = getUpdateUserBalanceQuery ();
        String updateCompRateQuery = getUpdateSqlForCompRate ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement updateBetting = connection.prepareStatement (updateBettingQuery);
             PreparedStatement updateUser = connection.prepareStatement (updateUserQuery);
             PreparedStatement updateEvent = connection.prepareStatement (updateCompRateQuery);){

            for(Betting betting:winner){
                updateBetting.setDouble (1, winMoney);
                updateBetting.setLong (2, betting.getUser ().getId ());
                updateBetting.setLong (3, event.getId ());
                updateBetting.executeUpdate ();
                updateUser.setLong (2, betting.getUser ().getId ());
                updateUser.setDouble (1, betting.getUser ().getMoney ()+winMoney);
                updateUser.executeUpdate ();
            }
            updateEvent.setBoolean (1, event.isPayment ());
            updateEvent.setLong (2, event.getId ());
            updateEvent.executeUpdate ();
            rollback.commit ();
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    public List<Betting> getActiveBettingForUser(User user) throws DaoException {
        String query = getActiveBettingQuery ();
        List<Betting> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1,user.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return bettingList;
    }

    @Override
    public List<Betting> getResultBettingForUser(User user) throws DaoException {
        String query = getSelectSqlResultBetting ();
        List<Betting> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1,user.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return bettingList;
    }
}
