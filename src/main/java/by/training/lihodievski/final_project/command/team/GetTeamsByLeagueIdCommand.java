package by.training.lihodievski.final_project.command.team;

import by.training.lihodievski.final_project.bean.Team;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.TeamService;
import by.training.lihodievski.final_project.util.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class GetTeamsByLeagueIdCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (GetTeamsByLeagueIdCommand.class);
    private static final String LEAGUE_ID = "leagueId";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private TeamService teamService = serviceFactory.getTeamService ();

    @Override
    public Respond execute() throws CommandException {
        long id = Long.parseLong (request.getParameter (LEAGUE_ID));
        List<Team> teams;
        try {
            teams = teamService.getTeamByLeague (id);
            request.setAttribute (REQUEST_ATTRIBUTE_JSON, JsonSerializer.json (teams));
        } catch (ServiceException | JsonProcessingException e) {
            LOGGER.error ("Exception in GetTeamsByLeagueIdCommand.class ", e);
            throw new CommandException (e);
        }
        response.setHeader (RESPONSE_CONTENT_TYPE, JSON);
        return new Respond (Respond.PAGE, FORWARD_JSON_PAGE);
    }
}
