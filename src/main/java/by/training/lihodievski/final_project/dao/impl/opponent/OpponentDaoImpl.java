package by.training.lihodievski.final_project.dao.impl.opponent;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Opponent;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpponentDaoImpl extends OpponentDaoAbstract{

    private static OpponentDaoImpl INSTANCE = new OpponentDaoImpl ();

    private OpponentDaoImpl() {
    }
    public static OpponentDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, Opponent object) throws DaoException {
        try{
            preparedStatement.setLong (1, object.getId ());
            preparedStatement.setString (2, object.getNameOpponent ());
        }catch (SQLException e){
            throw new DaoException (e);
        }

    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Opponent object) throws DaoException {

    }

    @Override
    protected List<Opponent> parseResultSet(ResultSet resultSet, List<Opponent> list) throws DaoException {
        try{
            while (resultSet.next ()){
                Opponent opponent = new Opponent ();
                opponent.setId (resultSet.getLong ("opponent_id"));
                opponent.setNameOpponent (resultSet.getString ("name"));
                list.add (opponent);
            }
        } catch (SQLException e) {
            throw new DaoException (e);
        }
        return list;
    }


    @Override
    public List<Opponent> getOpponentByLeague(League league) throws DaoException {
        String sqlQuery = getSelectSqlOpponentByLeague ();
        List<Opponent> opponents = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            PreparedStatement statement = connection.prepareStatement (sqlQuery)){
            statement.setLong (1,league.getId ());
            ResultSet resultSet = statement.executeQuery ();
            opponents = parseResultSet (resultSet, opponents);
        }catch (SQLException e){
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
            throw new DaoException (e);
        }
        return opponents;
    }
}
