package by.training.lihodievski.final_project.dao.impl.competition;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

public abstract class CompetitionDaoAbstract extends AbstractGenericDao<Competition> {

    public abstract Competition changeStatus(Competition competition) throws DaoException;

    @Override
    protected String getInsertSql() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getDeleteSQL() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE competition set status = 'finished'," +
                "team_first_result=?,team_second_result =?, winner=? WHERE competition_id =?" ;
    }

    @Override
    protected String getSelectSql () {
        return "SELECT competition_id, t1.name,t2.name,status," +
                "team_first_result, team_second_result FROM totalizator.competition" +
                " JOIN team t1 ON" +
                " competition.team_first = t1.team_id" +
                " JOIN team t2 ON" +
                " competition.team_second = t2.team_id" +
                " WHERE status = 'new'";
    }
    String getCompetitionByIdQuery(){
        return "SELECT competition_id, t1.name,t2.name,status," +
                "team_first_result, team_second_result FROM totalizator.competition" +
                " JOIN team t1 ON" +
                " competition.team_first = t1.team_id" +
                " JOIN team t2 ON" +
                " competition.team_second = t2.team_id" +
                " WHERE competition_id = ?";
    }


}
