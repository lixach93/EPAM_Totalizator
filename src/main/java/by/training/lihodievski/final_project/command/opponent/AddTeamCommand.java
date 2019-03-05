package by.training.lihodievski.final_project.command.opponent;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.impl.OpponentService;

public class AddTeamCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private OpponentService opponentService = serviceFactory.getOpponentService();

    @Override
    public String execute() throws CommandException {
        long leagueId = Long.parseLong (request.getParameter ("leagueId"));
        String teamName = request.getParameter ("teamName");
        String page = request.getParameter ("redirect");
        request.setAttribute ("redirect",page);
        try {
            opponentService.addOpponent (leagueId, teamName);
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
        return "/WEB-INF/view/adminPage.jsp";
    }
}
