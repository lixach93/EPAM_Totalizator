package by.training.lihodievski.final_project.dao.impl.betting;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.connection.AutoRollback;
import by.training.lihodievski.final_project.connection.AutoSetAutoCommit;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BettingDaoImpl extends BettingDaoAbstract {

    private static BettingDaoImpl INSTANCE = new BettingDaoImpl ();

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
                User user = new User ();
                user.setId (resultSet.getLong ("user_id"));
                user.setMoney (resultSet.getDouble ("u.money"));
                betting.setWinner (resultSet.getInt ("winner_id"));
                betting.setOpponentFirstScore (resultSet.getInt ("opponnet_firts_score"));
                betting.setOpponentSecondScore (resultSet.getInt ("opponnet_second_score"));
                betting.setUser (user);
                list.add (betting);
            }
        }catch (SQLException e) {
           throw new DaoException (e);
        }
        return list;
    }

    private BettingDaoImpl() {
    }


    @Override
    public void insertBetting(Betting object) throws DaoException {
        String insertQuery = getInsertSql ();
        String updateUserQuery = getUpdateSqlUserBalance ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement preparedStatement = connection.prepareStatement (insertQuery);
             PreparedStatement update = connection.prepareStatement (updateUserQuery);){

            preparedStatement.setLong (1,object.getUser ().getId ());
            preparedStatement.setLong (2,object.getCompetitionRate ().getId ());
            preparedStatement.setLong (3,object.getWinner ());
            preparedStatement.setLong (4,object.getOpponentFirstScore ());
            preparedStatement.setLong (5,object.getOpponentSecondScore ());
            preparedStatement.setDouble (6,object.getMoney ());
            preparedStatement.executeUpdate ();

            update.setLong (2, object.getUser ().getId ());
            update.setDouble (1, object.getUser ().getMoney ());
            update.executeUpdate ();
            rollback.commit ();
        } catch (SQLException | ConnectionPoolException e) {
           throw new DaoException (e);
        }
    }

    @Override
    public Double getMoneyForCompRate(CompetitionRate competitionRate) throws DaoException {
        String query = getSelectSqlMoney ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);) {
            preparedStatement.setLong (1,competitionRate.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            if(resultSet.next ()){
                return resultSet.getDouble (1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException (e);
        }

        return 0.0;
    }

    @Override
    public List<Betting> getAllBettingByCompRate(CompetitionRate competitionRate) throws DaoException {
        String query = getSelectSqlByCompRate ();
        List<Betting> bettingList = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setLong (1,competitionRate.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            bettingList = parseResultSet (resultSet, bettingList);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException (e);
        }
        return bettingList;
    }

    @Override
    public void setWinner(List<Betting> winner, double winMoney, CompetitionRate competitionRate) throws DaoException {
        String updateBettingQuery = getUpdateSqlWinMoney ();
        String updateUserQuery = getUpdateSqlUserBalance ();
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
                updateBetting.setLong (3,competitionRate.getId ());
                updateBetting.executeUpdate ();
                updateUser.setLong (2, betting.getUser ().getId ());
                updateUser.setDouble (1, betting.getUser ().getMoney ()+winMoney);
                updateUser.executeUpdate ();
            }
            updateEvent.setBoolean (1, competitionRate.isPayment ());
            updateEvent.setLong (2, competitionRate.getId ());
            updateEvent.executeUpdate ();
            rollback.commit ();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException (e);
        }
    }
}
