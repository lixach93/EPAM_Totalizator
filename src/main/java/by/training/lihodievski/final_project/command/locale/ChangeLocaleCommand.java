package by.training.lihodievski.final_project.command.locale;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;

import javax.servlet.http.HttpSession;


public class ChangeLocaleCommand extends ActionCommand {

    private static final String REFERER = "referer";
    private static final String LANGUAGE = "language";
    @Override
    public Respond execute() throws CommandException {
        HttpSession session = request.getSession ();
        session.setAttribute (LANGUAGE, request.getParameter (LANGUAGE));
        String lastUrl = request.getHeader (REFERER);
        return new Respond (Respond.REDIRECT, lastUrl);
    }
}
