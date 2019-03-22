package by.training.lihodievski.final_project.dao.impl.competition_rate;

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
                leagueOne.setLeagueName (resultSet.getString ("league_first_name"));
                Category categoryOne = Category.valueOf (resultSet.getString ("category_first_name").toUpperCase ());
                Team teamTwo = new Team ();
                teamTwo.setId (resultSet.getLong ("team_second_id"));
                teamTwo.setNameTeam (resultSet.getString ("team_second_name"));
                League leagueTwo= new League ();
                leagueTwo.setLeagueName (resultSet.getString ("league_second_name"));
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
            LOGGER.error ("Exception in EventDaoImpl", e);
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
            return resultSet.getInt ("countEvent");
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
            return resultSet.getInt ("countEvent");
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public int getCountPageUnPaymentEvents() throws DaoException {
        String query = getCountEventByUnPaymentQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return resultSet.getInt ("countEvent");
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
            throw new DaoException (e);
        }
    }

    @Override
    public List<Event> getEventsByCategory(Category category, int numberPage) throws DaoException {
        String query = getEventsByCategory ();
        List<Event> events = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1, category.getCategoryName ());
            preparedStatement.setInt (2, numberPage);
            preparedStatement.setInt (3, Constants.PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            events = parseResultSet (resultSet, events);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in EventDaoImpl", e);
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
            LOGGER.error ("Exception in EventDaoImpl", e);
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
            LOGGER.error ("Exception in EventDaoImpl", e);
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
            LOGGER.error ("Exception in EventDaoImpl", e);
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
            if(resultSet.next () && resultSet.getString ("c.status").equals ("finished")
                    && !resultSet.getBoolean ("e.payment")){
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
