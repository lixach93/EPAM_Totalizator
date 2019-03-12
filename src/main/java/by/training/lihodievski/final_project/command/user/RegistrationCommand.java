package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

public class RegistrationCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private UserService userServiceImpl = serviceFactory.getUserService ();

    @Override
    public String execute() throws CommandException {
        String url = request.getParameter ("redirect");
        String page = "/WEB-INF/view/registrationPage.jsp";
        String login = request.getParameter ("login");
        String email = request.getParameter ("email");
        String password = request.getParameter ("password");
        String confirmPassword = request.getParameter ("confirmPassword");
        try {
            userServiceImpl.registration (login, email, password, confirmPassword);
        } catch (UserException e) {
            request.setAttribute ("errorMessage", e.getMessage ());
            request.setAttribute ("userError",e.getUser ());
            return null;
        }catch (ServiceException e) {
            throw new CommandException ("RegistrationCommand exception ", e);
        }
        request.setAttribute ("success", login);

        return null;
    }

    @Override
    public Respond execute1() throws CommandException {
        HttpSession session = request.getSession (false);
        String url = request.getParameter ("redirect");
        String login = request.getParameter ("login");
        String email = request.getParameter ("email");
        String password = request.getParameter ("password");
        String confirmPassword = request.getParameter ("confirmPassword");
        try {
            userServiceImpl.registration (login, email, password, confirmPassword);
        } catch (UserException e) {
            session.setAttribute ("errorMessage", e.getMessage ());
            session.setAttribute ("userError", e.getUser ());
            return new Respond (Respond.REDIRECT, url);
        }catch (ServiceException e) {
            throw new CommandException ("RegistrationCommand exception ", e);
        }
        session.setAttribute ("success", login);
        return new Respond (Respond.REDIRECT, url);
    }
}
