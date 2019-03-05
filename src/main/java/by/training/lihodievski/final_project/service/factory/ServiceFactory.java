package by.training.lihodievski.final_project.service.factory;

import by.training.lihodievski.final_project.service.*;
import by.training.lihodievski.final_project.service.impl.*;

public class ServiceFactory {

    private final static ServiceFactory INSTANCE = new ServiceFactory ();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return INSTANCE;
    }
    public UserService getUserService(){
        return UserServiceImpl.getInstance ();
    }
    public CategoryService getCategoryService(){
        return CategoryServiceImpl.getInstance ();
    }
    public LeagueService getLeagueService(){
        return LeagueServiceImpl.getInstance ();
    }

    public OpponentService getOpponentService() {
        return OpponentServiceImpl.getInstance();}

    public CompetitionRateService getCompetitionRateServiceImpl() {
        return CompetitionRateServiceImpl.getInstance();
    }
    public BettingService getBettingServiceImpl(){
        return BettingServiceImpl.getInstance ();
    }
    public CompetitionService getCompetitionService(){
        return CompetitionServiceImpl.getInstance();
    }
}
