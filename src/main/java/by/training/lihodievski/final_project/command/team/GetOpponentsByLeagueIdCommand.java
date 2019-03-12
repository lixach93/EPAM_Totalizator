package by.training.lihodievski.final_project.command.team;

import by.training.lihodievski.final_project.bean.Team;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.impl.TeamService;
import by.training.lihodievski.final_project.util.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class GetOpponentsByLeagueIdCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private TeamService teamService = serviceFactory.getOpponentService ();

    @Override
    public String execute() throws CommandException {
        long id = Long.parseLong (request.getParameter ("leagueId"));
        List<Team> teams;
        try {
            teams = teamService.getOpponentByLeague(id);
            request.setAttribute ("json", JsonSerializer.json (teams));
        } catch (ServiceException | JsonProcessingException e) {
            throw new CommandException (e);
        }
        response.setHeader ("Content-type)", "json");
        return "/WEB-INF/ajax/json.jsp";
    }
}
