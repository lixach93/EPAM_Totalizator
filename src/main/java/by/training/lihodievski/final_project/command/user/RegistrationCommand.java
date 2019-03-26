package by.training.lihodievski.final_project.command.user;

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

public class RegistrationCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (RegistrationCommand.class);
    private static final String CONFIRM_PASSWORD = "confirmPassword";

    @Override
    public Respond execute() throws CommandException {
        HttpSession session = request.getSession ();
        String redirect = request.getParameter (PARAMETER_REDIRECT );
        String login = request.getParameter (PARAMETER_LOGIN);
        String email = request.getParameter (PARAMETER_EMAIL);
        String password = request.getParameter (PARAMETER_PASSWORD);
        String confirmPassword = request.getParameter (CONFIRM_PASSWORD);
        UserService userService = ServiceFactory.getInstance ().getUserService ();
        try {
            userService.registration (login, email, password, confirmPassword);
        } catch (UserException e) {
            LOGGER.error ("Exception validate user ");
            session.setAttribute (SESSION_ATTRIBUTE_ERROR, e.getMessage ());
            return new Respond (Respond.REDIRECT, redirect);
        }catch (ServiceException e) {
            LOGGER.error ("Exception in RegistrationCommand.class ", e);
            throw new CommandException ("RegistrationCommand exception ", e);
        }
        session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        return new Respond (Respond.REDIRECT, redirect);
    }
}
