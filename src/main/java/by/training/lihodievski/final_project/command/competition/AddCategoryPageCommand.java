package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCategoryPageCommand extends ActionCommand {


    @Override
    public String execute() throws CommandException {
        return "/WEB-INF/view/addCategory.jsp";
    }
}
