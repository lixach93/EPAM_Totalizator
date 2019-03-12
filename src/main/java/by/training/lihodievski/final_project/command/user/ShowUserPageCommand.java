package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.betting.ShowActiveBettingPageCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.FORWARD_PERSONAL_PAGE;
import static by.training.lihodievski.final_project.util.Constants.REQUEST_ATTRIBUTE_ACTION;

public class ShowUserPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowUserPageCommand.class);
    private static final String USER_INFO = "userInfo";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private UserService userServiceImpl = serviceFactory.getUserService ();

    @Override
    public String execute() throws CommandException {
        return null;
    }

    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
        }

        HttpSession session = request.getSession ();
        long id = (long) session.getAttribute (SESSION_ATTRIBUTE_ID);
        User user;
        try {
            user = userServiceImpl.getUserById(id);
        } catch (ServiceException e) {
            LOGGER.error ("Error in ShowUserPageCommand " ,e);
            throw new CommandException (e);
        }

        request.setAttribute (REQUEST_ATTRIBUTE_USER, user);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_BLOCK, PERSONAL);
        request.setAttribute (REQUEST_ATTRIBUTE_LINK_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, USER_INFO);
        return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
    }
}