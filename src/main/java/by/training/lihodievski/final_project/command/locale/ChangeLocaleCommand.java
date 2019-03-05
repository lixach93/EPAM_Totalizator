package by.training.lihodievski.final_project.command.locale;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;

import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleCommand extends ActionCommand {
    @Override
    public String execute() throws CommandException {
        HttpSession session = request.getSession (false);
        session.setAttribute ("language",request.getParameter ("language"));
        String lastUrl = request.getHeader ("referer");
        request.setAttribute ("redirect",lastUrl);
        System.out.println (lastUrl);
        return "/WEB-INF/view/competitionRate.jsp";
    }
}
