package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.ERROR_PERMISSION_INFO;
import static by.training.lihodievski.final_project.util.Constants.FORWARD_ADMIN_PAGE;

public class CreateCompetitionCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (CreateCompetitionCommand.class);
    private static final String TEAM_FIRST_ID = "teamFirstId";
    private static final String TEAM_SECOND_ID = "teamSecondId";
    private static final String RATE = "rate";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();


    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        String teamFirstIdStr = request.getParameter (TEAM_FIRST_ID);
        String teamSecondIdStr = request.getParameter (TEAM_SECOND_ID);
        String typeRate = request.getParameter (RATE);
        boolean status;
        try {
            status = eventService.createEvent (teamFirstIdStr, teamSecondIdStr, typeRate);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in CreateCompetitionCommand.class ", e);
            throw new CommandException (e);
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
