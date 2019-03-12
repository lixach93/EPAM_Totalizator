package by.training.lihodievski.final_project.dao.impl.competition_rate;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl extends EventDaoAbstract {

    private static final Logger LOGGER = LogManager.getLogger (EventDaoImpl.class);
    private static final  EventDaoImpl INSTANCE = new EventDaoImpl ();

    private EventDaoImpl() {
    }

    public static EventDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, Event object) throws DaoException {
        throw new UnsupportedOperationException ();
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Event object) throws DaoException {
        try {
            preparedStatement.setDouble (1,object.getPercent ());
            preparedStatement.setLong (2, object.getId ());
        } catch (SQLException e) {
            throw new DaoException ();
        }
    }

    @Override
    protected List<Event> parseResultSet(ResultSet resultSet, List<Event> list) throws DaoException {
        try {
            while (resultSet.next ()){
                Event event = new Event ();
                event.setId (resultSet.getLong ("e.event_id"));
                Competition competition = new Competition ();
                competition.setId (resultSet.getLong ("e.competition_id"));
                Team teamOne = new Team ();
                teamOne.setId (resultSet.getLong ("team_first_id"));
                teamOne.setNameTeam (resultSet.getString ("team_first_name"));
                League leagueOne = new League ();
                leagueOne.setNameLeague (resultSet.getString ("league_first_name"));
                Category categoryOne = Category.valueOf (resultSet.getString ("category_first_name").toUpperCase ());
                Team teamTwo = new Team ();
                teamTwo.setId (resultSet.getLong ("team_second_id"));
                teamTwo.setNameTeam (resultSet.getString ("team_second_name"));
                League leagueTwo= new League ();
                leagueTwo.setNameLeague (resultSet.getString ("league_second_name"));
                Category categoryTwo = Category.valueOf (resultSet.getString ("category_second_name").toUpperCase ());
                competition.setStatus (resultSet.getString ("c.status"));
                competition.setFirstOpponentResult (resultSet.getInt ("c.team_first_result"));
                competition.setSecondOpponentResult (resultSet.getInt ("c.team_second_result"));
                competition.setWinner(resultSet.getInt ("c.winner"));
                event.setPayment (resultSet.getBoolean ("e.payment"));
                event.setPercent (resultSet.getDouble ("e.percent"));
                event.setWinPercent (resultSet.getDouble ("e.win_percent"));
                Rate rate = Rate.valueOf (resultSet.getString ("rate_value").toUpperCase ());
                leagueOne.setCategory (categoryOne);
                leagueTwo.setCategory (categoryTwo);
                teamOne.setLeague (leagueOne);
                teamTwo.setLeague (leagueTwo);
                competition.setFirstTeam (teamOne);
                competition.setSecondTeam (teamTwo);
                event.setCompetition (competition);
                event.setRate (rate);
                list.add (event);
            }
        }catch (SQLException e){
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return list;
    }


    @Override
    public List<Event> getEventsByRate(String category, String rate) throws DaoException {
        String query = getEventsByRateQuery ();
        List<Event> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,category);
            preparedStatement.setString (2,rate);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public Event getEventById(Long id) throws DaoException {
        String query = getEventByIdQuery ();
        List<Event> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setLong (1,id);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return list.iterator ().next ();
    }

    @Override
    public void createCompetitionRate(long opponentFirstId, long opponentSecondId, Rate[] rate) throws DaoException {
        String insertCompetitionQuery = getInsertSqlCompetition ();
        String insertQuery = getInsertSql();
        try (ProxyConnection connection = connectionPool.takeConnection ()){
            try(PreparedStatement preparedStatementCompetition = connection.prepareStatement (insertCompetitionQuery, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatement = connection.prepareStatement (insertQuery);){
                connection.setAutoCommit (false);
                preparedStatementCompetition.setLong (1, opponentFirstId);
                preparedStatementCompetition.setLong (2, opponentSecondId);
                preparedStatementCompetition.executeUpdate ();
                ResultSet resultSet = preparedStatementCompetition.getGeneratedKeys ();
                resultSet.next ();
                long competitionId = resultSet.getLong (1);
                for (Rate currentRate : rate) {
                    preparedStatement.setLong (1, competitionId);
                    preparedStatement.setLong (2, currentRate.getId ());
                    preparedStatement.executeUpdate ();
                }
                connection.commit ();
            }catch (SQLException e){
                connection.rollback ();
                throw new DaoException (e);
            }
            finally {
                connection.setAutoCommit (true);
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }

    }

    @Override
    public List<Event> getUnPaymentRate() throws DaoException {
        String query = getEventByPaymentQuery ();
        List<Event> events = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            events = parseResultSet(resultSet, events);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return events;
    }

    @Override
    public List<Event> getActiveEvent() throws DaoException {
        String query = getActiveEventsQuery ();
        List<Event> events = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            events = parseResultSet(resultSet, events);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return events;
    }
}
