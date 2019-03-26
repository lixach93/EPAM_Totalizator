package by.training.lihodievski.final_project.command.league;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.ACTIVE;
import static by.training.lihodievski.final_project.util.Constants.REQUEST_ATTRIBUTE_ACTION;

public class ShowDeleteLeaguePageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowDeleteLeaguePageCommand.class);
    private static final String DELETE_LEAGUE = "deleteLeague";
    private static final String LEAGUES = "leagues";

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        LeagueService leagueService = ServiceFactory.getInstance ().getLeagueService ();
        List<League> leagues;
        try {
             leagues =  leagueService.getLeagues();
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowDeleteLeaguePageCommand.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (LEAGUES, leagues);
        request.setAttribute (REQUEST_ATTRIBUTE_SIZE, leagues.size ());
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, DELETE_LEAGUE);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
