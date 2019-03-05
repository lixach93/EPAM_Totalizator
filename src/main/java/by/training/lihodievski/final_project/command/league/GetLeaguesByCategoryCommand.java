package by.training.lihodievski.final_project.command.league;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;


import java.util.List;

public class GetLeaguesByCategoryCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private LeagueService leagueService = serviceFactory.getLeagueService ();

    @Override
    public String execute() throws CommandException {
        long id = Long.parseLong (request.getParameter ("categoryId"));
        List<League> leagues;
        try {
            leagues = leagueService.getLeagueByCategory (id);
            request.setAttribute ("json", JsonSerializer.json (leagues));
        } catch (ServiceException | JsonProcessingException e) {
            throw new CommandException (e);
        }
        response.setHeader ("Content-type)", "json");
        return "/WEB-INF/ajax/json.jsp";
    }
}
