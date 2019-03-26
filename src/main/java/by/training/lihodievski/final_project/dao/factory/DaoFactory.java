package by.training.lihodievski.final_project.dao.factory;

import by.training.lihodievski.final_project.dao.impl.bet.BetDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.bet.BetDaoImpl;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.competition.CompetitionDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoImpl;
import by.training.lihodievski.final_project.dao.impl.competition.CompetitionDaoImpl;
import by.training.lihodievski.final_project.dao.impl.event.EventDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.event.EventDaoImpl;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoImpl;
import by.training.lihodievski.final_project.dao.impl.team.TeamDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.team.TeamDaoImpl;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoImpl;

public class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory ();

    private DaoFactory() {
    }
    public static DaoFactory getInstance(){
        return INSTANCE;
    }

    public CategoryDaoAbstract getCategoryDao(){
        return CategoryDaoImpl.getInstance ();
    }

    public LeagueDaoAbstract getLeagueDao(){
        return LeagueDaoImpl.getInstance ();
    }

    public CompetitionDaoAbstract getCompetitionDao(){
        return CompetitionDaoImpl.getInstance ();
    }

    public EventDaoAbstract getEventDao(){
        return EventDaoImpl.getInstance ();
    }

    public UserDaoAbstract getUserDao(){
        return UserDaoImpl.getInstance();
    }

    public TeamDaoAbstract getTeamDao() {
        return TeamDaoImpl.getInstance();
    }

    public BetDaoAbstract getBetDao(){
        return BetDaoImpl.getInstance ();
    }

}


