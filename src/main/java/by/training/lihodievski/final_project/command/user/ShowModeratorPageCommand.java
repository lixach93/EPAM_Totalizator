package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import static by.training.lihodievski.final_project.util.Constants.ERROR_PERMISSION_INFO;
import static by.training.lihodievski.final_project.util.Constants.FORWARD_MODERATOR_PAGE;
import static by.training.lihodievski.final_project.util.Constants.REQUEST_ATTRIBUTE_PERMISSION;

public class ShowModeratorPageCommand extends ActionCommand {


    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.MODERATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
        }
        return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
    }
}
