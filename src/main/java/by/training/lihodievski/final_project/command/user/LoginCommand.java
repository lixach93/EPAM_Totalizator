package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import javax.servlet.http.HttpSession;

public class LoginCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private UserService userServiceImpl = serviceFactory.getUserService ();
    @Override
    public String execute() throws CommandException {
        String page = "/WEB-INF/view/loginPage.jsp";
        String login = request.getParameter ("login");
        String password = request.getParameter ("password");
        User user = null;
        try {
            user = userServiceImpl.login (login,password);
        }catch (UserException e) {
            request.setAttribute ("errorMessage", e.getMessage ());
            request.setAttribute ("userError",e.getUser ());
            return page;
        }catch (ServiceException e) {
            throw new CommandException ("LoginCommand exception ", e);
        }
        HttpSession session = request.getSession (true);
        session.setAttribute ("userId",user.getId ());
        session.setAttribute ("userLogin",user.getLogin ());
        session.setAttribute ("userRole",user.getRoleType ().getValue ());
        page =request.getParameter ("redirect");
        request.setAttribute ("redirect", page);
        return page;
    }
}
