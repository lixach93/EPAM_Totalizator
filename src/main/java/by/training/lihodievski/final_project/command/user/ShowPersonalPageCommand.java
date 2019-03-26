package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.bean.User;
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
import static by.training.lihodievski.final_project.util.Constants.ACTIVE;
import static by.training.lihodievski.final_project.util.Constants.REQUEST_ATTRIBUTE_ACTION;

public class ShowPersonalPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowPersonalPageCommand.class);
    private static final String PERSONAL_INFO = "personalInfo";


    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
        }

        HttpSession session = request.getSession ();
        long id = (long) session.getAttribute (SESSION_ATTRIBUTE_USER_ID);
        User user;
        UserService userService = ServiceFactory.getInstance ().getUserService ();
        try {
            user = userService.getUserById(id);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowPersonalPageCommand.class" ,e);
            throw new CommandException (e);
        }

        request.setAttribute (REQUEST_ATTRIBUTE_USER, user);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, PERSONAL_INFO);
        return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
    }
}
