package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class FillBalanceCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (FillBalanceCommand.class);
    private static final String FILL_BALANCE = "fillBalance";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private UserService userServiceImpl = serviceFactory.getUserService ();


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
        String cardNumberStr = request.getParameter ("cardNumber");
        String moneyStr = request.getParameter ("money");
        boolean status;
        try {
             status = userServiceImpl.updateBalance(id, cardNumberStr, moneyStr);
        } catch (ServiceException e) {
            LOGGER.error ("Error in ShowUserPageCommand " ,e);
            throw new CommandException (e);
        }



        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_THREE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, FILL_BALANCE );
        return new Respond (Respond.REDIRECT, redirect);
    }
}
