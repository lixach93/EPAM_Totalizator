package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

public class ShowLoginPageCommand extends ActionCommand {
    @Override
    public String execute() throws CommandException {

        return "/WEB-INF/view/loginPage.jsp";
    }
}
