package by.training.lihodievski.final_project.service.factory;

import by.training.lihodievski.final_project.service.*;
import by.training.lihodievski.final_project.service.impl.*;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory ();

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

    public TeamService getTeamService() {
        return TeamServiceImpl.getInstance();
    }

    public EventService getEventService() {
        return EventServiceImpl.getInstance();
    }

    public BetService getBettingService(){
        return BetServiceImpl.getInstance ();
    }

    public CompetitionService getCompetitionService(){
        return CompetitionServiceImpl.getInstance();
    }
}
