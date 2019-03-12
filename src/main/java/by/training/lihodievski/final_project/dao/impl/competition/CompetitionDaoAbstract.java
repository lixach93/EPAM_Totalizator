package by.training.lihodievski.final_project.dao.impl.competition;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

public abstract class CompetitionDaoAbstract extends AbstractGenericDao<Competition> {

    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSQL() {
        return "update competition set status = 'finished'," +
                "team_first_result=?,team_second_result =?, winner=? where competition_id =?" ;
    }

    @Override
    protected String getSelectSql () {
        return "SELECT competition_id, t1.name,t2.name,status," +
                "team_first_result, team_second_result FROM totalizator.competition" +
                " join team t1 on" +
                " competition.team_first = t1.team_id" +
                " join team t2 on" +
                " competition.team_second = t2.team_id" +
                " where status = 'new'";
    }

    @Override
    protected String getInsertSql() {
        return null;
    }

    protected String getSelectSqlCompetitionById(){
        return "SELECT competition_id from totalizator.competition where competition_id = ? and status ='new'";
    }

    public abstract boolean updateStatus(Competition competition) throws DaoException;
}
