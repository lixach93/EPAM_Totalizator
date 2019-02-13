package by.training.lihodievski.final_project.dao.impl;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.dao.CompetitionDaoAbstract;
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

    }

    @Override
    protected List<Competition> parseResultSet(ResultSet resultSet, List<Competition> list) throws DaoException {
        try {
            while (resultSet.next ()){
                Competition competition = new Competition ();
                competition.setId (resultSet.getLong ("competition_id"));
                competition.getFirstOpponent ().setId (resultSet.getLong ("opponent_first"));
                competition.getSecondOpponent ().setId (resultSet.getLong ("opponent_second"));
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
}
