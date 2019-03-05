package by.training.lihodievski.final_project.dao.impl.competition;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.connection.AutoRollback;
import by.training.lihodievski.final_project.connection.AutoSetAutoCommit;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompetitionDaoImpl extends CompetitionDaoAbstract {

    private static final CompetitionDaoImpl INSTANCE = new CompetitionDaoImpl ();
    private CompetitionDaoImpl() {
    }

    public static CompetitionDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, Competition object) throws DaoException {

    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Competition object) throws DaoException {
        try {
            preparedStatement.setLong (1,object.getId ());
        } catch (SQLException e) {
            throw new DaoException (e);
        }
    }

    @Override
    protected List<Competition> parseResultSet(ResultSet resultSet, List<Competition> list) throws DaoException {
        try {
            while (resultSet.next ()){
                Competition competition = new Competition ();
                competition.setId (resultSet.getLong ("competition_id"));
                competition.getFirstOpponent ().setNameOpponent (resultSet.getString ("o1.name"));
                competition.getSecondOpponent ().setNameOpponent (resultSet.getString ("o2.name"));
                competition.setStatus (resultSet.getString ("status"));
                competition.setFirstOpponentResult (resultSet.getInt ("opponent_first_result"));
                competition.setSecondOpponentResult (resultSet.getInt ("opponent_second_result"));
                list.add (competition);
            }
        }catch (SQLException e){
            throw new DaoException ();
        }
        return list;
    }

    public boolean updateStatus(Competition competition) throws DaoException {
        String selectQuery = getSelectSqlCompetitionById ();
        String updateQuery = getUpdateSQL ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
            AutoRollback rollback = new AutoRollback (connection);
            PreparedStatement preparedStatement = connection.prepareStatement (selectQuery);
            PreparedStatement update = connection.prepareStatement (updateQuery);){

            preparedStatement.setLong (1,competition.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            if(resultSet.next ()){
                update.setLong (1,competition.getId ());
                update.executeUpdate ();
                rollback.commit ();
                return true;
            }else{
               autoSetAutoCommit.setOriginalAutoCommit (true);
               return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException (e);
        }
    }


}
