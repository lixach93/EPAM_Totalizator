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
        return "update competition set status = 'finished' where competition_id =?" ;
    }

    @Override
    protected String getSelectSql () {
        return "SELECT competition_id, o1.name,o2.name,status," +
                "                opponent_first_result, opponent_second_result FROM totalizator.competition\n" +
                "                join opponent o1 on\n" +
                "                competition.opponent_first = o1.opponent_id\n" +
                "                join opponent o2 on\n" +
                "                competition.opponent_second = o2.opponent_id\n" +
                "\t\t\n" +
                "                where status = 'new'";
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
