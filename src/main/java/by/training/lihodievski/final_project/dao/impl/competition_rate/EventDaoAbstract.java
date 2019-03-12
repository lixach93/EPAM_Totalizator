package by.training.lihodievski.final_project.dao.impl.competition_rate;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

import java.util.List;

public abstract class EventDaoAbstract extends AbstractGenericDao<Event> {

    public abstract List<Event> getEventsByRate(String category, String rate) throws DaoException;
    public abstract Event getEventById(Long id) throws DaoException;
    public abstract void createCompetitionRate(long opponentFirstId, long opponentSecondId, Rate[] rate) throws DaoException;
    public abstract List<Event> getUnPaymentRate() throws DaoException;
    public abstract List<Event> getActiveEvent() throws DaoException;

    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE  totalizator.event SET percent = ?  where event_id = ?";
    }

    @Override
    protected String getSelectSql() {
        return null;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT into totalizator.event (competition_id, rate_id) value (? , ?)";
    }

     String getInsertSqlCompetition(){
        return "INSERT into totalizator.competition (team_first, team_second) value (?, ?)";
    }

     String getEventsByRateQuery(){
        return "select e.event_id,e.competition_id,t1.team_id as team_first_id,t1.name as team_first_name," +
                "t2.team_id as team_second_id,t2.name as team_second_name ,c.status,c.team_first_result," +
                "c.team_second_result,c.winner,l1.name as league_first_name,l2.name as league_second_name," +
                "category1.name as category_first_name,category2.name as category_second_name,r.name as rate_value," +
                "e.payment,e.percent,e.win_percent from event e" +
                " join competition c on e.competition_id= c.competition_id" +
                " join rate_type r on e.rate_id = r.rate_id" +
                " join team t1 on c.team_first = t1.team_id" +
                " join team t2 on" +
                " c.team_second = t2.team_id" +
                " join league l1 on" +
                " l1.league_id = t1.league_id" +
                " join league l2 on" +
                " l2.league_id = t2.league_id" +
                " join category category1 on" +
                " category1.category_id = l1.category_id" +
                " join category category2 on " +
                " category2.category_id = l2.category_id" +
                " where  category1.name in (select category2.name from event where category2.name = ?)" +
                " and r.name = ? and c.status = 'new'";
    }

     String getEventByIdQuery(){
        return "select e.event_id,e.competition_id,t1.team_id as team_first_id,t1.name as team_first_name," +
                "t2.team_id as team_second_id,t2.name as team_second_name ,c.status,c.team_first_result," +
                "c.team_second_result,c.winner,l1.name as league_first_name,l2.name as league_second_name," +
                "category1.name as category_first_name,category2.name as category_second_name,r.name as rate_value," +
                "e.payment,e.percent,e.win_percent from event e" +
                " join competition c on e.competition_id= c.competition_id" +
                " join rate_type r on e.rate_id = r.rate_id" +
                " join team t1 on c.team_first = t1.team_id" +
                " join team t2 on" +
                " c.team_second = t2.team_id" +
                " join league l1 on" +
                " l1.league_id = t1.league_id" +
                " join league l2 on" +
                " l2.league_id = t2.league_id" +
                " join category category1 on" +
                " category1.category_id = l1.category_id" +
                " join category category2 on " +
                " category2.category_id = l2.category_id" +
                " where e.event_id =?";
    }

    String getEventByPaymentQuery(){
        return "select e.event_id,e.competition_id,t1.team_id as team_first_id,t1.name as team_first_name," +
                "t2.team_id as team_second_id,t2.name as team_second_name ,c.status,c.team_first_result," +
                "c.team_second_result,c.winner,l1.name as league_first_name,l2.name as league_second_name," +
                "category1.name as category_first_name,category2.name as category_second_name,r.name as rate_value," +
                "e.payment,e.percent,e.win_percent from event e" +
                " join competition c on e.competition_id= c.competition_id" +
                " join rate_type r on e.rate_id = r.rate_id" +
                " join team t1 on c.team_first = t1.team_id" +
                " join team t2 on" +
                " c.team_second = t2.team_id" +
                " join league l1 on" +
                " l1.league_id = t1.league_id" +
                " join league l2 on" +
                " l2.league_id = t2.league_id" +
                " join category category1 on" +
                " category1.category_id = l1.category_id" +
                " join category category2 on " +
                " category2.category_id = l2.category_id" +
                " where e.payment = 0 and status = 'finished'";
    }
    String getActiveEventsQuery(){
        return "select e.event_id,e.competition_id,t1.team_id as team_first_id,t1.name as team_first_name," +
                "t2.team_id as team_second_id,t2.name as team_second_name ,c.status,c.team_first_result," +
                "c.team_second_result,c.winner,l1.name as league_first_name,l2.name as league_second_name," +
                "category1.name as category_first_name,category2.name as category_second_name,r.name as rate_value," +
                "e.payment,e.percent,e.win_percent from event e" +
                " join competition c on e.competition_id= c.competition_id" +
                " join rate_type r on e.rate_id = r.rate_id" +
                " join team t1 on c.team_first = t1.team_id" +
                " join team t2 on" +
                " c.team_second = t2.team_id" +
                " join league l1 on" +
                " l1.league_id = t1.league_id" +
                " join league l2 on" +
                " l2.league_id = t2.league_id" +
                " join category category1 on" +
                " category1.category_id = l1.category_id" +
                " join category category2 on " +
                " category2.category_id = l2.category_id" +
                " where e.percent = 0 and status = 'new'";
    }
}
