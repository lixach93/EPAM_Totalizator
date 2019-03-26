package by.training.lihodievski.final_project.command.league;

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

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class DeleteLeagueCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (DeleteLeagueCommand.class);
    private static final String LEAGUE_ID = "leagueId";

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        String leagueIdStr = request.getParameter (LEAGUE_ID);
        boolean status;
        LeagueService leagueService = ServiceFactory.getInstance ().getLeagueService ();
        try {
            status = leagueService.deleteLeague (leagueIdStr);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in DeleteLeagueCommand.class ", e);
            throw new CommandException (e);
        }
        HttpSession session = request.getSession ();
        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
