package by.training.lihodievski.final_project.command.betting;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.BettingService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;


public class ShowActiveBettingPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowActiveBettingPageCommand.class);
    private static final String ACTIVE_BETTING = "activeBetting";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private BettingService bettingService = serviceFactory.getBettingService ();
    @Override
    public String execute() throws CommandException {
        return null;
    }

    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
        }
        HttpSession session = request.getSession ();
        long id = (long) session.getAttribute (SESSION_ATTRIBUTE_ID);
        List<Betting> bettingList;
        try {
            bettingList = bettingService.getActiveBettingForUser(id);
        } catch (ServiceException e) {
            LOGGER.error ("Error in ShowActiveBettingPageCommand.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_BETTINGS, bettingList);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_TWO, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_BLOCK, BETTING);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, ACTIVE_BETTING);
        request.setAttribute (REQUEST_ATTRIBUTE_LINK_ONE, ACTIVE);
        return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
    }
}
