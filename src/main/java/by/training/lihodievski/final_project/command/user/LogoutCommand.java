package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.FORWARD_MAIN_PAGE;

public class LogoutCommand extends ActionCommand {

    @Override
    public Respond execute() throws CommandException {
        HttpSession session = request.getSession ();
       session.invalidate ();
        return new Respond (Respond.PAGE, FORWARD_MAIN_PAGE);
    }
}
