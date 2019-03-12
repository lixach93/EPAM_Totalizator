package by.training.lihodievski.final_project.command.user.admin;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ChooseAdminPageCommand extends ActionCommand {

    private static final String OPPONENT = "team";
    private static final String COMPETITION = "competition";
    private static final String RATE = "rate";
    @Override
    public String execute() throws CommandException {
       return  null;
    }

    @Override
    public Respond execute1() throws CommandException {
        String page = request.getParameter (PARAMETER_PAGE);
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }

        switch (page){
            case LEAGUE:
                request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
                break;
            case OPPONENT:
                request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_TWO, ACTIVE);
                break;
            case COMPETITION:
                request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_THREE, ACTIVE);
                break;
            case RATE:
                request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_FOUR, ACTIVE);
                break;
            default:
                String redirect = (String) request.getServletContext ().getAttribute (PARAMETER_REDIRECT);
                request.setAttribute (REQUEST_ATTRIBUTE_BACK, redirect);
                request.setAttribute (REQUEST_ATTRIBUTE_ERROR, ERROR_MESSAGE);
                return new Respond (Respond.PAGE, FORWARD_ERROR_PAGE);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_BLOCK , page);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
