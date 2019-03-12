package by.training.lihodievski.final_project.command.league;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class CreateLeagueCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (CreateLeagueCommand.class);
    private static final String CATEGORY_NAME = "categoryName";
    private static final String LEAGUE_NAME = "leagueName";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private LeagueService leagueService = serviceFactory.getLeagueService ();

    @Override
    public String execute() throws CommandException {
        return "/WEB-INF/view/adminPage.jsp";
    }

    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        String categoryName = request.getParameter (CATEGORY_NAME);
        String leagueName = request.getParameter (LEAGUE_NAME);
        boolean status;

        try {
            status = leagueService.createLeague (categoryName, leagueName);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in CreateLeagueCommand.class ", e);
            throw new CommandException (e);
        } catch (ValidationException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_BACK, redirect);
            request.setAttribute (REQUEST_ATTRIBUTE_ERROR, e.getMessage ());
            return new Respond (Respond.PAGE, FORWARD_ERROR_PAGE);
        }
        HttpSession session = request.getSession (false);
        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
