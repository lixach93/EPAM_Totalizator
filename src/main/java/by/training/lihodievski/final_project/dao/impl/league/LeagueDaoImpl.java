package by.training.lihodievski.final_project.dao.impl.league;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
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

import static by.training.lihodievski.final_project.util.Constants.LEAGUE_ID;
import static by.training.lihodievski.final_project.util.Constants.LEAGUE_NAME;

public class LeagueDaoImpl extends LeagueDaoAbstract {

    private static final Logger LOGGER = LogManager.getLogger (LeagueDaoImpl.class);
    private static final LeagueDaoImpl INSTANCE = new LeagueDaoImpl ();

    private LeagueDaoImpl() {
    }

    public static LeagueDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, League object) throws DaoException {
        try {
            preparedStatement.setLong (1, object.getCategory ().getId ());
            preparedStatement.setString (2, object.getLeagueName ());
        }catch (SQLException e){
            LOGGER.error ("Exception in insert in LeagueDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, League object) throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected List<League> parseResultSet(ResultSet resultSet, List<League> list) throws DaoException {
        try {
            while (resultSet.next ()){
                League league = new League ();
                league.setId (resultSet.getLong (LEAGUE_ID));
                league.setLeagueName (resultSet.getString (LEAGUE_NAME));
                list.add (league);
            }
        }catch (SQLException e){
            LOGGER.error ("Exception in parseResultSet in LeagueDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public List<League> getLeaguesByCategory(Category category) throws DaoException {
        String query = getLeagueByCategoryQuery ();
        List<League> leagues = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            PreparedStatement statement = connection.prepareStatement (query)){
            statement.setLong (1,category.getId ());
            ResultSet resultSet = statement.executeQuery ();
            leagues = parseResultSet (resultSet, leagues);
        }catch (SQLException | ConnectionPoolException e){
            LOGGER.error ("Exception in getLeaguesByCategory in LeagueDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return leagues;
    }

    @Override
    public League getLeagueById(long leagueId) throws DaoException {
        String sqlQuery = getLeagueByIdQuery ();
        List<League> leagues = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            PreparedStatement statement = connection.prepareStatement (sqlQuery)){
            statement.setLong (1,leagueId);
            ResultSet resultSet = statement.executeQuery ();
            leagues = parseResultSet (resultSet, leagues);
        }catch (SQLException | ConnectionPoolException e){
            LOGGER.error ("Exception in getLeagueById in LeagueDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return leagues.iterator ().next ();
    }

    @Override
    public boolean deleteLeague(League league) throws DaoException {
        String deleteQuery = getDeleteSQL ();
        String unUsedLeagueQuery = getUnUsedLeagueQuery();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement deleteStatement = connection.prepareStatement (deleteQuery);
             PreparedStatement selectStatement = connection.prepareStatement (unUsedLeagueQuery);){

            selectStatement.setLong (1, league.getId ());
            ResultSet resultSet = selectStatement.executeQuery ();
            if (resultSet.next ()) {
                deleteStatement.setLong (1,league.getId ());
                deleteStatement.execute ();
                rollback.commit ();
                return true;
            }else {
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in deleteLeague in LeagueDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }
}
