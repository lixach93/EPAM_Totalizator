package by.training.lihodievski.final_project.dao.impl.event;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.connection.AutoRollback;
import by.training.lihodievski.final_project.connection.AutoSetAutoCommit;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

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
                event.setId (resultSet.getLong (EVENT_ID));
                Competition competition = new Competition ();
                competition.setId (resultSet.getLong (EVENT_COMPETITION_ID));
                Team teamOne = new Team ();
                teamOne.setId (resultSet.getLong (TEAM_FIRST_ID));
                teamOne.setTeamName (resultSet.getString (EVENT_TEAM_FIRST_NAME));
                League leagueOne = new League ();
                leagueOne.setLeagueName (resultSet.getString (EVENT_LEAGUE_FIRST_NAME));
                Category categoryOne = Category.valueOf (resultSet.getString (EVENT_CATEGORY_FIRST_NAME).toUpperCase ());
                Team teamTwo = new Team ();
                teamTwo.setId (resultSet.getLong (TEAM_SECOND_ID));
                teamTwo.setTeamName (resultSet.getString (EVENT_TEAM_SECOND_NAME));
                League leagueTwo= new League ();
                leagueTwo.setLeagueName (resultSet.getString (EVENT_LEAGUE_SECOND_NAME));
                Category categoryTwo = Category.valueOf (resultSet.getString (EVENT_CATEGORY_SECOND_NAME).toUpperCase ());
                competition.setStatus (resultSet.getString (EVENT_STATUS));
                competition.setFirstTeamResult (resultSet.getInt (EVENT_TEAM_FIRST_RESULT));
                competition.setSecondTeamResult (resultSet.getInt (EVENT_TEAM_SECOND_RESULT));
                competition.setWinner(resultSet.getInt (EVENT_WINNER ));
                event.setPayment (resultSet.getBoolean (EVENT_PAYMENT));
                event.setPercent (resultSet.getDouble (EVENT_PERCENT));
                event.setWinPercent (resultSet.getDouble (EVENT_WIN_PERCENT));
                Rate rate = Rate.valueOf (resultSet.getString (RATE_VALUE).toUpperCase ());
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
    public List<Event> getEventsByRate(Rate rate, int numberPage) throws DaoException {
        String query = getEventsByRateQuery ();
        List<Event> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,rate.getValue ());
            preparedStatement.setInt (2, numberPage);
            preparedStatement.setInt (3, Constants.PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getEventsByRate in  EventDaoImpl", e);
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public List<Event> getClosedEvents(int page) throws DaoException {
        String query = getClosedEventsQuery ();
        List<Event> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setInt (1, page);
            preparedStatement.setInt (2, Constants.PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getClosedEvents in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public int getCountEventByCategory(Category category) throws DaoException {
        String query = getCountEventByCategoryQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,category.getCategoryName ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return resultSet.getInt (COUNT_EVENT );
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public int getCountEventByRate(Rate rate) throws DaoException {
        String query = getCountEventByRateQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,rate.getValue ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return resultSet.getInt (COUNT_EVENT );
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public int getCountUnPaymentEvents() throws DaoException {
        String query = getCountEventByUnPaymentQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return resultSet.getInt (COUNT_EVENT );
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getCountUnPaymentEvents in  EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public int getCountClosedEvents() throws DaoException {
        String query = getCountClosedEventsQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return resultSet.getInt (COUNT_EVENT);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getCountClosedEvents in EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public List<Event> getEventsByCategory(Category category, int numberPage) throws DaoException {
        String query = getEventsByCategoryQuery ();
        List<Event> events = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1, category.getCategoryName ());
            preparedStatement.setInt (2, numberPage);
            preparedStatement.setInt (3, Constants.PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            events = parseResultSet (resultSet, events);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getEventsByCategory in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return events;
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
            LOGGER.error ("Exception in getEventById EventDaoImpl", e);
            throw new DaoException (e);
        }
        return list.iterator ().next ();
    }

    @Override
    public void createEvent(long teamFirstId, long teamSecondId, Rate[] rate) throws DaoException {
        String insertCompetitionQuery = getInsertCompetitionQuery ();
        String insertQuery = getInsertSql();
        try (ProxyConnection connection = connectionPool.takeConnection ();
            AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
            AutoRollback rollback = new AutoRollback (connection);
            PreparedStatement preparedStatementCompetition = connection.prepareStatement (insertCompetitionQuery, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatementEvent = connection.prepareStatement (insertQuery);){

            preparedStatementCompetition.setLong (1, teamFirstId);
            preparedStatementCompetition.setLong (2, teamSecondId);
            preparedStatementCompetition.executeUpdate ();

            ResultSet resultSet = preparedStatementCompetition.getGeneratedKeys ();
            resultSet.next ();
            long competitionId = resultSet.getLong (1);
            for (Rate currentRate : rate) {
                preparedStatementEvent.setLong (1, competitionId);
                preparedStatementEvent.setLong (2, currentRate.getId ());
                preparedStatementEvent.executeUpdate ();
            }
            rollback.commit ();
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error ("Exception in createEvent in  EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public List<Event> getUnPaymentEvents(int page) throws DaoException {
        String query = getUnPaymentEventsQuery ();
        List<Event> events = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setInt (1, page);
            preparedStatement.setInt (2, Constants.PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            events = parseResultSet(resultSet, events);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getUnPaymentEvents in EventDaoImpl", e);
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
            LOGGER.error ("Exception in getActiveEvent in EventDaoImpl", e);
            throw new DaoException (e);
        }
        return events;
    }

    @Override
    public boolean updatePercent(Event event) throws DaoException {
        String activeEventQuery = getActiveEventQuery ();
        String updateEventQuery = getUpdateSql ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
            AutoRollback rollback = new AutoRollback (connection);
            PreparedStatement pSGet = connection.prepareStatement (activeEventQuery);
            PreparedStatement pSUpdate = connection.prepareStatement (updateEventQuery);){

            pSGet.setLong (1, event.getId ());
            ResultSet resultSet = pSGet.executeQuery ();
            if(resultSet.next ()){
                pSUpdate.setDouble (1,event.getPercent ());
                pSUpdate.setLong (2, event.getId ());
                pSUpdate.execute ();
                rollback.commit ();
                return true;
            }else{
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in changeStatus in  CompetitionDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    public boolean closeEvent(Event event) throws DaoException {
        String closeEventQuery = getCloseEventQuery ();
        String eventByIdQuery = getEventByIdQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
             AutoRollback rollback = new AutoRollback (connection);
             PreparedStatement updateStatement = connection.prepareStatement (closeEventQuery);
             PreparedStatement selectStatement = connection.prepareStatement (eventByIdQuery);){

            selectStatement.setLong (1, event.getId ());
            ResultSet resultSet = selectStatement.executeQuery ();
            if(resultSet.next () && resultSet.getString (COMPETITION_STATUS_ALIAS).equals (STATUS_FINISHED)
                    && !resultSet.getBoolean (EVENT_PAYMENT)){
                updateStatement.setBoolean (1, event.isPayment ());;
                updateStatement.setDouble (2, event.getWinPercent ());
                updateStatement.setLong (3, event.getId ());
                updateStatement.execute ();
                rollback.commit ();
                return true;
            }else {
                return false;
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
    }
}
