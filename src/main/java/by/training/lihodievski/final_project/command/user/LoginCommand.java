package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class LoginCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (LoginCommand.class);
    private static final String PARAMETER_REDIRECT_ERROR = "redirectError";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private UserService userServiceImpl = serviceFactory.getUserService ();

    @Override
    public Respond execute() throws CommandException {
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        String login = request.getParameter (PARAMETER_LOGIN);
        String password = request.getParameter (PARAMETER_PASSWORD);
        User user = null;
        HttpSession session = request.getSession ();
        try {
            user = userServiceImpl.login (login,password);
        }catch (UserException e) {
            LOGGER.error ("Exception validate user ");
            redirect = request.getParameter (PARAMETER_REDIRECT_ERROR);
            session.setAttribute (SESSION_ATTRIBUTE_ERROR, e.getMessage ());
            return new Respond (Respond.REDIRECT, redirect);
        }catch (ServiceException e) {
            LOGGER.error ("Exception in LoginCommand.class ", e);
            throw new CommandException ("LoginCommand exception ", e);
        }
        session.setAttribute (SESSION_ATTRIBUTE_USER_ID, user.getId ());
        session.setAttribute (SESSION_ATTRIBUTE_LOGIN, user.getLogin ());
        session.setAttribute (SESSION_ATTRIBUTE_ROLE, user.getRoleType ().getValue ());
        return new Respond (Respond.REDIRECT, redirect);
    }
}
