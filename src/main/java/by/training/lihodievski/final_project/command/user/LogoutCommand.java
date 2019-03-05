package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;

import javax.servlet.http.HttpSession;

public class LogoutCommand extends ActionCommand {

    @Override
    public String execute() throws CommandException {
        HttpSession session = request.getSession (false);
        session.invalidate ();
        return null;
    }
}
