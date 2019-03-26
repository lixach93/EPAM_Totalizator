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


public class ShowActiveBetPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowActiveBetPageCommand.class);
    private static final String ACTIVE_BET = "activeBet";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private BetService betService = serviceFactory.getBettingService ();

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
        }
        HttpSession session = request.getSession ();
        long userId = (long) session.getAttribute (SESSION_ATTRIBUTE_USER_ID);
        String numberPageStr =  request.getParameter (PARAMETER_PAGE);
        int numberPage;
        if(numberPageStr != null){
            numberPage = (Integer.parseInt (numberPageStr)-1)*2;
        }else{
            numberPage = 0;
        }
        List<Bet> bets;
        int countPage;
        try {
            countPage = betService.getCountPage (userId);
            bets = betService.getActiveBet(userId, numberPage );
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowActiveBetPageCommand.class ", e);
            throw new CommandException (e);
        }

        request.setAttribute (REQUEST_ATTRIBUTE_SIZE, bets.size ());
        request.setAttribute (REQUEST_ATTRIBUTE_COUNT_PAGE, countPage);
        request.setAttribute (REQUEST_ATTRIBUTE_BETS, bets);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_TWO, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, ACTIVE_BET);

        return new Respond (Respond.PAGE, FORWARD_PERSONAL_PAGE);
    }
}
