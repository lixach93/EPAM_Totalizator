package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ShowUpdateBalancePage extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowUpdateBalancePage.class);
    private static final String FILL_BALANCE = "fillBalance";

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_THREE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, FILL_BALANCE);
        return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
    }
}
