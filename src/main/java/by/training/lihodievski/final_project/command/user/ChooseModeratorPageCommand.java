package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ChooseModeratorPageCommand extends ActionCommand {

    private static final String EVENT = "event";
    @Override
    public String execute() throws CommandException {
        return null;
    }

    @Override
    public Respond execute1() throws CommandException {
        String page = request.getParameter (PARAMETER_PAGE);
        try {
            checkRole (request,new RoleType[]{RoleType.MODERATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
        }

        switch (page){
            case EVENT:
                request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
                break;
            default:
                String redirect = (String) request.getServletContext ().getAttribute (PARAMETER_REDIRECT);
                request.setAttribute (REQUEST_ATTRIBUTE_BACK, redirect);
                request.setAttribute (REQUEST_ATTRIBUTE_ERROR, ERROR_MESSAGE);
                return new Respond (Respond.PAGE, FORWARD_ERROR_PAGE);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_BLOCK , page);
        return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
    }
}
