package by.training.lihodievski.final_project.dao.impl.competition_rate;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetitionRateImpl extends CompetitionRateAbstract {
    private final static CompetitionRateImpl INSTANCE = new CompetitionRateImpl ();
    private ProxyConnection proxyConnection;
    public void setProxyConnection(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }
    public List<CompetitionRate> test(String category, String rate) throws DaoException {
        try (ProxyConnection connection=(proxyConnection==null? connectionPool.takeConnection ():proxyConnection);){
            System.out.println ("test one");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException (e);
        }
        return null;
    }
    public List<CompetitionRate> test2(String category, String rate) throws DaoException {
        try (ProxyConnection connection=proxyConnection=(proxyConnection==null? connectionPool.takeConnection ():proxyConnection);){
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException (e);
        }
        return null;
    }


    private CompetitionRateImpl() {
    }

    public static CompetitionRateImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, CompetitionRate object) throws DaoException {
        throw new UnsupportedOperationException ();
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, CompetitionRate object) throws DaoException {
        throw new UnsupportedOperationException ();
    }

    @Override
    protected List<CompetitionRate> parseResultSet(ResultSet resultSet, List<CompetitionRate> list) throws DaoException {
        try {
            while (resultSet.next ()){
                CompetitionRate competitionRate = new CompetitionRate ();
                competitionRate.setId (resultSet.getLong ("comp_rate.competition_rate_id"));
                competitionRate.setPayment (resultSet.getBoolean ("comp_rate.payment"));
                Competition competition = new Competition ();
                Rate rate = Rate.valueOf (resultSet.getString ("t.name").toUpperCase ());
                competitionRate.setRate (rate);
                competition.setId (resultSet.getLong ("comp_rate.competition_id"));
                competition.setFirstOpponentResult (resultSet.getInt ("c.opponent_first_result"));
                competition.setSecondOpponentResult (resultSet.getInt ("c.opponent_second_result"));
                competition.setWinner(resultSet.getInt ("c.winner"));
                Opponent opponentOne = new Opponent ();
                Opponent opponentTwo = new Opponent ();
                League leagueOne = new League ();
                League leagueTwo= new League ();
                opponentOne.setId (resultSet.getLong ("opponentFirstId"));
                opponentTwo.setId (resultSet.getLong ("opponentFirstId"));
                opponentOne.setNameOpponent (resultSet.getString ("opponentFirstName"));
                opponentTwo.setNameOpponent (resultSet.getString ("o2.name"));
                leagueOne.setNameLeague (resultSet.getString ("tem1"));
                leagueTwo.setNameLeague (resultSet.getString ("tem2"));
                Category categoryOne = Category.valueOf (resultSet.getString ("categoryOne").toUpperCase ());
                Category categoryTwo = Category.valueOf (resultSet.getString ("categoryTwo").toUpperCase ());
                leagueOne.setCategory (categoryOne);
                leagueTwo.setCategory (categoryTwo);
                opponentOne.setLeague (leagueOne);
                opponentTwo.setLeague (leagueTwo);
                competition.setFirstOpponent (opponentOne);
                competition.setSecondOpponent(opponentTwo);
                competitionRate.setCompetition (competition);
                list.add (competitionRate);
            }
        }catch (SQLException e){
            throw new DaoException (e);
        }
        return list;
    }


    @Override
    public List<CompetitionRate> getRatesByCategoryAndRate(String category, String rate) throws DaoException {
        String query = getSelectSqlByCategoryAndRate ();
        List<CompetitionRate> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,category);
            preparedStatement.setString (2,rate);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException e) {
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
            throw new DaoException (e);
        }
        return list;
    }

    @Override
    public CompetitionRate getCompetitionRateById(Long id) throws DaoException {
        String query = getSelectSqlById ();
        List<CompetitionRate> list = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setLong (1,id);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException e) {
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
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
            throw new DaoException (e);
        }

    }

    @Override
    public List<CompetitionRate> getUnPaymentRate() throws DaoException {
        String query = getSelectSqlByPaymentStatus ();
        List<CompetitionRate> competitionRates = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            ResultSet resultSet = preparedStatement.executeQuery ();
            competitionRates = parseResultSet(resultSet, competitionRates);
        } catch (SQLException e) {
            throw new DaoException (e);
        } catch (ConnectionPoolException e) {
            throw new DaoException (e);
        }
        return competitionRates;
    }
}
