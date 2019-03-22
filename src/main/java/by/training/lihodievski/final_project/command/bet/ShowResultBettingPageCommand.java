package by.training.lihodievski.final_project.command.bet;

import by.training.lihodievski.final_project.bean.Bet;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.BetService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.ERROR_PERMISSION_INFO;

public class ShowResultBettingPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowResultBettingPageCommand.class);
    private static final String RESULT_BETTING = "resultBetting";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private BetService betService = serviceFactory.getBettingService ();

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
        }
        HttpSession session = request.getSession ();
        long id = (long) session.getAttribute (SESSION_ATTRIBUTE_USER_ID);
        List<Bet> bettingList;
        try {
            bettingList = betService.getResultBetForUser (id);
        } catch (ServiceException e) {
            LOGGER.error ("Error in ShowResultBettingPageCommand.class ", e);
            throw new CommandException (e);
        }

        request.setAttribute (REQUEST_ATTRIBUTE_BETS, bettingList);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_TWO, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, RESULT_BETTING );;
        return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
    }
}
