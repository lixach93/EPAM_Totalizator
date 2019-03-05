package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CompetitionRateService;
import by.training.lihodievski.final_project.service.CompetitionService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.util.List;

public class ShowCloseCompetitionPage extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionService competitionService = serviceFactory.getCompetitionService ();
    @Override
    public String execute() throws CommandException {
        request.setAttribute ("activeThree", "active");
        request.setAttribute ("page", "competition");
        request.setAttribute ("action", "closeCompetition");
        List<Competition> competitions;
        try{
           competitions = competitionService.getCompetition ();
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
        request.setAttribute ("competitions",competitions);
        return "/WEB-INF/view/adminPage.jsp";
    }
}
