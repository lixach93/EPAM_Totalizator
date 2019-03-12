package by.training.lihodievski.final_project.command.user.admin;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import static by.training.lihodievski.final_project.util.Constants.ERROR_PERMISSION_INFO;
import static by.training.lihodievski.final_project.util.Constants.FORWARD_ADMIN_PAGE;
import static by.training.lihodievski.final_project.util.Constants.REQUEST_ATTRIBUTE_PERMISSION;

public class ShowAdminPageCommand extends ActionCommand {

    @Override
    public String execute() throws CommandException {

        return "/WEB-INF/view/adminPage.jsp";
    }


    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION,ERROR_PERMISSION_INFO);
        }
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
