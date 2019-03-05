package by.training.lihodievski.final_project.command.league;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

public class AddLeagueCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private LeagueService leagueService = serviceFactory.getLeagueService ();

    @Override
    public String execute() throws CommandException {
        long id = Long.parseLong (request.getParameter ("categoryId"));
        String leagueName = request.getParameter ("leagueName");
        String page = request.getParameter ("redirect");
        request.setAttribute ("redirect",page);
        try {
            leagueService.addLeague (id, leagueName);
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
        return "/WEB-INF/view/adminPage.jsp";
    }
}
