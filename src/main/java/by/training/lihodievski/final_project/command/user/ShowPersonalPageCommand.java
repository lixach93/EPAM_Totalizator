package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;

public class ShowPersonalPageCommand extends ActionCommand {
    @Override
    public String execute() throws CommandException {
        return "/WEB-INF/view/personalPage.jsp";
    }
}
