package by.training.lihodievski.final_project.dao.impl.competition_rate;

import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.HashMap;
import java.util.List;

public abstract class CompetitionRateAbstract extends AbstractGenericDao<CompetitionRate> {


    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSQL() {
        return null;
    }

    @Override
    protected String getSelectSql() {
        return null;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT into totalizator.competition_rate (competition_id, rate_id) value (? , ?)";
    }

    protected String getInsertSqlCompetition(){
        return "INSERT into totalizator.competition (opponent_first, opponent_second) value (?, ?)";
    }

    protected String getSelectSqlByCategoryAndRate(){
        return "select  comp_rate.competition_rate_id,comp_rate.competition_id,\n" +
                "comp_rate.payment,o1.opponent_id as opponentFirstId,o1.name as opponentFirstName,o2.opponent_id, o2.name ,c.status,c.opponent_first_result,c.opponent_second_result,c.winner,l1.name as tem1,l2.name as tem2,category1.name as categoryOne,category2.name as categoryTwo,t.name  from competition_rate comp_rate\n" +
                "join competition c on comp_rate.competition_id= c.competition_id \n" +
                "join rate_type t on comp_rate.rate_id = t.rate_id\n" +
                "join opponent o1 on c.opponent_first = o1.opponent_id\n" +
                "join opponent o2 on\n" +
                "c.opponent_second = o2.opponent_id\n" +
                "join league l1 on \n" +
                "l1.league_id = o1.league_id\n" +
                "join league l2 on\n" +
                "l2.league_id = o2.league_id\n" +
                "join category category1 on \n" +
                "category1.category_id = l1.category_id\n" +
                "join category category2 on \n" +
                "category2.category_id = l2.category_id\n" +
                "\n" +
                "where  category1.name in (select category2.name from competition_rate where category2.name = ?) and t.name = ? ;";
    }

    protected String getSelectSqlById(){
        return "select  comp_rate.competition_rate_id,comp_rate.competition_id,\n" +
                "comp_rate.payment,o1.opponent_id as opponentFirstId,o1.name as opponentFirstName,o2.opponent_id, o2.name ,c.status,c.opponent_first_result,c.opponent_second_result,c.winner,l1.name as tem1,l2.name as tem2,category1.name as categoryOne,category2.name as categoryTwo,t.name  from competition_rate comp_rate\n" +
                "join competition c on comp_rate.competition_id= c.competition_id \n" +
                "join rate_type t on comp_rate.rate_id = t.rate_id\n" +
                "join opponent o1 on c.opponent_first = o1.opponent_id\n" +
                "join opponent o2 on\n" +
                "c.opponent_second = o2.opponent_id\n" +
                "join league l1 on \n" +
                "l1.league_id = o1.league_id\n" +
                "join league l2 on\n" +
                "l2.league_id = o2.league_id\n" +
                "join category category1 on \n" +
                "category1.category_id = l1.category_id\n" +
                "join category category2 on \n" +
                "category2.category_id = l2.category_id\n" +
                "\n" +
                "where comp_rate.competition_rate_id =?";
    }

    protected String getSelectSqlByPaymentStatus(){
        return "select  comp_rate.competition_rate_id,comp_rate.competition_id,\n" +
                "comp_rate.payment,o1.opponent_id as opponentFirstId,o1.name as opponentFirstName,o2.opponent_id, o2.name ,c.status,c.opponent_first_result,c.opponent_second_result,c.winner,l1.name as tem1,l2.name as tem2,category1.name as categoryOne,category2.name as categoryTwo,t.name  from competition_rate comp_rate\n" +
                "join competition c on comp_rate.competition_id= c.competition_id \n" +
                "join rate_type t on comp_rate.rate_id = t.rate_id\n" +
                "join opponent o1 on c.opponent_first = o1.opponent_id\n" +
                "join opponent o2 on\n" +
                "c.opponent_second = o2.opponent_id\n" +
                "join league l1 on \n" +
                "l1.league_id = o1.league_id\n" +
                "join league l2 on\n" +
                "l2.league_id = o2.league_id\n" +
                "join category category1 on \n" +
                "category1.category_id = l1.category_id\n" +
                "join category category2 on \n" +
                "category2.category_id = l2.category_id\n" +
                "\n" +
                "where comp_rate.payment =0 and status = 'finished'";
    }




    public abstract List<CompetitionRate> getRatesByCategoryAndRate(String category, String rate) throws DaoException;
    public abstract CompetitionRate getCompetitionRateById(Long id) throws DaoException;

    public abstract void createCompetitionRate(long opponentFirstId, long opponentSecondId, Rate[] rate) throws DaoException;

    public abstract List<CompetitionRate> getUnPaymentRate() throws DaoException;


}
