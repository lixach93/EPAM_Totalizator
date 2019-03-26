package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ChangeRoleCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ChangeRoleCommand.class);
    private static final String ROLE = "role";
    private static final String USER_ID = "userId";

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION,ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        String role = request.getParameter (ROLE);
        String idStr = request.getParameter (USER_ID);
        boolean status;
        UserService userService = ServiceFactory.getInstance ().getUserService ();
        try {
            status = userService.changeRole(role, idStr);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ChangeRoleCommand.class", e);
            throw new CommandException (e);
        }
        HttpSession session = request.getSession ();

        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        long userId = (long) session.getAttribute (SESSION_ATTRIBUTE_USER_ID);
        if(userId == Long.parseLong (idStr)){
            session.invalidate ();
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
