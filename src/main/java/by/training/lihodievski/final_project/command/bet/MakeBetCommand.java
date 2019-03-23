package by.training.lihodievski.final_project.command.bet;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.BetService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class MakeBetCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (MakeBetCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private BetService betServiceImpl = serviceFactory.getBettingService ();
    private static final String PARAMETER_MONEY = "money";
    private static final String PARAMETER_FIRST_SCORE = "firstScore";
    private static final String PARAMETER_SECOND_SCORE = "secondScore";


    @Override
    public Respond execute() throws CommandException {
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        try {
            checkRole (request,new RoleType[]{RoleType.USER});
        } catch (PermissionException e) {
            return new Respond (Respond.REDIRECT, redirect);
        }
        String moneyStr = request.getParameter (PARAMETER_MONEY);
        String eventIdStr = request.getParameter (PARAMETER_EVENT_ID);
        HttpSession session = request.getSession (false);
        long userId = (long) session.getAttribute (SESSION_ATTRIBUTE_USER_ID);
        boolean status;
        try{
            String teamStr = request.getParameter (PARAMETER_TEAM);
            if(Validation.isNull (teamStr)){
                String firstScoreStr = request.getParameter (PARAMETER_FIRST_SCORE);
                String secondScoreStr = request.getParameter (PARAMETER_SECOND_SCORE);
                status = betServiceImpl.makeBet (userId, eventIdStr, firstScoreStr, secondScoreStr, moneyStr);
            }else{
                status = betServiceImpl.makeBet (userId, eventIdStr, teamStr, moneyStr);
            }
        }catch (ServiceException e){
            LOGGER.error ("Exception in MakeBetCommand ",e);
            throw new CommandException (e);
        } catch (UserException e) {
            session.setAttribute (SESSION_ATTRIBUTE_ERROR, e.getMessage ());
            return new Respond (Respond.REDIRECT, redirect);
        }
        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
