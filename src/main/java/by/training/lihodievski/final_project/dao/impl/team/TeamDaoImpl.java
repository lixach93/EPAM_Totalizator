package by.training.lihodievski.final_project.dao.impl.team;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Team;
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

public class TeamDaoImpl extends TeamDaoAbstract {

    private static final Logger LOGGER = LogManager.getLogger (TeamDaoImpl.class);
    private static final TeamDaoImpl INSTANCE = new TeamDaoImpl ();

    private TeamDaoImpl() {
    }
    public static TeamDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, Team object) throws DaoException {
        try{
            preparedStatement.setLong (1, object.getLeague ().getId ());
            preparedStatement.setString (2, object.getNameTeam ());
        }catch (SQLException e){
            LOGGER.error ("Exception in insert in TeamDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Team object) throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected List<Team> parseResultSet(ResultSet resultSet, List<Team> list) throws DaoException {
        try{
            while (resultSet.next ()){
                Team team = new Team ();
                team.setId (resultSet.getLong ("team_id"));
                team.setNameTeam (resultSet.getString ("name"));
                list.add (team);
            }
        } catch (SQLException e) {
            LOGGER.error ("Exception in parseResultSet in TeamDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return list;
    }


    @Override
    public List<Team> getTeamByLeague(League league) throws DaoException {
        String query = getTeamByLeagueQuery ();
        List<Team> teams = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            PreparedStatement statement = connection.prepareStatement (query)){
            statement.setLong (1,league.getId ());
            ResultSet resultSet = statement.executeQuery ();
            teams = parseResultSet (resultSet, teams);
        }catch (SQLException | ConnectionPoolException e){
            LOGGER.error ("Exception in getTeamByLeague in TeamDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return teams;
    }
}
