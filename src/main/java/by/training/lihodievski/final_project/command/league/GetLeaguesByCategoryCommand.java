package by.training.lihodievski.final_project.command.league;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class GetLeaguesByCategoryCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (GetLeaguesByCategoryCommand.class);
    private static final String CATEGORY_ID = "categoryId";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private LeagueService leagueService = serviceFactory.getLeagueService ();



    @Override
    public Respond execute() throws CommandException {
        long categoryId = Long.parseLong (request.getParameter (CATEGORY_ID));
        List<League> leagues;
        try {
            leagues = leagueService.getLeagueByCategory (categoryId);
            request.setAttribute (REQUEST_ATTRIBUTE_JSON, JsonSerializer.json (leagues));
        } catch (ServiceException | JsonProcessingException e) {
            LOGGER.error ("Error in GetLeaguesByCategoryCommand.class ", e);
            throw new CommandException (e);
        }
        response.setHeader (RESPONSE_CONTENT_TYPE, JSON);
        return new Respond (Respond.PAGE, FORWARD_JSON_PAGE);
    }
}
