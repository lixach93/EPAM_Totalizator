package by.training.lihodievski.final_project.command.locale;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;

import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleCommand extends ActionCommand {

    @Override
    public Respond execute() throws CommandException {
        HttpSession session = request.getSession (false);
        session.setAttribute ("language",request.getParameter ("language"));
        String lastUrl = request.getHeader ("referer");
        return new Respond (Respond.REDIRECT, lastUrl);
    }
}
