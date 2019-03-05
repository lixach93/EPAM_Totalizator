package by.training.lihodievski.final_project.dao.factory;

import by.training.lihodievski.final_project.dao.impl.betting.BettingDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.betting.BettingDaoImpl;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.competition.CompetitionDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoImpl;
import by.training.lihodievski.final_project.dao.impl.competition.CompetitionDaoImpl;
import by.training.lihodievski.final_project.dao.impl.competition_rate.CompetitionRateAbstract;
import by.training.lihodievski.final_project.dao.impl.competition_rate.CompetitionRateImpl;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoImpl;
import by.training.lihodievski.final_project.dao.impl.opponent.OpponentDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.opponent.OpponentDaoImpl;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoImpl;

public class DaoFactory {

    private final static DaoFactory INSTANCE = new DaoFactory ();

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
    public CompetitionRateAbstract getCompetitionRateDao(){
        return CompetitionRateImpl.getInstance ();
    }
    public UserDaoAbstract getUserDao(){return UserDaoImpl.getInstance();}

    public OpponentDaoAbstract getOpponentDao() {
        return OpponentDaoImpl.getInstance();
    }
    public BettingDaoAbstract getBettingDao(){
        return BettingDaoImpl.getInstance ();
    }
}


