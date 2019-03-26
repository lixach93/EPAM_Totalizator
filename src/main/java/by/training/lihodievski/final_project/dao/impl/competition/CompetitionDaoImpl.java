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
import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

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
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, Competition object) throws DaoException {
        try {
            preparedStatement.setLong (1,object.getId ());
        } catch (SQLException e) {
            LOGGER.error ("Exception in preparedStatementUpdate in CompetitionDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

    @Override
    protected List<Competition> parseResultSet(ResultSet resultSet, List<Competition> list) throws DaoException {
        try {
            while (resultSet.next ()){
                Competition competition = new Competition ();
                competition.setId (resultSet.getLong (COMPETITION_ID));
                competition.getFirstTeam ().setTeamName (resultSet.getString (TEAM_FIRST_NAME));
                competition.getSecondTeam ().setTeamName (resultSet.getString (TEAM_SECOND_NAME));
                competition.setStatus (resultSet.getString (STATUS));
                competition.setFirstTeamResult (resultSet.getInt (TEAM_FIRST_RESULT));
                competition.setSecondTeamResult (resultSet.getInt (TEAM_SECOND_RESULT));
                list.add (competition);
            }
        }catch (SQLException e){
            LOGGER.error ("Exception in parseResultSet in CompetitionDaoImpl.class ", e);
            throw new DaoException ();
        }
        return list;
    }

    public Competition changeStatus(Competition competition) throws DaoException {
        String competitionByIdQuery = getCompetitionByIdQuery ();
        String updateCategoryQuery = getUpdateSql ();
        List<Competition> competitions = new ArrayList<> ();
        try(ProxyConnection connection = connectionPool.takeConnection ();
            AutoSetAutoCommit autoSetAutoCommit = new AutoSetAutoCommit (connection,false);
            AutoRollback rollback = new AutoRollback (connection);
            PreparedStatement update = connection.prepareStatement (updateCategoryQuery);
            PreparedStatement select = connection.prepareStatement (competitionByIdQuery);){

            select.setLong (1,competition.getId ());
            ResultSet resultSet = select.executeQuery ();
            if(resultSet.next () && resultSet.getString (STATUS).equals (STATUS_NEW)){
                update.setInt (1,competition.getFirstTeamResult ());
                update.setInt (2,competition.getSecondTeamResult ());
                update.setInt (3,competition.getWinner ());
                update.setLong (4,competition.getId ());
                update.executeUpdate ();
                select.setLong (1,competition.getId ());
                resultSet = select.executeQuery ();
                parseResultSet (resultSet, competitions);
                rollback.commit ();
                return competitions.iterator ().next ();
            }else{
               return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in changeStatus in  CompetitionDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }

}
