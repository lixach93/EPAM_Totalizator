package by.training.lihodievski.final_project.dao.impl.league;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueDaoImpl extends LeagueDaoAbstract {

    private final static LeagueDaoImpl INSTANCE = new LeagueDaoImpl ();
    private LeagueDaoImpl() {
    }

    public static LeagueDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, League object) throws DaoException {
        try {
            preparedStatement.setLong (1, object.getId ());
            preparedStatement.setString (2, object.getNameLeague ());
        }catch (SQLException e){
            throw new DaoException (e);
        }
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, League object) throws DaoException {

    }

    @Override
    protected List<League> parseResultSet(ResultSet resultSet, List<League> list) throws DaoException {
        try {
            while (resultSet.next ()){
                League league = new League ();
                league.setId (resultSet.getLong ("league_id"));
                league.setNameLeague (resultSet.getString ("name"));
                list.add (league);
            }
        }catch (SQLException e){
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public List<League> getLeaguesByCategory(Category category) throws DaoException {
        String sqlQuery = getSelectSqlByCategory ();
        List<League> leagues = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            PreparedStatement statement = connection.prepareStatement (sqlQuery)){
            statement.setLong (1,category.getId ());
            ResultSet resultSet = statement.executeQuery ();
            leagues = parseResultSet (resultSet, leagues);
        }catch (SQLException e){
            throw new DaoException (e);
        }catch (ConnectionPoolException e){
            throw new DaoException (e);
        }
        return leagues;
    }
}