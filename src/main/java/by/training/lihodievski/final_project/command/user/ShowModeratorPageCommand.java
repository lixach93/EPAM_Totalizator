package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import static by.training.lihodievski.final_project.util.Constants.FORWARD_MODERATOR_PAGE;

public class ShowModeratorPageCommand extends ActionCommand {


    @Override
    public String execute() throws CommandException {
        System.out.println (request.getLocale() + " dsa");
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            request.setAttribute ("permissionMessage","Only for admin");
        }
        return "/WEB-INF/view/personalPage.jsp";
    }

    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.MODERATOR});
        } catch (PermissionException e) {
            request.setAttribute ("permissionMessage","Only for moderator");
        }
        return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
    }
}
