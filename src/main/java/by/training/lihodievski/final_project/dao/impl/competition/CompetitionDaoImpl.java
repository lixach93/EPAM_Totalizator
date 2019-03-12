package by.training.lihodievski.final_project.dao.impl.competition;

import by.training.lihodievski.final_project.bean.Competition;
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
import java.util.List;

public class CompetitionDaoImpl extends CompetitionDaoAbstract {

    private static final Logger LOGGER = LogManager.getLogger (CompetitionDaoImpl.class);
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
                competition.getFirstTeam ().setNameTeam (resultSet.getString ("t1.name"));
                competition.getSecondTeam ().setNameTeam (resultSet.getString ("t2.name"));
                competition.setStatus (resultSet.getString ("status"));
                competition.setFirstOpponentResult (resultSet.getInt ("team_first_result"));
                competition.setSecondOpponentResult (resultSet.getInt ("team_second_result"));
                list.add (competition);
            }
        }catch (SQLException e){
            LOGGER.error ("Error in CompetitionDaoImpl.class ", e);
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
            PreparedStatement select = connection.prepareStatement (selectQuery);
            PreparedStatement update = connection.prepareStatement (updateQuery);){

            select.setLong (1,competition.getId ());
            ResultSet resultSet = select.executeQuery ();
            if(resultSet.next ()){
                update.setInt (1,competition.getFirstOpponentResult ());
                update.setInt (2,competition.getSecondOpponentResult ());
                update.setInt (3,competition.getWinner ());
                update.setLong (4,competition.getId ());
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
